package dcd_eng.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Vfx.FAR_SoundsAndAnimation;
import dcd_eng.Vfx.Kuuga_FAR_Background;
import dcd_eng.Vfx.Kuuga_Pegasus_FAR2;

public class PegasusAttack extends AbstractCustomCardWithType {
   public static final String ID = "PegasusAttack";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FinalAttackRide_Kuuga.png";
   private static final int COST = 0;
   private static final int ATTACK_DMG = 11;

   public PegasusAttack() {
      super("PegasusAttack", NAME, "img/cards/FinalAttackRide_Kuuga.png", 0, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.tags.add(DCDmod.RiderCard);
      this.baseDamage = 11;
      this.exhaust = true;
      this.setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("FAR", 0.0F);
      if (p.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new FAR_SoundsAndAnimation(p.drawX, p.drawY, "Kuuga", false), 3.88F));
      }

      if (!DCDmod.AnimationTrigger && p.hasPower("KamenRideKuugaPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new FAR_SoundsAndAnimation(p.drawX, p.drawY, "Kuuga", true), 3.88F));
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Pegasus_FAR2(p, m, this.damage)));
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_Background(false, false)));
      } else {
         for(int i = 0; i < 3; ++i) {
            for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
               if (!monster.isDead && !monster.isDying) {
                  AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.damage, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));
               }
            }
         }
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("RisingPegasusPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         if (p.hasPower("DecadeViolentEmotionPower")) {
            canUse = true;
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new PegasusAttack();
   }

   public void optionDecade() {
   }

   public void optionKuuga() {
   }

   public void optionAgito() {
   }

   public void optionRyuki() {
   }

   public void optionFaiz() {
   }

   public void optionBlade() {
   }

   public void optionHibiki() {
   }

   public void optionKabuto() {
   }

   public void optionDenO() {
   }

   public void optionKiva() {
   }

   public void optionNeutral() {
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
      }

   }

   public void update() {
      super.update();
      if (this.freeToPlayOnce) {
         this.setBannerTexture(DCDmod.FAR[0], DCDmod.FAR_P[0]);
      } else if (this.costForTurn == -1) {
         this.setBannerTexture(DCDmod.FAR[6], DCDmod.FAR_P[6]);
      } else {
         int cost = this.costForTurn;
         this.setBannerTexture(DCDmod.FAR[cost], DCDmod.FAR_P[cost]);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("PegasusAttack");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
