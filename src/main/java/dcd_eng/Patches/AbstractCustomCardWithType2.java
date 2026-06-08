package dcd_eng.Patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import dcd_eng.DCDmod;
import dcd_eng.Actions.ForceIntentAction;
import dcd_eng.Vfx.ForceIntentChangePreviewEffect;
import java.util.ArrayList;

public abstract class AbstractCustomCardWithType2 extends AbstractGIFCard {
   private CardColorType colorType;
   private boolean willApplyPowers;
   private IntentTypes intentType;
   private ArrayList<EnemyMoveInfo> enemyMoves;
   private EnemyMoveInfo move;
   private AbstractMonster newTarget;
   private boolean intentRevert;

   private AbstractCustomCardWithType2(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, CardColorType damageType, boolean willApplyPowers, IntentTypes intentType) {
      super(id, name, img, cost, rawDescription, type, color, rarity, target);
      this.enemyMoves = new ArrayList();
      this.intentRevert = false;
      this.intentType = intentType;
      this.willApplyPowers = willApplyPowers;
      this.colorType = damageType;
      this.assignOrbTexture();
      this.assignBGTexture();
      this.assignBannerTexture();
   }

   public AbstractCustomCardWithType2(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, CardColorType damageType, IntentTypes intentType) {
      this(id, name, img, cost, rawDescription, type, color, rarity, target, damageType, true, intentType);
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
            break;
         case Agito:
            indexPointer = 2;
            break;
         case Ryuki:
            indexPointer = 3;
            break;
         case Faiz:
            indexPointer = 4;
            break;
         case Blade:
            indexPointer = 5;
            break;
         case Hibiki:
            indexPointer = 6;
            break;
         case Kabuto:
            indexPointer = 7;
            break;
         case DenO:
            indexPointer = 8;
            break;
         case Kiva:
            indexPointer = 9;
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
            if (this.colorType == AbstractCustomCardWithType2.CardColorType.Decade) {
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

            if (modifier != 0) {
               this.isDamageModified = true;
            }

            this.damage += modifier;
            if (this.isMultiDamage) {
               for(int i = 0; i < this.multiDamage.length; ++i) {
                  int[] var10000 = this.multiDamage;
                  var10000[i] += modifier;
               }
            }
         }

         if (this.hasTag(DCDmod.IntentCard)) {
            if (this.target == CardTarget.ALL_ENEMY) {
               if (this.newTarget == null) {
                  for(Object monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                     AbstractMonster m = (AbstractMonster)monster;
                     this.enemyMoves.add((EnemyMoveInfo)ReflectionHacks.getPrivate(m, AbstractMonster.class, "move"));
                     if (!m.isDeadOrEscaped() && !m.halfDead) {
                        this.newTarget = m;
                        int counter = AbstractDungeon.aiRng.counter;
                        long seed0 = (Long)ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, RandomXS128.class, "seed0");
                        long seed2 = (Long)ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, RandomXS128.class, "seed1");
                        ForceIntentAction.previewNewIntent(this.newTarget, this.intentType);
                        AbstractDungeon.aiRng.counter = counter;
                        AbstractDungeon.aiRng.random.setState(seed0, seed2);
                     }
                  }
               }
            } else if (this.target == CardTarget.ENEMY && arg0 != null && this.newTarget == null) {
               this.newTarget = arg0;
               this.move = (EnemyMoveInfo)ReflectionHacks.getPrivate(arg0, AbstractMonster.class, "move");
               int counter2 = AbstractDungeon.aiRng.counter;
               long seed3 = (Long)ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, RandomXS128.class, "seed0");
               long seed4 = (Long)ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, RandomXS128.class, "seed1");
               ForceIntentAction.previewNewIntent(this.newTarget, this.intentType);
               AbstractDungeon.aiRng.counter = counter2;
               AbstractDungeon.aiRng.random.setState(seed3, seed4);
            }

            this.intentRevert = false;
         }

      }
   }

   public abstract void energychange();

   public void update() {
      super.update();
      this.energychange();
      if (this.hasTag(DCDmod.IntentCard)) {
         if (this.target == CardTarget.ALL_ENEMY) {
            if (this.newTarget != null && !this.intentRevert) {
               for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                  if (!m.isDeadOrEscaped() && !m.halfDead) {
                     this.newTarget = m;
                     AbstractDungeon.effectsQueue.add(new ForceIntentChangePreviewEffect(this.newTarget.intentHb.cX, this.newTarget.intentHb.cY, 0.75F, 1.75F));
                  }
               }
            }
         } else if (this.target == CardTarget.ENEMY && this.newTarget != null && !this.intentRevert) {
            AbstractDungeon.effectsQueue.add(new ForceIntentChangePreviewEffect(this.newTarget.intentHb.cX, this.newTarget.intentHb.cY, 0.75F, 1.75F));
         }

         if (this.target != CardTarget.ALL_ENEMY) {
            if (this.target == CardTarget.ENEMY && this.newTarget != null && this.intentRevert) {
               this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
               if (this.newTarget.moveHistory.size() > 0) {
                  this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
               }

               this.newTarget.setMove(this.move.nextMove, this.move.intent, this.move.baseDamage, this.move.multiplier, this.move.isMultiDamage);
               this.newTarget.createIntent();
               this.newTarget = null;
               this.move = null;
            }
         } else if (this.newTarget != null && this.intentRevert) {
            ArrayList monsters = AbstractDungeon.getCurrRoom().monsters.monsters;

            for(int i = 0; i < monsters.size(); ++i) {
               AbstractMonster j = (AbstractMonster)monsters.get(i);
               if (!j.isDeadOrEscaped() && !j.halfDead) {
                  this.newTarget = j;
                  this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
                  if (this.newTarget.moveHistory.size() > 0) {
                     this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
                  }

                  EnemyMoveInfo move = (EnemyMoveInfo)this.enemyMoves.get(i);
                  this.newTarget.setMove(move.nextMove, move.intent, move.baseDamage, move.multiplier, move.isMultiDamage);
                  this.newTarget.createIntent();
                  this.newTarget = null;
               }
            }

            this.enemyMoves.clear();
         }

         this.intentRevert = true;
      }

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

   public static enum IntentTypes {
      ATTACK;
   }
}
