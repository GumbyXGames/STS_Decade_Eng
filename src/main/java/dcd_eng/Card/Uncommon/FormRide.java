package dcd_eng.Card.Uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Common.FormRideBasshaa;
import dcd_eng.Card.Common.FormRideDogga;
import dcd_eng.Card.Common.FormRideDragon;
import dcd_eng.Card.Common.FormRideFlame;
import dcd_eng.Card.Common.FormRideGaruru;
import dcd_eng.Card.Common.FormRidePegasus;
import dcd_eng.Card.Common.FormRideStorm;
import dcd_eng.Card.Common.FormRideTitan;
import dcd_eng.Card.FormRide.Axel_s;
import dcd_eng.Card.FormRide.Basshaa_s;
import dcd_eng.Card.FormRide.Dogga_s;
import dcd_eng.Card.FormRide.Dragon_s;
import dcd_eng.Card.FormRide.Flame_s;
import dcd_eng.Card.FormRide.Garuru_s;
import dcd_eng.Card.FormRide.Jack_s;
import dcd_eng.Card.FormRide.Pegasus_s;
import dcd_eng.Card.FormRide.Storm_s;
import dcd_eng.Card.FormRide.Titan_s;
import dcd_eng.Card.Rare.FormRideAxel;
import dcd_eng.Card.Special.Kabuto_CastOff;
import dcd_eng.Card.Special.Kabuto_PutOn;
import dcd_eng.Card.Status.Kabuto_Photon;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.BladeJackPower;
import dcd_eng.Power.JackFlightPower;
import dcd_eng.Power.KabutoMaskedPower;
import dcd_eng.Vfx.Axel_faiztoaxel;
import dcd_eng.Vfx.Faiz_axelsounds;
import dcd_eng.Vfx.Jack_henshin;
import dcd_eng.Vfx.Kabuto_MaskedToRider;
import dcd_eng.Vfx.Kabuto_RiderToMasked;
import java.util.ArrayList;
import java.util.List;

public class FormRide extends AbstractCustomCardWithType {
   public static final String ID = "FormRide";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FormRide.png";
   private static final int COST = 2;
   private AbstractCard c = null;
   private CardGroup group;
   private boolean FormRideCard;
   private List<TooltipInfo> tips;

   public FormRide() {
      super("FormRide", NAME, "img/cards/FormRide.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.group = new CardGroup(CardGroupType.CARD_POOL);
      this.FormRideCard = false;
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[13], EXTENDED_DESCRIPTION[14]));
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      this.FormRideCard = true;
      if (p.hasPower("KamenRideDecadePower")) {
         this.group.addToBottom(new Dragon_s());
         this.group.addToBottom(new Pegasus_s());
         this.group.addToBottom(new Titan_s());
         this.group.addToBottom(new Flame_s());
         this.group.addToBottom(new Storm_s());
         this.group.addToBottom(new Axel_s());
         this.group.addToBottom(new Jack_s());
         this.group.addToBottom(new Garuru_s());
         this.group.addToBottom(new Basshaa_s());
         this.group.addToBottom(new Dogga_s());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张形态卡牌获得", false, false, true, false);
      } else if (p.hasPower("KamenRideKuugaPower")) {
         this.group.addToBottom(new Dragon_s());
         this.group.addToBottom(new Pegasus_s());
         this.group.addToBottom(new Titan_s());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1种形态切换", false, false, true, false);
      } else if (p.hasPower("KamenRideAgitoPower")) {
         this.group.addToBottom(new Flame_s());
         this.group.addToBottom(new Storm_s());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1种形态切换", false, false, true, false);
      } else if (p.hasPower("KamenRideFaizPower")) {
         CardCrawlGame.sound.playA("formride", 0.0F);
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_faiztoaxel(), 5.7F));
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_axelsounds(), 0.0F));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 3, false), 3));
         this.FormRideCard = false;
      } else if (p.hasPower("KamenRideBladePower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeJackPower(p), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new JackFlightPower(p), 1));
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Jack_henshin(p.drawX, p.drawY), 0.0F));
         this.FormRideCard = false;
      } else if (p.hasPower("KamenRideKabutoPower") && !p.hasPower("KabutoMaskedPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Kabuto_RiderToMasked(), 1.2F));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KabutoMaskedPower(p, 1), 1));
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_CastOff(), 1));
         KabutoMaskedPower.PhotonPoint = 0;
         this.FormRideCard = false;
      } else if (p.hasPower("KamenRideKabutoPower") && p.hasPower("KabutoMaskedPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Kabuto_MaskedToRider(), 1.4F));
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "KabutoMaskedPower"));
         int[] N = new int[]{0, 0, 0, 0, 0};

         for(AbstractCard c : p.hand.group) {
            switch (c.type) {
               case ATTACK:
                  int var14 = N[0]++;
                  break;
               case CURSE:
                  int var13 = N[1]++;
                  AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.hand, true));
                  break;
               case POWER:
                  int var12 = N[2]++;
                  break;
               case SKILL:
                  int var11 = N[3]++;
                  break;
               case STATUS:
                  int var10002 = N[4]++;
            }
         }

         int theSize = p.hand.size();
         AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, theSize, false));
         if (N[4] != 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, N[4]), N[4]));
         }

         if (N[3] != 0) {
            for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
               if (!monster.isDead && !monster.isDying) {
                  AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, N[3] * 2, DamageType.NORMAL), AttackEffect.SLASH_DIAGONAL));
               }
            }
         }

         if (N[2] != 0) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_PutOn(), 1));
         }

         if (N[1] != 0) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, N[1] * 3, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));

            for(int i = 0; i < 3; ++i) {
               AbstractCard c = new Kabuto_Photon();
               AbstractDungeon.player.hand.moveToDeck(c, true);
            }
         }

         if (N[0] != 0) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, N[0]));
         }

         this.FormRideCard = false;
      } else if (p.hasPower("KamenRideKivaPower")) {
         this.group.addToBottom(new Garuru_s());
         this.group.addToBottom(new Basshaa_s());
         this.group.addToBottom(new Dogga_s());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1种形态切换", false, false, true, false);
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideRyukiPower") && !p.hasPower("KamenRideHibikiPower") && !p.hasPower("KamenRideDenOPower")) {
            if (p.hasPower("KamenRideFaizPower") && SpecialFaizBox.AxelForm) {
               canUse = false;
               this.cantUseMessage = EXTENDED_DESCRIPTION[8];
            } else if (!p.hasPower("KamenRideDecadePower") && !p.hasPower("KamenRideKuugaPower") && !p.hasPower("KamenRideAgitoPower") && !p.hasPower("KamenRideFaizPower") && !p.hasPower("KamenRideBladePower") && !p.hasPower("KamenRideHibikiPower") && !p.hasPower("KamenRideKabutoPower") && !p.hasPower("KamenRideDenOPower") && !p.hasPower("KamenRideKivaPower")) {
               canUse = false;
               this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            } else if (p.hasPower("BladeJackPower")) {
               canUse = false;
               this.cantUseMessage = EXTENDED_DESCRIPTION[10];
            } else if (p.hasPower("KamenRideFaizPower") && SpecialFaizBox.FaizPoint < 10) {
               this.cantUseMessage = EXTENDED_DESCRIPTION[16];
               canUse = false;
            }
         } else {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[5];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new FormRide();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return AbstractDungeon.player instanceof Decade && AbstractDungeon.player.hasPower("KamenRideKabutoPower") && AbstractDungeon.player.hasPower("KabutoMaskedPower") ? this.tips : null;
   }

   public void optionDecade() {
      this.rawDescription = EXTENDED_DESCRIPTION[1];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
   }

   public void optionKuuga() {
      this.rawDescription = EXTENDED_DESCRIPTION[2];
      if (AbstractDungeon.player.hasPower("RisingMightyPower") || AbstractDungeon.player.hasPower("RisingPegasusPower") || AbstractDungeon.player.hasPower("RisingTitanPower") || AbstractDungeon.player.hasPower("RisingDragonPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[6];
      }

      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_kuuga.png", "img/1024/attack_kuuga.png");
   }

   public void optionAgito() {
      this.rawDescription = EXTENDED_DESCRIPTION[3];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_agito.png", "img/1024/attack_agito.png");
   }

   public void optionRyuki() {
      this.rawDescription = EXTENDED_DESCRIPTION[4];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_ryuki.png", "img/1024/attack_ryuki.png");
   }

   public void optionFaiz() {
      this.rawDescription = EXTENDED_DESCRIPTION[7];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
   }

   public void optionBlade() {
      this.rawDescription = EXTENDED_DESCRIPTION[9];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
   }

   public void optionHibiki() {
      this.rawDescription = EXTENDED_DESCRIPTION[4];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
   }

   public void optionKabuto() {
      if (AbstractDungeon.player.hasPower("KabutoMaskedPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[12];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[11];
      }

      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
   }

   public void optionDenO() {
      this.rawDescription = EXTENDED_DESCRIPTION[4];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
   }

   public void optionKiva() {
      this.rawDescription = EXTENDED_DESCRIPTION[15];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(1);
      }

   }

   public void update() {
      super.update();
      if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && this.FormRideCard) {
         switch (((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).cardID) {
            case "Dragon_s":
               this.c = new FormRideDragon();
               break;
            case "Pegasus_s":
               this.c = new FormRidePegasus();
               break;
            case "Titan_s":
               this.c = new FormRideTitan();
               break;
            case "Flame_s":
               this.c = new FormRideFlame();
               break;
            case "Storm_s":
               this.c = new FormRideStorm();
               break;
            case "Axel_s":
               this.c = new FormRideAxel();
               break;
            case "Jack_s":
               this.c = new FormRideJack();
               break;
            case "Garuru_s":
               this.c = new FormRideGaruru();
               break;
            case "Basshaa_s":
               this.c = new FormRideBasshaa();
               break;
            case "Dogga_s":
               this.c = new FormRideDogga();
         }

         if (AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
            this.c.freeToPlayOnce = true;
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.c, 1));
         } else {
            this.c.freeToPlayOnce = true;
            this.c.purgeOnUse = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this.c, (AbstractMonster)null, this.c.energyOnUse));
         }

         this.c = null;
         this.FormRideCard = false;
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         this.group.clear();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRide");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
