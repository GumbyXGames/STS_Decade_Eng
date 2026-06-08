package dcd_eng.Patches;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;

public abstract class AbstractCustomCardWithType extends AbstractGIFCard {
   private CardColorType colorType;
   private boolean willApplyPowers;

   public AbstractCustomCardWithType(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, CardColorType damageType, boolean willApplyPowers) {
      super(id, name, img, cost, rawDescription, type, color, rarity, target);
      this.willApplyPowers = willApplyPowers;
      this.colorType = damageType;
      this.assignOrbTexture();
      this.assignBGTexture();
      this.assignBannerTexture();
   }

   public AbstractCustomCardWithType(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, CardColorType damageType) {
      this(id, name, img, cost, rawDescription, type, color, rarity, target, damageType, true);
   }

   private void assignBannerTexture() {
      switch (this.rarity) {
         case BASIC:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.BASIC[0], DCDmod.BASIC_P[0]);
            } else if (this.cost == -1) {
               this.setBannerTexture(DCDmod.BASIC[6], DCDmod.BASIC_P[6]);
            } else {
               int cost = this.cost;
               this.setBannerTexture(DCDmod.BASIC[cost], DCDmod.BASIC_P[cost]);
            }

            if (this.costForTurn == -1) {
               this.setBannerTexture(DCDmod.BASIC[6], DCDmod.BASIC_P[6]);
            } else {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.BASIC[cost], DCDmod.BASIC_P[cost]);
            }
            break;
         case COMMON:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
            } else if (this.cost == -1) {
               this.setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
            } else {
               int cost = this.cost;
               this.setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
            }

            if (this.costForTurn == -1) {
               this.setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
            } else {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
            }
            break;
         case RARE:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.RARE[0], DCDmod.RARE_P[0]);
            } else if (this.cost == -1) {
               this.setBannerTexture(DCDmod.RARE[6], DCDmod.RARE_P[6]);
            } else {
               int cost = this.cost;
               this.setBannerTexture(DCDmod.RARE[cost], DCDmod.RARE_P[cost]);
            }

            if (this.costForTurn == -1) {
               this.setBannerTexture(DCDmod.RARE[6], DCDmod.RARE_P[6]);
            } else {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.RARE[cost], DCDmod.RARE_P[cost]);
            }
            break;
         case SPECIAL:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.SPECIAL[0], DCDmod.SPECIAL_P[0]);
            } else if (this.cost == -1) {
               this.setBannerTexture(DCDmod.SPECIAL[6], DCDmod.SPECIAL_P[6]);
            } else {
               int cost = this.cost;
               this.setBannerTexture(DCDmod.SPECIAL[cost], DCDmod.SPECIAL_P[cost]);
            }

            if (this.costForTurn == -1) {
               this.setBannerTexture(DCDmod.SPECIAL[6], DCDmod.SPECIAL_P[6]);
            } else {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.SPECIAL[cost], DCDmod.SPECIAL_P[cost]);
            }
            break;
         case UNCOMMON:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
            } else if (this.cost == -1) {
               this.setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
            } else {
               int cost = this.cost;
               this.setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
            }

            if (this.costForTurn == -1) {
               this.setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
            } else {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
            }
      }

      if (this.cardID.equals("FinalAttackRide")) {
         if (this.freeToPlayOnce) {
            this.setBannerTexture(DCDmod.FAR[0], DCDmod.FAR_P[0]);
         } else if (this.cost == -1) {
            this.setBannerTexture(DCDmod.FAR[6], DCDmod.FAR_P[6]);
         } else {
            int cost = this.cost;
            this.setBannerTexture(DCDmod.FAR[cost], DCDmod.FAR_P[cost]);
         }

         if (this.costForTurn == -1) {
            this.setBannerTexture(DCDmod.FAR[6], DCDmod.FAR_P[6]);
         } else {
            int cost = this.costForTurn;
            this.setBannerTexture(DCDmod.FAR[cost], DCDmod.FAR_P[cost]);
         }
      }

   }

   private void assignOrbTexture() {
      switch (this.colorType) {
         case Decade:
         case Kuuga:
         case Agito:
         case Ryuki:
         case Faiz:
         case Blade:
         case Hibiki:
         case Kabuto:
         case DenO:
         case Kiva:
            this.setOrbTexture("img/512/orb-dark.png", "img/1024/orb-dark.png");
         default:
      }
   }

   private void assignBGTexture() {
      String[] portraitListPointer = null;
      String[] textureListPointer = null;
      switch (this.type) {
         case ATTACK:
            textureListPointer = DCDmod.ATTACK_BG;
            portraitListPointer = DCDmod.ATTACK_BG_P;
            break;
         case SKILL:
            textureListPointer = DCDmod.SKILL_BG;
            portraitListPointer = DCDmod.SKILL_BG_P;
            break;
         case POWER:
            textureListPointer = DCDmod.POWER_BG;
            portraitListPointer = DCDmod.POWER_BG_P;
      }

      int indexPointer;
      switch (this.colorType) {
         case Kuuga:
            indexPointer = 1;
            this.tags.add(DCDmod.KuugaCard);
            break;
         case Agito:
            indexPointer = 2;
            this.tags.add(DCDmod.AgitoCard);
            break;
         case Ryuki:
            indexPointer = 3;
            this.tags.add(DCDmod.RyukiCard);
            break;
         case Faiz:
            indexPointer = 4;
            this.tags.add(DCDmod.FaizCard);
            break;
         case Blade:
            indexPointer = 5;
            this.tags.add(DCDmod.BladeCard);
            break;
         case Hibiki:
            indexPointer = 6;
            this.tags.add(DCDmod.HibikiCard);
            break;
         case Kabuto:
            indexPointer = 7;
            this.tags.add(DCDmod.KabutoCard);
            break;
         case DenO:
            indexPointer = 8;
            this.tags.add(DCDmod.DenOCard);
            break;
         case Kiva:
            indexPointer = 9;
            this.tags.add(DCDmod.KivaCard);
            break;
         default:
            indexPointer = 0;
      }

      assert textureListPointer != null;

      this.setBackgroundTexture(textureListPointer[indexPointer], portraitListPointer[indexPointer]);
   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      int modifier = 0;
      if (this.willApplyPowers) {
         if (this.damage != 0) {
            if (this.colorType == AbstractCustomCardWithType.CardColorType.Decade) {
               if (AbstractDungeon.player.hasPower("BladeThunderPower") && !this.cardID.equals("FinalAttackRide")) {
                  modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
               }
            } else if (AbstractDungeon.player.hasPower("BladeThunderPower")) {
               modifier += AbstractDungeon.player.getPower("BladeThunderPower").amount;
            }

            if (AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
               modifier += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
            }

            if (this.hasTag(DCDmod.UnarmedCard) && AbstractDungeon.player.hasPower("KamenRideKuugaPower") && !AbstractDungeon.player.hasPower("KuugaDragonPower") && !AbstractDungeon.player.hasPower("RisingDragonPower") && !AbstractDungeon.player.hasPower("KuugaTitanPower") && !AbstractDungeon.player.hasPower("RisingTitanPower") && !AbstractDungeon.player.hasPower("KuugaPegasusPower") && !AbstractDungeon.player.hasPower("RisingPegasusPower")) {
               modifier += 2;
            }

            if (this.hasTag(DCDmod.UnarmedCard) && AbstractDungeon.player.hasPower("KamenRideKivaPower")) {
               modifier += 2;
            }

            this.damage += modifier;
            if (modifier != 0) {
               this.isDamageModified = true;
            }

            if (this.isMultiDamage) {
               for(int i = 0; i < this.multiDamage.length; ++i) {
                  int[] var10000 = this.multiDamage;
                  var10000[i] += modifier;
               }
            }
         }

      }
   }

   public void EnergyChange(AbstractCard.CardRarity rarity) {
      switch (rarity) {
         case BASIC:
            if (!this.cardID.equals("FinalAttackRide")) {
               if (this.freeToPlayOnce) {
                  this.setBannerTexture(DCDmod.BASIC[0], DCDmod.BASIC_P[0]);
               } else if (this.costForTurn != -1 && this.costForTurn <= 5) {
                  int cost = this.costForTurn;
                  this.setBannerTexture(DCDmod.BASIC[cost], DCDmod.BASIC_P[cost]);
               } else {
                  this.setBannerTexture(DCDmod.BASIC[6], DCDmod.BASIC_P[6]);
               }
            }
            break;
         case COMMON:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
            } else if (this.costForTurn != -1 && this.costForTurn <= 5) {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
            } else {
               this.setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
            }
            break;
         case RARE:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.RARE[0], DCDmod.RARE_P[0]);
            } else if (this.costForTurn != -1 && this.costForTurn <= 5) {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.RARE[cost], DCDmod.RARE_P[cost]);
            } else {
               this.setBannerTexture(DCDmod.RARE[6], DCDmod.RARE_P[6]);
            }
            break;
         case SPECIAL:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.SPECIAL[0], DCDmod.SPECIAL_P[0]);
            } else if (this.costForTurn != -1 && this.costForTurn <= 5) {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.SPECIAL[cost], DCDmod.SPECIAL_P[cost]);
            } else {
               this.setBannerTexture(DCDmod.SPECIAL[6], DCDmod.SPECIAL_P[6]);
            }
            break;
         case UNCOMMON:
            if (this.freeToPlayOnce) {
               this.setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
            } else if (this.costForTurn != -1 && this.costForTurn <= 5) {
               int cost = this.costForTurn;
               this.setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
            } else {
               this.setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
            }
      }

   }

   public void update() {
      super.update();
      this.EnergyChange(this.rarity);
   }

   public abstract void optionDecade();

   public abstract void optionKuuga();

   public abstract void optionAgito();

   public abstract void optionRyuki();

   public abstract void optionFaiz();

   public abstract void optionBlade();

   public abstract void optionHibiki();

   public abstract void optionKabuto();

   public abstract void optionDenO();

   public abstract void optionKiva();

   public abstract void optionNeutral();

   public void applyPowers() {
      super.applyPowers();
      if (AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
         this.optionDecade();
      } else if (AbstractDungeon.player.hasPower("KamenRideKuugaPower")) {
         this.optionKuuga();
      } else if (AbstractDungeon.player.hasPower("KamenRideAgitoPower")) {
         this.optionAgito();
      } else if (AbstractDungeon.player.hasPower("KamenRideRyukiPower")) {
         this.optionRyuki();
      } else if (AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
         this.optionFaiz();
      } else if (AbstractDungeon.player.hasPower("KamenRideBladePower")) {
         this.optionBlade();
      } else if (AbstractDungeon.player.hasPower("KamenRideHibikiPower")) {
         this.optionHibiki();
      } else if (AbstractDungeon.player.hasPower("KamenRideKabutoPower")) {
         this.optionKabuto();
      } else if (AbstractDungeon.player.hasPower("KamenRideDenOPower")) {
         this.optionDenO();
      } else if (AbstractDungeon.player.hasPower("KamenRideKivaPower")) {
         this.optionKiva();
      } else {
         this.optionNeutral();
      }

   }

   public static enum CardColorType {
      Decade,
      Kuuga,
      Agito,
      Ryuki,
      Faiz,
      Blade,
      Hibiki,
      Kabuto,
      DenO,
      Kiva;
   }
}
