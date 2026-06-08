package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.HenshinAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Patches.AbstractSummonedAnimation;

public class Human_henshin extends AbstractGameEffect {
   private static String KR = "";
   private String Sounds;
   private boolean start = true;
   private int stage;
   private String HENSHIN_CARD_ATLAS;
   private String HENSHIN_CARD_JSON;
   private String HENSHIN_P_ATLAS;
   private String HENSHIN_P_JSON;

   public Human_henshin(String KR) {
      this.duration = 3.0F;
      this.startingDuration = 3.0F;
      this.stage = 0;
      Human_henshin.KR = KR;
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
      if (this.duration < this.startingDuration - 0.04F && this.stage == 0) {
         new AbstractSummonedAnimation("decade_henshin_card", this.HENSHIN_CARD_ATLAS, this.HENSHIN_CARD_JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("decade_henshin_card");
         VfxController.far_effect_S.setMovable(false);
         VfxController.far_effect_S.state.setAnimation(0, "card", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.36F && this.stage == 1) {
         CardCrawlGame.sound.playA("people_henshin", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.48F && this.stage == 2) {
         if (!KR.equals("decade")) {
            CardCrawlGame.sound.playA("kamenride", 0.0F);
         }

         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.87F && this.stage == 3) {
         float y = AbstractDungeon.player.drawY;
         if (!KR.equals("decade")) {
            y -= 5.0F;
            CardCrawlGame.sound.playA(this.Sounds, 0.0F);
         }

         new AbstractAnimation("decade_henshin_P", this.HENSHIN_P_ATLAS, this.HENSHIN_P_JSON, 0.8F, AbstractDungeon.player.drawX, y, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("decade_henshin_P");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "P", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.1F && this.stage == 4) {
         AbstractDungeon.actionManager.addToTop(new HenshinAction(KR));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("decade_henshin_P");
         AbstractSummonedAnimation.clear("decade_henshin_card");
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         TurnTimer.StopBGM(false);
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(2);
         this.start = false;
      }

   }

   public void dispose() {
      VfxController.Henshin_A = null;
      VfxController.far_effect_S = null;
   }
}
