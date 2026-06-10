package dcd_eng.Orb;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Power.Flex1;

public class FaizShot extends CustomOrb {
   public static final String ORB_ID = "FaizShot";
   private static final OrbStrings orbString;
   public static final String[] DESC;

   public FaizShot() {
      super("FaizShot", orbString.NAME, 1, 1, DESC[0], DESC[1], "img/orbs/SB-555C.png");
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

   public void onStartOfTurn() {
      if (SpecialFaizBox.FaizShot) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Flex1(AbstractDungeon.player, 2), 2));
      }

   }

   public void render(SpriteBatch sb) {
      if (!SpecialFaizBox.FaizShot && !this.name.equals("SB-555C: Faiz Shot (Off)")) {
         this.name = "SB-555C: Faiz Shot (Off)";
         this.img = ImageMaster.loadImage("img/orbs/SB-555C_N.png");
      }

      if (SpecialFaizBox.FaizShot && !this.name.equals("SB-555C: Faiz Shot (On)")) {
         this.name = "SB-555C: Faiz Shot (On)";
         this.img = ImageMaster.loadImage("img/orbs/SB-555C.png");
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
      return new FaizShot();
   }

   static {
      orbString = CardCrawlGame.languagePack.getOrbString("FaizShot");
      DESC = orbString.DESCRIPTION;
   }
}
