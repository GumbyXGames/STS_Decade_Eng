package dcd_eng.Characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.neow.NeowRoom;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.DecadeAnimationAction;
import dcd_eng.Actions.NewRideBookerCardAction;
import dcd_eng.Actions.ReturnRandomNumberAction2;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Actions.UseCardAction;
import dcd_eng.Card.Common.KamenRideDecade;
import dcd_eng.Card.Rare.TimeVent;
import dcd_eng.Card.Uncommon.PrometheusPower;
import dcd_eng.Effect.FREffect;
import dcd_eng.Effect.HungryBasshaaEffect;
import dcd_eng.Effect.HungryGaruruEffect;
import dcd_eng.Effect.LightningEffect;
import dcd_eng.Effect.RisingEffect;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Helper.SpecialHibikiTaiko;
import dcd_eng.Helper.SpecialHibikiTaikoScore;
import dcd_eng.Helper.SpecialKivaPowerMeter;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AnimationLoader;
import dcd_eng.Patches.CharacterEnum;
import dcd_eng.Patches.GetButtonSoundKey;
import dcd_eng.Patches.GetCharacterPicture;
import dcd_eng.Patches.HibikiTaikoKeyEvent;
import dcd_eng.Patches.ModBaseClassForSLExample;
import dcd_eng.Power.AgitoLevelPower;
import dcd_eng.Power.KamenRideDenOPower;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class Decade extends CustomPlayer {
   private static final int ENERGY_PER_TURN = 3;
   private static final String[] orbTextures = new String[]{"img/character/DCD-orb/enable/layer1.png", "img/character/DCD-orb/enable/layer2.png", "img/character/DCD-orb/enable/layer3.png", "img/character/DCD-orb/enable/layer4.png", "img/character/DCD-orb/enable/layer5.png", "img/character/DCD-orb/enable/layer6.png", "img/character/DCD-orb/disable/layer1d.png", "img/character/DCD-orb/disable/layer2d.png", "img/character/DCD-orb/disable/layer3d.png", "img/character/DCD-orb/disable/layer4d.png", "img/character/DCD-orb/disable/layer5d.png"};
   public static int cf;
   public static String KamenRide = "human";
   private static final String FAR0_ATLAS = "img/char/DCD_Animation/FAR/FAR0.atlas";
   private static final String FAR1_ATLAS = "img/char/DCD_Animation/FAR/FAR1.atlas";
   private static final String FAR2_ATLAS = "img/char/DCD_Animation/FAR/FAR2.atlas";
   private static final String FAR0_JSON = "img/char/DCD_Animation/FAR/FAR0.json";
   private static final String FAR1_JSON = "img/char/DCD_Animation/FAR/FAR1.json";
   private static final String FAR2_JSON = "img/char/DCD_Animation/FAR/FAR2.json";
   public static AnimationLoader FAR0 = new AnimationLoader("img/char/DCD_Animation/FAR/FAR0.atlas", "img/char/DCD_Animation/FAR/FAR0.json", 1.0F);
   public static AnimationLoader FAR1 = new AnimationLoader("img/char/DCD_Animation/FAR/FAR1.atlas", "img/char/DCD_Animation/FAR/FAR1.json", 1.0F);
   public static AnimationLoader FAR2 = new AnimationLoader("img/char/DCD_Animation/FAR/FAR2.atlas", "img/char/DCD_Animation/FAR/FAR2.json", 1.0F);
   SoftReference<Object> softRef1;
   SoftReference<Object> softRef2;
   SoftReference<Object> softRef3;
   private static final int[] ban = new int[]{13, 15, 19, 20, 21, 22, 24, 25, 26, 27, 31, 32, 33, 36, 38, 42, 51, 52, 53, 56, 57, 58, 71, 72, 73, 74, 76, 77, 78, 79, 80, 81, 82, 83, 84, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 166, 167, 168, 169, 170, 171, 172, 173, 174};
   private static final int[] defend = new int[]{77, 81, 87, 88, 115, 170};
   float i;
   float i2;
   public static boolean removeTimeVent = false;

   public Decade(String name) {
      super(name, CharacterEnum.Decade, orbTextures, "img/character/DCD-orb/vfx.png", (String)null, (String)null);
      this.softRef1 = new SoftReference(FAR0);
      this.softRef2 = new SoftReference(FAR1);
      this.softRef3 = new SoftReference(FAR2);
      cf = 0;
      this.dialogX = this.drawX + 0.0F * Settings.scale;
      this.dialogY = this.drawY + 220.0F * Settings.scale;
      this.initializeClass(GetCharacterPicture.CharacterPicture(), DCDmod.makePath("char/DCD/decade_shoulder.png"), DCDmod.makePath("char/DCD/decade_shoulder.png"), DCDmod.makePath("char/DCD/corpse.png"), this.getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
   }

   public void update() {
      super.update();
      if (KamenRideDenOPower.retain) {
         for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!c.retain) {
               c.retain = true;
            }
         }
      }

      if (AbstractDungeon.getCurrRoom() instanceof NeowRoom) {
         ModBaseClassForSLExample.timevent = false;
      }

      if ((AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof MonsterRoom) && AbstractDungeon.player != null && !AbstractDungeon.player.isDead && removeTimeVent) {
         for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals("TimeVent") && !c.upgraded && !TimeVent.TimeVentUpgraded) {
               AbstractDungeon.player.discardPile.removeCard(c);
               TimeVent.TimeVentUpgraded = true;
               removeTimeVent = false;
               break;
            }
         }
      }

      if (!DCDmod.AnimationTrigger && !TurnTimer.BattleEnd) {
         if (this.hasPower("RisingMightyPower") || this.hasPower("RisingPegasusPower") || this.hasPower("RisingDragonPower") || this.hasPower("RisingTitanPower")) {
            this.i -= Gdx.graphics.getDeltaTime();
            if (this.i < 0.0F) {
               this.i = 0.05F;
               AbstractDungeon.effectsQueue.add(new RisingEffect());
               if (this.hasPower("RisingMightyPower") && (this.hasPower("RisingPegasusPower") || this.hasPower("RisingDragonPower") || this.hasPower("RisingTitanPower"))) {
                  AbstractDungeon.effectsQueue.add(new LightningEffect());
               }
            }
         }

         if (this.hasPower("KamenRideKuugaPower") || this.hasPower("KamenRideKivaPower")) {
            this.i2 -= Gdx.graphics.getDeltaTime();
            if (this.i2 < 0.0F) {
               this.i2 = MathUtils.random(0.3F, 0.4F);
               if (this.hasPower("RisingMightyPower") && !this.hasPower("KuugaPegasusPower") && !this.hasPower("KuugaDragonPower") && !this.hasPower("KuugaTitanPower") && !this.hasPower("RisingPegasusPower") && !this.hasPower("RisingDragonPower") && !this.hasPower("RisingTitanPower")) {
                  AbstractDungeon.effectsQueue.add(new FREffect("RED"));
               } else if (!this.hasPower("KuugaDragonPower") && !this.hasPower("RisingDragonPower") && !this.hasPower("KivaGaruruPower")) {
                  if (!this.hasPower("KuugaPegasusPower") && !this.hasPower("RisingPegasusPower") && !this.hasPower("KivaBasshaaPower")) {
                     if (this.hasPower("KuugaTitanPower") || this.hasPower("RisingTitanPower") || this.hasPower("KivaDoggaPower")) {
                        AbstractDungeon.effectsQueue.add(new FREffect("PURPLE"));
                     }
                  } else {
                     AbstractDungeon.effectsQueue.add(new FREffect("GREEN"));
                  }
               } else {
                  AbstractDungeon.effectsQueue.add(new FREffect("BLUE"));
               }
            }
         }

         if (this.hasPower("KamenRideKivaPower") && this.hasPower("KivaHungry3Power")) {
            this.i -= Gdx.graphics.getDeltaTime();
            if (this.i < 0.0F) {
               if (this.hasPower("KivaGaruruPower")) {
                  AbstractDungeon.effectsQueue.add(new HungryGaruruEffect());
                  this.i = 0.05F;
               } else if (this.hasPower("KivaBasshaaPower")) {
                  AbstractDungeon.effectsQueue.add(new HungryBasshaaEffect());
                  this.i = 0.05F;
               } else if (this.hasPower("KivaDoggaPower")) {
                  AbstractDungeon.effectsQueue.add(new DivinityParticleEffect());
                  this.i = 0.2F;
               }
            }
         }
      }

      if (InputHelper.justReleasedClickRight && this.hb.hovered && this.hasPower("AgitoLevelPower") && this.getPower("AgitoLevelPower").amount >= 4) {
         AgitoLevelPower.Lv -= 4;
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this, this, "AgitoLevelPower", 4));
         AbstractDungeon.actionManager.addToBottom(new UseCardAction(new PrometheusPower(), this));
      }

   }

   public void Trickster(int a) {
      boolean banAnimation = false;
      boolean isDefend = false;
      boolean loop = false;
      System.out.println("模型" + cf);
      if (a == cf) {
         System.out.println("==a");
      } else if (TurnTimer.BattleEnd && cf == 3) {
         System.out.println("==3");
      } else {
         if (DCDmod.AnimationTrigger) {
            for(int value : ban) {
               if (a == value) {
                  banAnimation = true;
                  break;
               }
            }
         } else {
            for(int value : defend) {
               if (a == value) {
                  isDefend = true;
                  break;
               }
            }

            if (isDefend) {
               for(int value : ban) {
                  if (cf == value) {
                     banAnimation = true;
                     break;
                  }
               }
            }
         }

         if (banAnimation) {
            System.out.println("==ban");
         } else {
            cf = a;
            String AnimationName = DecadeAnimationAction.NAME[a];
            if (AnimationName.equals("normal") || AnimationName.equals("normal_p") || AnimationName.equals("normal_t") || AnimationName.equals("normal_tp") || AnimationName.equals("Dragon") || AnimationName.equals("Pegasus") || AnimationName.equals("Titan")) {
               loop = true;
            }

            this.img.dispose();
            this.loadAnimation(DecadeAnimationAction.ATLAS[a], DecadeAnimationAction.JSON[a], 0.8F);
            this.state.setAnimation(0, AnimationName, loop);
            switch (a) {
               case 1:
               case 2:
               case 6:
               case 10:
               case 11:
               case 23:
                  KamenRide = "Decade";
                  if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
                     SpecialRideBooker.kamenpowerpoint = SpecialRideBooker.kamenpower_decadeVE_point;
                  }

                  SpecialRideBooker.nodecade = false;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/decade_down.png"));
                  break;
               case 3:
                  KamenRide = "human";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/corpse.png"));
                  break;
               case 4:
                  KamenRide = "Kuuga";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/kuuga_down.png"));
                  break;
               case 14:
                  KamenRide = "Agito";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/agito_down.png"));
                  break;
               case 30:
                  KamenRide = "Ryuki";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/ryuki_down.png"));
                  break;
               case 34:
                  KamenRide = "Faiz";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/faiz_down.png"));
                  break;
               case 37:
               case 47:
                  KamenRide = "Decade";
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/decade_down.png"));
                  break;
               case 43:
                  SpecialRideBooker.nodecade = true;
                  break;
               case 45:
                  KamenRide = "Blade";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/blade_down.png"));
                  break;
               case 46:
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  break;
               case 49:
                  KamenRide = "Hibiki";
                  SpecialRideBooker.nodecade = true;
                  HibikiTaikoKeyEvent.TaikoTrigger = true;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/hibiki_down.png"));
                  break;
               case 60:
                  KamenRide = "Kabuto";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  this.corpseImg.dispose();
                  this.corpseImg = new Texture(Gdx.files.internal("img/char/DCD/kabuto_down.png"));
                  break;
               case 66:
                  KamenRide = "DenO";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
                  break;
               case 176:
                  KamenRide = "Kiva";
                  SpecialRideBooker.nodecade = true;
                  SpecialRideBooker.haskamenpower = true;
            }
         }

      }
   }

   public void onVictory() {
      super.onVictory();
      TurnTimer.atNextBattle();
   }

   public void applyEndOfTurnTriggers() {
      for(AbstractPower p : this.powers) {
         p.atEndOfTurn(true);
      }

      AbstractDungeon.actionManager.addToBottom(new ExhaustAllEtherealAction());
   }

   public ArrayList<String> getStartingDeck() {
      new SpecialRideBooker();
      new SpecialFaizBox();
      new SpecialHibikiTaiko();
      new SpecialHibikiTaikoScore();
      new SpecialKivaPowerMeter();
      ArrayList<String> retVal = new ArrayList();
      retVal.add("Decade_Attack");
      retVal.add("Decade_Attack");
      retVal.add("Decade_Attack");
      retVal.add("Decade_Attack");
      retVal.add("KamenRide");
      retVal.add("Decade_SpecialSkill");
      retVal.add("Decade_Defend");
      retVal.add("Decade_Defend");
      retVal.add("Decade_Defend");
      retVal.add("Decade_Defend");
      return retVal;
   }

   public ArrayList<String> getStartingRelics() {
      ArrayList<String> retVal = new ArrayList();
      retVal.add("Decadriver");
      UnlockTracker.markRelicAsSeen("Decadriver");
      retVal.add("TOEIDeficitBill");
      UnlockTracker.markRelicAsSeen("TOEIDeficitBill");
      return retVal;
   }

   public CharSelectInfo getLoadout() {
      String title = "Decade";
      String flavor;
      if (Settings.language == GameLanguage.ZHS) {
         flavor = "还不谢谢帝骑哥？";
      } else if (Settings.language == GameLanguage.ZHT) {
         flavor = "仲唔多谢帝骑哥？";
      } else {
         flavor = "Tsukasa Kadoya from Kamen Rider Decade.";
      }

      return new CharSelectInfo(title, flavor, 70, 70, 0, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
   }

   public void doCharSelectScreenSelectEffect() {
      CardCrawlGame.sound.stop("henshin(oilfish)");
      CardCrawlGame.sound.stop("ButtonSoundKey1");
      if (10 > ReturnRandomNumberAction2.ReturnRandomNumber()) {
         CardCrawlGame.sound.playA("henshin(oilfish)", 0.0F);
      } else {
         CardCrawlGame.sound.playA("ButtonSoundKey1", 0.0F);
      }

      NewRideBookerCardAction.AllRideGroup.clear();
      SpecialFaizBox.FaizPhone = true;
      SpecialFaizBox.FaizPointer = true;
      SpecialFaizBox.FaizShot = true;
      SpecialFaizBox.FaizEdge = true;
      SpecialFaizBox.FaizPoint = 0;
      DCDmod.RyukiCardLv[0] = 0;
      DCDmod.RyukiCardLv[1] = 0;
      DCDmod.RyukiCardLv[2] = 0;
      AgitoLevelPower.Lv = 0;
      DCDmod.clearConfig2();
   }

   public int getAscensionMaxHPLoss() {
      return 5;
   }

   public AbstractCard.CardColor getCardColor() {
      return AbstractCardEnum.DCD;
   }

   public int getCardCount() {
      return 0;
   }

   public Color getCardRenderColor() {
      return DCDmod.DCD;
   }

   public Color getCardTrailColor() {
      return DCDmod.DCD;
   }

   public String getCustomModeCharacterButtonSoundKey() {
      NewRideBookerCardAction.AllRideGroup.clear();
      SpecialFaizBox.FaizPhone = true;
      SpecialFaizBox.FaizPointer = true;
      SpecialFaizBox.FaizShot = true;
      SpecialFaizBox.FaizEdge = true;
      SpecialFaizBox.FaizPoint = 0;
      DCDmod.RyukiCardLv[0] = 0;
      DCDmod.RyukiCardLv[1] = 0;
      DCDmod.RyukiCardLv[2] = 0;
      DCDmod.clearConfig2();
      return GetButtonSoundKey.CustomModeButtonSoundKey();
   }

   public BitmapFont getEnergyNumFont() {
      return FontHelper.energyNumFontRed;
   }

   public AbstractPlayer newInstance() {
      return new Decade(this.name);
   }

   public String getLocalizedCharacterName() {
      return "Decade";
   }

   public int getSeenCardCount() {
      return 0;
   }

   public Color getSlashAttackColor() {
      return DCDmod.DCD;
   }

   public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
      return new AbstractGameAction.AttackEffect[]{AttackEffect.SLASH_HEAVY, AttackEffect.FIRE, AttackEffect.SLASH_DIAGONAL, AttackEffect.SLASH_HEAVY, AttackEffect.FIRE, AttackEffect.SLASH_DIAGONAL};
   }

   public String getSpireHeartText() {
      return "......";
   }

   public AbstractCard getStartCardForEvent() {
      return new KamenRideDecade();
   }

   public String getTitle(AbstractPlayer.PlayerClass arg0) {
      return "Decade";
   }

   public String getVampireText() {
      this.title = "....";
      return this.title;
   }
}
