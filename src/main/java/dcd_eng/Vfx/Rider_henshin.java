package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.HenshinAction;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Patches.AbstractSummonedAnimation;

public class Rider_henshin extends AbstractGameEffect {
   private static String KR = "";
   private boolean start = true;
   private int stage;
   private int AnimationNumber;
   private String HENSHIN_CARD_ATLAS;
   private String HENSHIN_CARD_JSON;
   private String HENSHIN_P_ATLAS;
   private String HENSHIN_P_JSON;
   private String Sounds;

   public Rider_henshin(String KR, int AnimationNumber) {
      this.duration = 4.33F;
      this.startingDuration = 4.33F;
      this.stage = 0;
      Rider_henshin.KR = KR;
      this.AnimationNumber = AnimationNumber;
      switch (KR) {
         case "kuuga":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/kuuga/kuuga_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/kuuga/kuuga_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/kuuga/kuuga_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/kuuga/kuuga_henshin_P.json";
            this.Sounds = "kuuga_henshin";
            break;
         case "agito":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/agito/agito_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/agito/agito_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/agito/agito_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/agito/agito_henshin_P.json";
            this.Sounds = "agito_henshin";
            break;
         case "ryuki":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/ryuki/ryuki_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/ryuki/ryuki_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/ryuki/ryuki_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/ryuki/ryuki_henshin_P.json";
            this.Sounds = "ryuki_henshin";
            break;
         case "faiz":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/faiz/faiz_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/faiz/faiz_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/faiz/faiz_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/faiz/faiz_henshin_P.json";
            this.Sounds = "faiz_henshin";
            break;
         case "blade":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/blade/blade_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/blade/blade_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/blade/blade_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/blade/blade_henshin_P.json";
            this.Sounds = "blade_henshin";
            break;
         case "hibiki":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/hibiki/hibiki_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/hibiki/hibiki_henshin_P.json";
            this.Sounds = "hibiki_henshin";
            break;
         case "kabuto":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/kabuto/kabuto_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/kabuto/kabuto_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/kabuto/kabuto_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/kabuto/kabuto_henshin_P.json";
            this.Sounds = "kabuto_henshin";
            break;
         case "den-o":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/deno/deno_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/deno/deno_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/deno/deno_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/deno/deno_henshin_P.json";
            this.Sounds = "deno_henshin";
            break;
         case "kiva":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/kiva/kiva_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/kiva/kiva_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/kiva/kiva_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/kiva/kiva_henshin_P.json";
            this.Sounds = "kiva_henshin";
            break;
         case "decade":
            this.HENSHIN_CARD_ATLAS = "img/char/DCD_Animation/decade/decade_henshin_card.atlas";
            this.HENSHIN_CARD_JSON = "img/char/DCD_Animation/decade/decade_henshin_card.json";
            this.HENSHIN_P_ATLAS = "img/char/DCD_Animation/decade/decade_henshin_P.atlas";
            this.HENSHIN_P_JSON = "img/char/DCD_Animation/decade/decade_henshin_P.json";
            this.Sounds = "decade_henshin";
      }

   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.15F && this.stage == 0) {
         new AbstractSummonedAnimation("henshin_card", this.HENSHIN_CARD_ATLAS, this.HENSHIN_CARD_JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("henshin_card");
         VfxController.far_effect_S.setMovable(false);
         VfxController.far_effect_S.state.setAnimation(0, "card", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.46F && this.stage == 1) {
         String HENSHIN3_ATLAS = "img/char/DCD_Animation/decade/decade_henshin3.atlas";
         String HENSHIN3_JSON = "img/char/DCD_Animation/decade/decade_henshin3.json";
         new AbstractAnimation("henshin3", HENSHIN3_ATLAS, HENSHIN3_JSON, 1.0F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.far_effect2_A = AbstractAnimation.getAnimation("henshin3");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "henshin", false);
         CardCrawlGame.sound.playA("kamenride", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.38F && this.stage == 2) {
         CardCrawlGame.sound.playA(this.Sounds, 0.0F);
         new AbstractAnimation("henshin_P", this.HENSHIN_P_ATLAS, this.HENSHIN_P_JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.far_effect3_A = AbstractAnimation.getAnimation("henshin_P");
         VfxController.far_effect3_A.setMovable(false);
         VfxController.far_effect3_A.state.setAnimation(0, "P", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.7F && this.stage == 3) {
         AbstractDungeon.actionManager.addToTop(new HenshinAction(KR));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("henshin3");
         AbstractAnimation.clear("henshin_P");
         AbstractSummonedAnimation.clear("henshin_card");
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(this.AnimationNumber);
         this.start = false;
      }

   }

   public void dispose() {
      VfxController.far_effect_S = null;
      VfxController.far_effect2_A = null;
      VfxController.far_effect3_A = null;
   }
}
