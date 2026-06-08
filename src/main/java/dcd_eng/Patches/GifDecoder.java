package dcd_eng.Patches;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Vector;

public class GifDecoder {
   public static final int STATUS_OK = 0;
   public static final int STATUS_FORMAT_ERROR = 1;
   public static final int STATUS_OPEN_ERROR = 2;
   protected static final int MAX_STACK_SIZE = 4096;
   protected InputStream in;
   protected int status;
   protected int width;
   protected int height;
   protected boolean gctFlag;
   protected int gctSize;
   protected int loopCount = 1;
   protected int[] gct;
   protected int[] lct;
   protected int[] act;
   protected int bgIndex;
   protected int bgColor;
   protected int lastBgColor;
   protected int pixelAspect;
   protected boolean lctFlag;
   protected boolean interlace;
   protected int lctSize;
   protected int ix;
   protected int iy;
   protected int iw;
   protected int ih;
   protected int lrx;
   protected int lry;
   protected int lrw;
   protected int lrh;
   protected DixieMap image;
   protected DixieMap lastPixmap;
   protected byte[] block = new byte[256];
   protected int blockSize = 0;
   protected int dispose = 0;
   protected int lastDispose = 0;
   protected boolean transparency = false;
   protected int delay = 0;
   protected int transIndex;
   protected short[] prefix;
   protected byte[] suffix;
   protected byte[] pixelStack;
   protected byte[] pixels;
   protected Vector<GifFrame> frames;
   protected int frameCount;

   public int getDelay(int n) {
      this.delay = -1;
      if (n >= 0 && n < this.frameCount) {
         this.delay = ((GifFrame)this.frames.elementAt(n)).delay;
      }

      return this.delay;
   }

   public int getFrameCount() {
      return this.frameCount;
   }

   public Pixmap getPixmap() {
      return this.getFrame(0);
   }

   public int getLoopCount() {
      return this.loopCount;
   }

   protected void setPixels() {
      int[] dest = new int[this.width * this.height];
      if (this.lastDispose > 0) {
         if (this.lastDispose == 3) {
            int n = this.frameCount - 2;
            if (n > 0) {
               this.lastPixmap = this.getFrame(n - 1);
            } else {
               this.lastPixmap = null;
            }
         }

         if (this.lastPixmap != null) {
            this.lastPixmap.getPixels(dest, 0, this.width, 0, 0, this.width, this.height);
            if (this.lastDispose == 2) {
               int c = 0;
               if (!this.transparency) {
                  c = this.lastBgColor;
               }

               for(int i = 0; i < this.lrh; ++i) {
                  int n1 = (this.lry + i) * this.width + this.lrx;
                  int n2 = n1 + this.lrw;

                  for(int k = n1; k < n2; ++k) {
                     dest[k] = c;
                  }
               }
            }
         }
      }

      int pass = 1;
      int inc = 8;
      int iline = 0;

      for(int i = 0; i < this.ih; ++i) {
         int line = i;
         if (this.interlace) {
            if (iline >= this.ih) {
               ++pass;
               switch (pass) {
                  case 2:
                     iline = 4;
                     break;
                  case 3:
                     iline = 2;
                     inc = 4;
                     break;
                  case 4:
                     iline = 1;
                     inc = 2;
               }
            }

            line = iline;
            iline += inc;
         }

         line += this.iy;
         if (line < this.height) {
            int k = line * this.width;
            int dx = k + this.ix;
            int dlim = dx + this.iw;
            if (k + this.width < dlim) {
               dlim = k + this.width;
            }

            for(int sx = i * this.iw; dx < dlim; ++dx) {
               int index = this.pixels[sx++] & 255;
               int c = this.act[index];
               if (c != 0) {
                  dest[dx] = c;
               }
            }
         }
      }

      this.image = new DixieMap(dest, this.width, this.height, Format.RGBA8888);
   }

   public DixieMap getFrame(int n) {
      if (this.frameCount <= 0) {
         return null;
      } else {
         n %= this.frameCount;
         return ((GifFrame)this.frames.elementAt(n)).image;
      }
   }

   public int read(InputStream is) {
      this.init();
      if (is != null) {
         this.in = is;
         this.readHeader();
         if (!this.err()) {
            this.readContents();
            if (this.frameCount < 0) {
               this.status = 1;
            }
         }
      } else {
         this.status = 2;
      }

      try {
         is.close();
      } catch (Exception var3) {
      }

      return this.status;
   }

   protected void decodeBitmapData() {
      int nullCode = -1;
      int npix = this.iw * this.ih;
      if (this.pixels == null || this.pixels.length < npix) {
         this.pixels = new byte[npix];
      }

      if (this.prefix == null) {
         this.prefix = new short[4096];
      }

      if (this.suffix == null) {
         this.suffix = new byte[4096];
      }

      if (this.pixelStack == null) {
         this.pixelStack = new byte[4097];
      }

      int data_size = this.read();
      int clear = 1 << data_size;
      int end_of_information = clear + 1;
      int available = clear + 2;
      int old_code = nullCode;
      int code_size = data_size + 1;
      int code_mask = (1 << code_size) - 1;

      for(int code = 0; code < clear; ++code) {
         this.prefix[code] = 0;
         this.suffix[code] = (byte)code;
      }

      int bi = 0;
      int pi = 0;
      int top = 0;
      int first = 0;
      int count = 0;
      int bits = 0;
      int datum = 0;
      int i = 0;

      while(i < npix) {
         if (top == 0) {
            if (bits < code_size) {
               if (count == 0) {
                  count = this.readBlock();
                  if (count <= 0) {
                     break;
                  }

                  bi = 0;
               }

               datum += (this.block[bi] & 255) << bits;
               bits += 8;
               ++bi;
               --count;
               continue;
            }

            int var20 = datum & code_mask;
            datum >>= code_size;
            bits -= code_size;
            if (var20 > available || var20 == end_of_information) {
               break;
            }

            if (var20 == clear) {
               code_size = data_size + 1;
               code_mask = (1 << code_size) - 1;
               available = clear + 2;
               old_code = nullCode;
               continue;
            }

            if (old_code == nullCode) {
               this.pixelStack[top++] = this.suffix[var20];
               old_code = var20;
               first = var20;
               continue;
            }

            int in_code = var20;
            if (var20 == available) {
               this.pixelStack[top++] = (byte)first;
               var20 = old_code;
            }

            while(var20 > clear) {
               this.pixelStack[top++] = this.suffix[var20];
               var20 = this.prefix[var20];
            }

            first = this.suffix[var20] & 255;
            if (available >= 4096) {
               break;
            }

            this.pixelStack[top++] = (byte)first;
            this.prefix[available] = (short)old_code;
            this.suffix[available] = (byte)first;
            ++available;
            if ((available & code_mask) == 0 && available < 4096) {
               ++code_size;
               code_mask += available;
            }

            old_code = in_code;
         }

         --top;
         this.pixels[pi++] = this.pixelStack[top];
         ++i;
      }

      for(int var21 = pi; var21 < npix; ++var21) {
         this.pixels[var21] = 0;
      }

   }

   protected boolean err() {
      return this.status != 0;
   }

   protected void init() {
      this.status = 0;
      this.frameCount = 0;
      this.frames = new Vector();
      this.gct = null;
      this.lct = null;
   }

   protected int read() {
      int curByte = 0;

      try {
         curByte = this.in.read();
      } catch (Exception var3) {
         this.status = 1;
      }

      return curByte;
   }

   protected int readBlock() {
      this.blockSize = this.read();
      int n = 0;
      if (this.blockSize > 0) {
         int count;
         try {
            for(count = 0; n < this.blockSize; n += count) {
               count = this.in.read(this.block, n, this.blockSize - n);
               if (count == -1) {
                  break;
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }

         if (n < this.blockSize) {
            this.status = 1;
         }
      }

      return n;
   }

   protected int[] readColorTable(int ncolors) {
      int nbytes = 3 * ncolors;
      int[] tab = null;
      byte[] c = new byte[nbytes];
      int n = 0;

      try {
         n = this.in.read(c);
      } catch (Exception e) {
         e.printStackTrace();
      }

      if (n < nbytes) {
         this.status = 1;
      } else {
         tab = new int[256];
         int i = 0;

         int r;
         int g;
         int b;
         for(int j = 0; i < ncolors; tab[i++] = -16777216 | r << 16 | g << 8 | b) {
            r = c[j++] & 255;
            g = c[j++] & 255;
            b = c[j++] & 255;
         }
      }

      return tab;
   }

   protected void readContents() {
      boolean done = false;

      while(!done && !this.err()) {
         int code = this.read();
         switch (code) {
            case 0:
            default:
               this.status = 1;
               break;
            case 33:
               code = this.read();
               switch (code) {
                  case 1:
                     this.skip();
                     continue;
                  case 249:
                     this.readGraphicControlExt();
                     continue;
                  case 254:
                     this.skip();
                     continue;
                  case 255:
                     this.readBlock();
                     String app = "";

                     for(int i = 0; i < 11; ++i) {
                        app = app + (char)this.block[i];
                     }

                     if (app.equals("NETSCAPE2.0")) {
                        this.readNetscapeExt();
                     } else {
                        this.skip();
                     }
                     continue;
                  default:
                     this.skip();
                     continue;
               }
            case 44:
               this.readBitmap();
               break;
            case 59:
               done = true;
         }
      }

   }

   protected void readGraphicControlExt() {
      this.read();
      int packed = this.read();
      this.dispose = (packed & 28) >> 2;
      if (this.dispose == 0) {
         this.dispose = 1;
      }

      this.transparency = (packed & 1) != 0;
      this.delay = this.readShort() * 10;
      this.transIndex = this.read();
      this.read();
   }

   protected void readHeader() {
      String id = "";

      for(int i = 0; i < 6; ++i) {
         id = id + (char)this.read();
      }

      if (!id.startsWith("GIF")) {
         this.status = 1;
      } else {
         this.readLSD();
         if (this.gctFlag && !this.err()) {
            this.gct = this.readColorTable(this.gctSize);
            this.bgColor = this.gct[this.bgIndex];
         }

      }
   }

   protected void readBitmap() {
      this.ix = this.readShort();
      this.iy = this.readShort();
      this.iw = this.readShort();
      this.ih = this.readShort();
      int packed = this.read();
      this.lctFlag = (packed & 128) != 0;
      this.lctSize = (int)Math.pow((double)2.0F, (double)((packed & 7) + 1));
      this.interlace = (packed & 64) != 0;
      if (this.lctFlag) {
         this.lct = this.readColorTable(this.lctSize);
         this.act = this.lct;
      } else {
         this.act = this.gct;
         if (this.bgIndex == this.transIndex) {
            this.bgColor = 0;
         }
      }

      int save = 0;
      if (this.transparency) {
         save = this.act[this.transIndex];
         this.act[this.transIndex] = 0;
      }

      if (this.act == null) {
         this.status = 1;
      }

      if (!this.err()) {
         this.decodeBitmapData();
         this.skip();
         if (!this.err()) {
            ++this.frameCount;
            this.image = new DixieMap(this.width, this.height, Format.RGBA8888);
            this.setPixels();
            this.frames.addElement(new GifFrame(this.image, this.delay));
            if (this.transparency) {
               this.act[this.transIndex] = save;
            }

            this.resetFrame();
         }
      }
   }

   protected void readLSD() {
      this.width = this.readShort();
      this.height = this.readShort();
      int packed = this.read();
      this.gctFlag = (packed & 128) != 0;
      this.gctSize = 2 << (packed & 7);
      this.bgIndex = this.read();
      this.pixelAspect = this.read();
   }

   protected void readNetscapeExt() {
      do {
         this.readBlock();
         if (this.block[0] == 1) {
            int b1 = this.block[1] & 255;
            int b2 = this.block[2] & 255;
            this.loopCount = b2 << 8 | b1;
         }
      } while(this.blockSize > 0 && !this.err());

   }

   protected int readShort() {
      return this.read() | this.read() << 8;
   }

   protected void resetFrame() {
      this.lastDispose = this.dispose;
      this.lrx = this.ix;
      this.lry = this.iy;
      this.lrw = this.iw;
      this.lrh = this.ih;
      this.lastPixmap = this.image;
      this.lastBgColor = this.bgColor;
      this.dispose = 0;
      this.transparency = false;
      this.delay = 0;
      this.lct = null;
   }

   protected void skip() {
      do {
         this.readBlock();
      } while(this.blockSize > 0 && !this.err());

   }

   public Animation<TextureRegion> getAnimation(Animation.PlayMode playMode) {
      int nrFrames = this.getFrameCount();
      Pixmap frame = this.getFrame(0);
      int width = frame.getWidth();
      int height = frame.getHeight();
      int vzones = (int)Math.sqrt((double)nrFrames);

      int hzones;
      for(hzones = vzones; vzones * hzones < nrFrames; ++vzones) {
      }

      Pixmap target = new Pixmap(width * hzones, height * vzones, Format.RGBA8888);

      for(int h = 0; h < hzones; ++h) {
         for(int v = 0; v < vzones; ++v) {
            int frameID = v + h * vzones;
            if (frameID < nrFrames) {
               Pixmap var15 = this.getFrame(frameID);
               target.drawPixmap(var15, h * width, v * height);
            }
         }
      }

      Texture texture = new Texture(target);
      Array<TextureRegion> texReg = new Array();

      for(int var17 = 0; var17 < hzones; ++var17) {
         for(int v = 0; v < vzones; ++v) {
            int frameID = v + var17 * vzones;
            if (frameID < nrFrames) {
               TextureRegion tr = new TextureRegion(texture, var17 * width, v * height, width, height);
               texReg.add(tr);
            }
         }
      }

      float frameDuration = (float)this.getDelay(0);
      frameDuration /= 1000.0F;
      Animation<TextureRegion> result = new Animation(frameDuration, texReg, playMode);
      return result;
   }

   public static Animation<TextureRegion> loadGIFAnimation(Animation.PlayMode playMode, InputStream is) {
      GifDecoder gdec = new GifDecoder();
      gdec.read(is);
      return gdec.getAnimation(playMode);
   }

   public static Animation<TextureRegion> loadGIFAnimation(Animation.PlayMode playMode, InputStream is, GifAnimation animation) {
      GifDecoder gdec = new GifDecoder();
      gdec.read(is);
      animation.gdec = gdec;
      return gdec.getAnimation(playMode);
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   private static class DixieMap extends Pixmap {
      DixieMap(int w, int h, Pixmap.Format f) {
         super(w, h, f);
      }

      DixieMap(int[] data, int w, int h, Pixmap.Format f) {
         super(w, h, f);

         for(int y = 0; y < h; ++y) {
            for(int x = 0; x < w; ++x) {
               int pxl_ARGB8888 = data[x + y * w];
               int pxl_RGBA8888 = pxl_ARGB8888 >> 24 & 255 | pxl_ARGB8888 << 8 & -256;
               this.drawPixel(x, y, pxl_RGBA8888);
            }
         }

      }

      void getPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
         ByteBuffer bb = this.getPixels();

         for(int k = y; k < y + height; ++k) {
            int _offset = offset;

            for(int l = x; l < x + width; ++l) {
               int pxl = bb.getInt(4 * (l + k * width));
               pixels[_offset++] = pxl >> 8 & 16777215 | pxl << 24 & -16777216;
            }

            offset += stride;
         }

      }
   }

   private static class GifFrame {
      public DixieMap image;
      public int delay;

      public GifFrame(DixieMap im, int del) {
         this.image = im;
         this.delay = del;
      }
   }
}
