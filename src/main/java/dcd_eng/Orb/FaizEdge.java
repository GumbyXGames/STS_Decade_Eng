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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Power.Flex1;
import dcd_eng.Power.Flex2;

public class FaizEdge extends CustomOrb {
   public static final String ORB_ID = "FaizEdge";
   private static final OrbStrings orbString;
   public static final String[] DESC;

   public FaizEdge() {
      super("FaizEdge", orbString.NAME, 1, 1, DESC[0], DESC[1], "img/orbs/SB-555H.png");
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
      if (SpecialFaizBox.FaizEdge) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Flex1(AbstractDungeon.player, 1), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Flex2(AbstractDungeon.player, 1), 1));
      }

   }

   public void render(SpriteBatch sb) {
      if (!SpecialFaizBox.FaizEdge && !this.name.equals("SB-555H: Faiz Edge (Off)")) {
         this.name = "SB-555H: Faiz Edge (Off)";
         this.img = ImageMaster.loadImage("img/orbs/SB-555H_N.png");
      }

      if (SpecialFaizBox.FaizEdge && !this.name.equals("SB-555H: Faiz Edge (On)")) {
         this.name = "SB-555H: Faiz Edge (On)";
         this.img = ImageMaster.loadImage("img/orbs/SB-555H.png");
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
      return new FaizEdge();
   }

   static {
      orbString = CardCrawlGame.languagePack.getOrbString("FaizEdge");
      DESC = orbString.DESCRIPTION;
   }
}
