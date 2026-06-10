package dcd_eng.Orb;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Vfx.Faiz_gunattack;

public class FaizPhone extends CustomOrb {
   private static final String ORB_ID = "FaizPhone";
   private static final OrbStrings orbString;
   private static final String[] DESC;

   public FaizPhone() {
      super("FaizPhone", orbString.NAME, 1, 1, DESC[0], DESC[1], "img/orbs/SB-555P.png");
      this.evokeAmount = this.baseEvokeAmount;
      this.passiveAmount = this.basePassiveAmount;
      this.updateDescription();
   }

   public void updateDescription() {
      this.applyFocus();
      this.description = DESC[0] + DESC[1];
   }

   public void onEvoke() {
   }

   public void onEndOfTurn() {
      if (SpecialFaizBox.FaizPhone) {
         for(int i = 0; i < 3; ++i) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
            AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(AbstractDungeon.player, 3, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         }

         AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_gunattack(), 0.0F));
      }

   }

   public void render(SpriteBatch sb) {
      if (!SpecialFaizBox.FaizPhone && !this.name.equals("SB-555P: Faiz Phone (Off)")) {
         this.name = "SB-555P: Faiz Phone (Off)";
         this.img = ImageMaster.loadImage("img/orbs/SB-555P_N.png");
      }

      if (SpecialFaizBox.FaizPhone && !this.name.equals("SB-555P: Faiz Phone (On)")) {
         this.name = "SB-555P: Faiz Phone (On)";
         this.img = ImageMaster.loadImage("img/orbs/SB-555P.png");
      }

      sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));
      sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
      sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));
      sb.setBlendFunction(770, 1);
      sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, -this.angle, 0, 0, 96, 96, false, false);
      sb.setBlendFunction(770, 771);
      this.hb.render(sb);
   }

   public void triggerEvokeAnimation() {
   }

   public void playChannelSFX() {
   }

   public CustomOrb makeCopy() {
      return new FaizPhone();
   }

   static {
      orbString = CardCrawlGame.languagePack.getOrbString("FaizPhone");
      DESC = orbString.DESCRIPTION;
   }
}
