package dcd_eng;

import basemod.BaseMod;
import basemod.DevConsole;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.ReflectionHacks;
import basemod.interfaces.AddAudioSubscriber;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.StartGameSubscriber;
import basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary.BlueCardsPatch;
import basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary.GreenCardsPatch;
import basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary.RedCardsPatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import dcd_eng.Actions.NewRideBookerCardAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Card.Basic.Decade_Attack;
import dcd_eng.Card.Basic.Decade_Defend;
import dcd_eng.Card.Basic.Decade_SpecialSkill;
import dcd_eng.Card.Basic.FinalAttackRide;
import dcd_eng.Card.Basic.KamenRide;
import dcd_eng.Card.Common.AutoVajin;
import dcd_eng.Card.Common.Blade_Beat;
import dcd_eng.Card.Common.Blade_Slash;
import dcd_eng.Card.Common.Decade_Dash1;
import dcd_eng.Card.Common.Decade_Defend2;
import dcd_eng.Card.Common.DenO_Ax;
import dcd_eng.Card.Common.DenO_Gun;
import dcd_eng.Card.Common.DenO_Rod;
import dcd_eng.Card.Common.DenO_Sword;
import dcd_eng.Card.Common.FormRideBasshaa;
import dcd_eng.Card.Common.FormRideDogga;
import dcd_eng.Card.Common.FormRideDragon;
import dcd_eng.Card.Common.FormRideFlame;
import dcd_eng.Card.Common.FormRideGaruru;
import dcd_eng.Card.Common.FormRidePegasus;
import dcd_eng.Card.Common.FormRideStorm;
import dcd_eng.Card.Common.FormRideTitan;
import dcd_eng.Card.Common.Hibiki_Attack1;
import dcd_eng.Card.Common.Hibiki_Attack2;
import dcd_eng.Card.Common.KamenRideDecade;
import dcd_eng.Card.Common.RideBooker;
import dcd_eng.Card.Common.RideBooker_Skill_1;
import dcd_eng.Card.Common.UnarmedAttack1;
import dcd_eng.Card.Common.UnarmedAttack3;
import dcd_eng.Card.Common.UnarmedAttack5;
import dcd_eng.Card.Common.UnarmedAttack7;
import dcd_eng.Card.Rare.DecadeViolentEmotion;
import dcd_eng.Card.Rare.DenO_Climax;
import dcd_eng.Card.Rare.FormRideAxel;
import dcd_eng.Card.Rare.Hibiki_FEVER;
import dcd_eng.Card.Rare.Kabuto_ClockUp;
import dcd_eng.Card.Rare.Kiva_Eat;
import dcd_eng.Card.Rare.Kiva_Swallow;
import dcd_eng.Card.Rare.Kuuga_Rising;
import dcd_eng.Card.Rare.NMDAZYYGL;
import dcd_eng.Card.Rare.TimeVent;
import dcd_eng.Card.Rare.UnarmedAttack9;
import dcd_eng.Card.Special.AgitoPower;
import dcd_eng.Card.Special.Blade_Thunder;
import dcd_eng.Card.Special.CrimsonSmash;
import dcd_eng.Card.Special.Decade_Blast;
import dcd_eng.Card.Special.Decade_Slash;
import dcd_eng.Card.Special.FlameSpecialCard;
import dcd_eng.Card.Special.Kabuto_CastOff;
import dcd_eng.Card.Special.Kabuto_PutOn;
import dcd_eng.Card.Special.PegasusAttack;
import dcd_eng.Card.Special.PegasusDefend;
import dcd_eng.Card.Special.PunchingUnit;
import dcd_eng.Card.Special.RideBooker_Attack;
import dcd_eng.Card.Special.RideBooker_Shoot;
import dcd_eng.Card.Special.SparkleCut;
import dcd_eng.Card.Special.StormSpecialCard;
import dcd_eng.Card.Uncommon.AttackRide;
import dcd_eng.Card.Uncommon.Blade_Kick;
import dcd_eng.Card.Uncommon.Blade_Mach;
import dcd_eng.Card.Uncommon.Blade_Metal;
import dcd_eng.Card.Uncommon.Decade_Dash2;
import dcd_eng.Card.Uncommon.Decade_Defend3;
import dcd_eng.Card.Uncommon.Decade_Illusion;
import dcd_eng.Card.Uncommon.Decade_Invisible;
import dcd_eng.Card.Uncommon.DragClaw;
import dcd_eng.Card.Uncommon.DragShield;
import dcd_eng.Card.Uncommon.Dragreder;
import dcd_eng.Card.Uncommon.FormRide;
import dcd_eng.Card.Uncommon.FormRideJack;
import dcd_eng.Card.Uncommon.Hibiki_Attack3;
import dcd_eng.Card.Uncommon.KamenRideAgito;
import dcd_eng.Card.Uncommon.KamenRideBlade;
import dcd_eng.Card.Uncommon.KamenRideDenO;
import dcd_eng.Card.Uncommon.KamenRideFaiz;
import dcd_eng.Card.Uncommon.KamenRideHibiki;
import dcd_eng.Card.Uncommon.KamenRideKabuto;
import dcd_eng.Card.Uncommon.KamenRideKiva;
import dcd_eng.Card.Uncommon.KamenRideKuuga;
import dcd_eng.Card.Uncommon.KamenRideRyuki;
import dcd_eng.Card.Uncommon.Kiva_Bats;
import dcd_eng.Card.Uncommon.Kiva_Frozen;
import dcd_eng.Card.Uncommon.Kuuga_GouramAttack;
import dcd_eng.Card.Uncommon.MirrorWorld;
import dcd_eng.Card.Uncommon.PhotonAcceleration;
import dcd_eng.Card.Uncommon.PrometheusPower;
import dcd_eng.Card.Uncommon.RideBooker_Skill_2;
import dcd_eng.Card.Uncommon.RideBooker_Skill_3;
import dcd_eng.Card.Uncommon.UnarmedAttack4;
import dcd_eng.Card.Uncommon.UnarmedAttack6;
import dcd_eng.Card.Uncommon.UnarmedAttack8;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Helper.SpecialTaikoEffects;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractHandAnimation;
import dcd_eng.Patches.AbstractSummonedAnimation;
import dcd_eng.Patches.CharacterEnum;
import dcd_eng.Patches.Keyword;
import dcd_eng.Power.AgitoLevelPower;
import dcd_eng.Power.AutoVajinPower;
import dcd_eng.Relic.Decaderiver;
import dcd_eng.Relic.TOEIDeficitBill;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

@SpireInitializer
public class DCDmod implements PostInitializeSubscriber, EditCharactersSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnStartBattleSubscriber, StartGameSubscriber, PostBattleSubscriber, AddAudioSubscriber {
   private static final String FRUITY_MOD_ASSETS_FOLDER = "img";
   public static final Color DCD = CardHelper.getColor(208, 45, 150);
   private static final String[] ENERGY_ORB = new String[]{"img/512/orb.png", "img/512/orb.png", "img/512/orb.png", "img/512/orb-dark.png"};
   private static final String[] ENERGY_ORB_P = new String[]{"img/1024/orb.png", "img/1024/orb.png", "img/1024/orb.png", "img/1024/orb-dark.png"};
   private static final String DCD_BUTTON = "charSelect/DecadeButton.png";
   private static final String DCD_PORTRAIT = "charSelect/2.png";
   public static final String MAGES_SHOULDER_1 = "char/DCD/decade_shoulder.png";
   public static final String MAGES_SHOULDER_2 = "char/DCD/decade_shoulder.png";
   public static final String MAGES_CORPSE = "char/DCD/corpse.png";
   public static final String[] ATTACK_BG = new String[]{"img/512/attack_decade.png", "img/512/attack_kuuga.png", "img/512/attack_agito.png", "img/512/attack_ryuki.png", "img/512/attack_faiz.png", "img/512/attack_blade.png", "img/512/attack_hibiki.png", "img/512/attack_kabuto.png", "img/512/attack_deno.png", "img/512/attack_kiva.png"};
   public static final String[] SKILL_BG = new String[]{"img/512/skill_decade.png", "img/512/skill_kuuga.png", "img/512/skill_agito.png", "img/512/skill_ryuki.png", "img/512/skill_faiz.png", "img/512/skill_blade.png", "img/512/skill_hibiki.png", "img/512/skill_kabuto.png", "img/512/skill_deno.png", "img/512/skill_kiva.png"};
   public static final String[] POWER_BG = new String[]{"img/512/power_decade.png", "img/512/power_kuuga.png", "img/512/power_agito.png", "img/512/power_ryuki.png", "img/512/power_faiz.png", "img/512/power_blade.png", "img/512/power_hibiki.png", "img/512/power_kabuto.png", "img/512/power_deno.png", "img/512/power_kiva.png"};
   public static final String[] ATTACK_BG_P = new String[]{"img/1024/attack_decade.png", "img/1024/attack_kuuga.png", "img/1024/attack_agito.png", "img/1024/attack_ryuki.png", "img/1024/attack_faiz.png", "img/1024/attack_blade.png", "img/1024/attack_hibiki.png", "img/1024/attack_kabuto.png", "img/1024/attack_deno.png", "img/1024/attack_kiva.png"};
   public static final String[] SKILL_BG_P = new String[]{"img/1024/skill_decade.png", "img/1024/skill_kuuga.png", "img/1024/skill_agito.png", "img/1024/skill_ryuki.png", "img/1024/skill_faiz.png", "img/1024/skill_blade.png", "img/1024/skill_hibiki.png", "img/1024/skill_kabuto.png", "img/1024/skill_deno.png", "img/1024/skill_kiva.png"};
   public static final String[] POWER_BG_P = new String[]{"img/1024/power_decade.png", "img/1024/power_kuuga.png", "img/1024/power_agito.png", "img/1024/power_ryuki.png", "img/1024/power_faiz.png", "img/1024/power_blade.png", "img/1024/power_hibiki.png", "img/1024/power_kabuto.png", "img/1024/power_deno.png", "img/1024/power_kiva.png"};
   public static final String[] BASIC = new String[]{"img/512/banner_decade.png", "img/512/banner_decade1.png", "img/512/banner_decade2.png", "img/512/banner_decade3.png", "img/512/banner_decade4.png", "img/512/banner_decade5.png", "img/512/banner_decadex.png"};
   public static final String[] COMMON = new String[]{"img/512/banner_decade_c.png", "img/512/banner_decade_c1.png", "img/512/banner_decade_c2.png", "img/512/banner_decade_c3.png", "img/512/banner_decade_c4.png", "img/512/banner_decade_c5.png", "img/512/banner_decade_cx.png"};
   public static final String[] UNCOMMON = new String[]{"img/512/banner_decade_u.png", "img/512/banner_decade_u1.png", "img/512/banner_decade_u2.png", "img/512/banner_decade_u3.png", "img/512/banner_decade_u4.png", "img/512/banner_decade_u5.png", "img/512/banner_decade_ux.png"};
   public static final String[] RARE = new String[]{"img/512/banner_decade_r.png", "img/512/banner_decade_r1.png", "img/512/banner_decade_r2.png", "img/512/banner_decade_r3.png", "img/512/banner_decade_r4.png", "img/512/banner_decade_r5.png", "img/512/banner_decade_rx.png"};
   public static final String[] SPECIAL = new String[]{"img/512/banner_decade_s.png", "img/512/banner_decade_s1.png", "img/512/banner_decade_s2.png", "img/512/banner_decade_s3.png", "img/512/banner_decade_s4.png", "img/512/banner_decade_s5.png", "img/512/banner_decade_sx.png"};
   public static final String[] BASIC_P = new String[]{"img/1024/banner_decade.png", "img/1024/banner_decade1.png", "img/1024/banner_decade2.png", "img/1024/banner_decade3.png", "img/1024/banner_decade4.png", "img/1024/banner_decade5.png", "img/1024/banner_decadex.png"};
   public static final String[] COMMON_P = new String[]{"img/1024/banner_decade_c.png", "img/1024/banner_decade_c1.png", "img/1024/banner_decade_c2.png", "img/1024/banner_decade_c3.png", "img/1024/banner_decade_c4.png", "img/1024/banner_decade_c5.png", "img/1024/banner_decade_cx.png"};
   public static final String[] UNCOMMON_P = new String[]{"img/1024/banner_decade_u.png", "img/1024/banner_decade_u1.png", "img/1024/banner_decade_u2.png", "img/1024/banner_decade_u3.png", "img/1024/banner_decade_u4.png", "img/1024/banner_decade_u5.png", "img/1024/banner_decade_ux.png"};
   public static final String[] RARE_P = new String[]{"img/1024/banner_decade_r.png", "img/1024/banner_decade_r1.png", "img/1024/banner_decade_r2.png", "img/1024/banner_decade_r3.png", "img/1024/banner_decade_r4.png", "img/1024/banner_decade_r5.png", "img/1024/banner_decade_rx.png"};
   public static final String[] SPECIAL_P = new String[]{"img/1024/banner_decade_s.png", "img/1024/banner_decade_s1.png", "img/1024/banner_decade_s2.png", "img/1024/banner_decade_s3.png", "img/1024/banner_decade_s4.png", "img/1024/banner_decade_s5.png", "img/1024/banner_decade_sx.png"};
   public static final String[] FAR = new String[]{"img/512/banner_FAR.png", "img/512/banner_FAR1.png", "img/512/banner_FAR2.png", "img/512/banner_FAR3.png", "img/512/banner_FAR4.png", "img/512/banner_FAR5.png", "img/512/banner_FARx.png"};
   public static final String[] FAR_P = new String[]{"img/1024/banner_FAR.png", "img/1024/banner_FAR1.png", "img/1024/banner_FAR2.png", "img/1024/banner_FAR3.png", "img/1024/banner_FAR4.png", "img/1024/banner_FAR5.png", "img/1024/banner_FARx.png"};
   public static int[] TaikoKeys = new int[]{49, 37, 43, 44, 62};
   public static int cn;
   public static int[] RyukiCardLv = new int[]{0, 0, 0};
   public static boolean AnimationTrigger = false;
   public static boolean HenshinTrigger = false;
   public static boolean BGMTrigger = false;
   private static Properties DCDDefaults = new Properties();
   private static final String DCD_BADGE = "img/powers/KamenRideDecadePower.png";
   @SpireEnum
   public static AbstractCard.CardTags RiderCard;
   @SpireEnum
   public static AbstractCard.CardTags KamenRide;
   @SpireEnum
   public static AbstractCard.CardTags FormRide;
   @SpireEnum
   public static AbstractCard.CardTags SelectCard;
   @SpireEnum
   public static AbstractCard.CardTags UnarmedCard;
   @SpireEnum
   public static AbstractCard.CardTags WeaponCard;
   @SpireEnum
   public static AbstractCard.CardTags TriggerCard;
   @SpireEnum
   public static AbstractCard.CardTags DenOActionCard;
   @SpireEnum
   public static AbstractCard.CardTags IntentCard;
   @SpireEnum
   public static AbstractCard.CardTags UndeadCard;
   @SpireEnum
   public static AbstractCard.CardTags DecadeCard;
   @SpireEnum
   public static AbstractCard.CardTags KuugaCard;
   @SpireEnum
   public static AbstractCard.CardTags AgitoCard;
   @SpireEnum
   public static AbstractCard.CardTags RyukiCard;
   @SpireEnum
   public static AbstractCard.CardTags FaizCard;
   @SpireEnum
   public static AbstractCard.CardTags BladeCard;
   @SpireEnum
   public static AbstractCard.CardTags HibikiCard;
   @SpireEnum
   public static AbstractCard.CardTags KabutoCard;
   @SpireEnum
   public static AbstractCard.CardTags DenOCard;
   @SpireEnum
   public static AbstractCard.CardTags KivaCard;

   public DCDmod() {
      DevConsole.logger.info("============================ 监听初始化事件 ============================");
      BaseMod.subscribe(this);
      DevConsole.logger.info("========================================================================");
      DevConsole.logger.info("========================= 正在加载追加游戏内容 =========================");
      DevConsole.logger.info("==========================其实没有新的游戏内容=============================");
      DevConsole.logger.info("======================== 正在注入新卡片相关信息 ========================");
      BaseMod.addColor(AbstractCardEnum.DCD, DCD, DCD, DCD, DCD, DCD, DCD, DCD, ATTACK_BG[0], SKILL_BG[0], POWER_BG[0], ENERGY_ORB[0], ATTACK_BG_P[0], SKILL_BG_P[0], POWER_BG_P[0], ENERGY_ORB_P[0]);
      DevConsole.logger.info("===========================注入新卡片相关信息成功========================");
      loadConfig();
   }

   public static void loadConfig() {
      DevConsole.logger.info("==========================读取设置=============================");

      try {
         SpireConfig config = new SpireConfig("DCDmod", "DCDSaveData", DCDDefaults);
         config.load();
         AnimationTrigger = config.getBool("AnimationTrigger");
         HenshinTrigger = config.getBool("HenshinTrigger");
         BGMTrigger = config.getBool("BGMTrigger");
      } catch (Exception e) {
         e.printStackTrace();
         clearConfig();
      }

      DevConsole.logger.info("==========================设置读取完毕=============================");
   }

   public static void loadConfig2() {
      DevConsole.logger.info("==========================读取设置=============================");

      try {
         SpireConfig config = new SpireConfig("DCDmod", "DCDCardGroupSaveData", DCDDefaults);
         config.load();
         int Save = config.getInt("RideBookerGroupSave");
         if (Save != 0) {
            cn = config.getInt("RideBookerGroupN");
            NewRideBookerCardAction.AllRideGroup.clear();
            if (cn == 0) {
               System.out.println("卡盒内无卡牌存储");
            } else {
               int lc = 0;

               for(int i = 0; i < cn; ++i) {
                  ++lc;
                  AbstractCard c = CardLibrary.getCopy(config.getString("RideBookerGroup" + lc));
                  if (config.getBool("RideBookerGroupUpgraded" + lc)) {
                     c.upgrade();
                  }

                  NewRideBookerCardAction.AllRideGroup.addToBottom(c);
               }

               System.out.println("卡盒载入完成");
            }
         }

         RyukiCardLv[0] = config.getInt("Ryuki1");
         RyukiCardLv[1] = config.getInt("Ryuki2");
         RyukiCardLv[2] = config.getInt("Ryuki3");
         SpecialFaizBox.FaizPhone = config.getBool("FaizGear1_s");
         SpecialFaizBox.FaizPointer = config.getBool("FaizGear2_s");
         SpecialFaizBox.FaizShot = config.getBool("FaizGear3_s");
         SpecialFaizBox.FaizEdge = config.getBool("FaizGear4_s");
         SpecialFaizBox.FaizPoint = config.getInt("FaizPoint_s");
         AgitoLevelPower.Lv = config.getInt("AgitoLv");
         System.out.println("Faiz插件状态载入成功");
      } catch (Exception e) {
         e.printStackTrace();
         clearConfig2();
      }

      DevConsole.logger.info("==========================设置读取完毕=============================");
   }

   public static void saveConfig() {
      DevConsole.logger.info("==========================存档设置=============================");

      try {
         SpireConfig config = new SpireConfig("DCDmod", "DCDSaveData", DCDDefaults);
         config.setBool("AnimationTrigger", AnimationTrigger);
         config.setBool("HenshinTrigger", HenshinTrigger);
         config.setBool("BGMTrigger", BGMTrigger);
         config.setInt("RideBookerGroupSave", 0);
         config.save();
      } catch (Exception e) {
         e.printStackTrace();
      }

      DevConsole.logger.info("==========================设置存档完毕=============================");
   }

   public static void saveConfig2() {
      DevConsole.logger.info("==========================存档设置=============================");

      try {
         SpireConfig config = new SpireConfig("DCDmod", "DCDCardGroupSaveData", DCDDefaults);
         int cn = 0;
         if (NewRideBookerCardAction.AllRideGroup.group.isEmpty()) {
            System.out.println("卡盒内无卡牌");
         } else {
            for(AbstractCard c : NewRideBookerCardAction.AllRideGroup.group) {
               ++cn;
               config.setString("RideBookerGroup" + cn, c.cardID);
               if (c.upgraded) {
                  config.setBool("RideBookerGroupUpgraded" + cn, true);
               } else {
                  config.setBool("RideBookerGroupUpgraded" + cn, false);
               }
            }

            System.out.println("卡盒存储完成");
         }

         config.setInt("RideBookerGroupN", cn);
         config.setInt("RideBookerGroupSave", cn);
         config.setBool("FaizGear1_s", SpecialFaizBox.FaizPhone);
         config.setBool("FaizGear2_s", SpecialFaizBox.FaizPointer);
         config.setBool("FaizGear3_s", SpecialFaizBox.FaizShot);
         config.setBool("FaizGear4_s", SpecialFaizBox.FaizEdge);
         config.setInt("FaizPoint_s", SpecialFaizBox.FaizPoint);
         config.setInt("Ryuki1", RyukiCardLv[0]);
         config.setInt("Ryuki2", RyukiCardLv[1]);
         config.setInt("Ryuki3", RyukiCardLv[2]);
         config.setInt("AgitoLv", AgitoLevelPower.Lv);
         config.save();
      } catch (Exception e) {
         e.printStackTrace();
      }

      DevConsole.logger.info("==========================设置存档完毕=============================");
   }

   public static void clearConfig() {
      DevConsole.logger.info("==========================清除存档设置=============================");

      try {
         SpireConfig config = new SpireConfig("DCDmod", "DCDSaveData", DCDDefaults);
         config.setBool("AnimationTrigger", AnimationTrigger);
         config.setBool("HenshinTrigger", HenshinTrigger);
         config.setBool("BGMTrigger", BGMTrigger);
         config.save();
         saveConfig();
      } catch (Exception e) {
         e.printStackTrace();
      }

      DevConsole.logger.info("==========================存档设置清除完毕=============================");
   }

   public static void clearConfig2() {
      DevConsole.logger.info("==========================清除存档设置=============================");

      try {
         SpireConfig config = new SpireConfig("DCDmod", "DCDSaveData", DCDDefaults);
         config.save();
         saveConfig2();
      } catch (Exception e) {
         e.printStackTrace();
      }

      DevConsole.logger.info("==========================存档设置清除完毕=============================");
   }

   public static void initialize() {
      DevConsole.logger.info("========================= 初始化DCDMod所有数据 =========================");
      new DCDmod();
      DevConsole.logger.info("=========================== 初始化DCDMod成功 ===========================");
   }

   public static String makePath(String resource) {
      return "img/" + resource;
   }

   private static String loadJson(String jsonPath) {
      return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
   }

   private void loadLocKeywords() {
      String keywordsPath;
      switch (Settings.language) {
         case ZHT:
         case ZHS:
            keywordsPath = "localization/zhs/DCDKeywords.json";
            break;
         default:
            keywordsPath = "localization/eng/DCDKeywords.json";
      }

      Gson gson = new Gson();
      String json = loadJson(keywordsPath);
      Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);
      DevConsole.logger.info("========================== 正在注入新的关键字 ==========================");
      if (keywords != null) {
         for(Keyword keyword : keywords) {
            BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
         }
      }

      DevConsole.logger.info("===========================注入新的关键字成功=============================");
   }

   public void receiveEditKeywords() {
      this.loadLocKeywords();
   }

   public void receiveEditCharacters() {
      DevConsole.logger.info("======================== 正在注入DCD的信息 ========================");
      DevConsole.logger.info("add " + CharacterEnum.Decade.toString());
      BaseMod.addCharacter(new Decade("Decade"), makePath("charSelect/DecadeButton.png"), makePath("charSelect/2.png"), CharacterEnum.Decade);
      DevConsole.logger.info("============================注入DCD成功================================");
   }

   public void receivePostInitialize() {
      Texture badgeTexture = new Texture("img/powers/KamenRideDecadePower.png");
      ModPanel settingsPanel = new ModPanel();
      ModLabeledToggleButton flipButton = new ModLabeledToggleButton("攻击动画开关（开启后可去除攻击动画等特效，极高提升流畅度，重启游戏以达最优效果）", 400.0F, 720.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, AnimationTrigger, settingsPanel, (label) -> {
      }, (button) -> {
         AnimationTrigger = button.enabled;
         saveConfig();
      });
      ModLabeledToggleButton flipButton2 = new ModLabeledToggleButton("变身动画开关（开启后可去除变身动画特效）", 400.0F, 660.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, HenshinTrigger, settingsPanel, (label) -> {
      }, (button) -> {
         HenshinTrigger = button.enabled;
         saveConfig();
      });
      ModLabeledToggleButton flipButton3 = new ModLabeledToggleButton("音效开关（开启后可去除变身和使用最终攻击驾驭时的背景音乐）", 400.0F, 600.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, BGMTrigger, settingsPanel, (label) -> {
      }, (button) -> {
         BGMTrigger = button.enabled;
         saveConfig();
      });
      settingsPanel.addUIElement(flipButton);
      settingsPanel.addUIElement(flipButton2);
      settingsPanel.addUIElement(flipButton3);
      BaseMod.registerModBadge(badgeTexture, "Decade", "树", "增加了以假面骑士Decade中的门矢士为原型的人物，测试版", settingsPanel);
      HashMap<String, Sfx> reflectedMap = this.getSoundsMap();
      reflectedMap.put("decade_henshin", new Sfx("sounds/decade_hensin.ogg"));
      reflectedMap.put("people_henshin", new Sfx("sounds/people_hensin.ogg"));
      reflectedMap.put("victory_normal", new Sfx("sounds/victory_normal.ogg"));
      reflectedMap.put("victory1", new Sfx("sounds/victory1.ogg"));
      reflectedMap.put("victory2", new Sfx("sounds/victory2.ogg"));
      reflectedMap.put("kamenride", new Sfx("sounds/kamenride.wav"));
      reflectedMap.put("kuuga_henshin", new Sfx("sounds/kuuga_hensin.wav"));
      reflectedMap.put("agito_henshin", new Sfx("sounds/agito_hensin.wav"));
      reflectedMap.put("driversounds", new Sfx("sounds/driversounds.wav"));
      reflectedMap.put("test1", new Sfx("sounds/test.ogg"));
      reflectedMap.put("formride", new Sfx("sounds/form_ride.mp3"));
      reflectedMap.put("pegasus", new Sfx("sounds/kuuga_pegasus.mp3"));
      reflectedMap.put("titan", new Sfx("sounds/kuuga_titan.mp3"));
      reflectedMap.put("dragon", new Sfx("sounds/kuuga_dragon.mp3"));
      reflectedMap.put("flame", new Sfx("sounds/agito_flame.mp3"));
      reflectedMap.put("storm", new Sfx("sounds/agito_storm.mp3"));
      reflectedMap.put("garuru", new Sfx("sounds/kiva_garuru.mp3"));
      reflectedMap.put("basshaa", new Sfx("sounds/kiva_basshaa.mp3"));
      reflectedMap.put("dogga", new Sfx("sounds/kiva_dogga.mp3"));
      reflectedMap.put("FAR", new Sfx("sounds/final_attack_ride.mp3"));
      reflectedMap.put("FAR_DCD", new Sfx("sounds/decade_final.mp3"));
      reflectedMap.put("FAR_KUUGA", new Sfx("sounds/kuuga_final.mp3"));
      reflectedMap.put("FAR_AGITO", new Sfx("sounds/agito_final.mp3"));
      reflectedMap.put("FAR_RYUKI", new Sfx("sounds/ryuki_final.mp3"));
      reflectedMap.put("FAR_FAIZ", new Sfx("sounds/faiz_final.mp3"));
      reflectedMap.put("FAR_BLADE", new Sfx("sounds/blade_final.mp3"));
      reflectedMap.put("FAR_HIBIKI", new Sfx("sounds/hibiki_final.mp3"));
      reflectedMap.put("FAR_KABUTO", new Sfx("sounds/kabuto_final.mp3"));
      reflectedMap.put("FAR_DENO", new Sfx("sounds/deno_final.mp3"));
      reflectedMap.put("FAR_KIVA", new Sfx("sounds/kiva_final.mp3"));
      reflectedMap.put("attackride", new Sfx("sounds/attack_ride.mp3"));
      reflectedMap.put("autovajin", new Sfx("sounds/faiz_auto_vajin.mp3"));
      reflectedMap.put("autovajinattack", new Sfx("sounds/autovajinattack.wav"));
      reflectedMap.put("autovajindisappear", new Sfx("sounds/autovajindisappear.wav"));
      reflectedMap.put("ryuki_henshin", new Sfx("sounds/ryuki_hensin.wav"));
      reflectedMap.put("dragreder_advent", new Sfx("sounds/advent.wav"));
      reflectedMap.put("dragreder_appear", new Sfx("sounds/dragreder_appear.wav"));
      reflectedMap.put("dragreder_attack", new Sfx("sounds/dragreder_attack.ogg"));
      reflectedMap.put("ryuki_guard", new Sfx("sounds/ryuki_guard.wav"));
      reflectedMap.put("ryuki_strike", new Sfx("sounds/ryuki_strike_vein.mp3"));
      reflectedMap.put("faiz_henshin", new Sfx("sounds/faiz_hensin.wav"));
      reflectedMap.put("faiz_button", new Sfx("sounds/ExceedCharge.wav"));
      reflectedMap.put("faiz_sword", new Sfx("sounds/faiz_sword.wav"));
      reflectedMap.put("axel_1", new Sfx("sounds/faiz_Axel_1.wav"));
      reflectedMap.put("axel_2", new Sfx("sounds/faiz_Axel_2.wav"));
      reflectedMap.put("axel_3", new Sfx("sounds/faiz_Axel_3.wav"));
      reflectedMap.put("axel_timer", new Sfx("sounds/faiz_Axel_timer.wav"));
      reflectedMap.put("axel_timeout", new Sfx("sounds/faiz_Axel_timeout.wav"));
      reflectedMap.put("axel_sound", new Sfx("sounds/faiz_axel.mp3"));
      reflectedMap.put("blade_henshin", new Sfx("sounds/blade_henshin.wav"));
      reflectedMap.put("blade_jack", new Sfx("sounds/blade_jack.mp3"));
      reflectedMap.put("blade_beat", new Sfx("sounds/Blade_beat.ogg"));
      reflectedMap.put("blade_kick", new Sfx("sounds/Blade_kick.ogg"));
      reflectedMap.put("blade_mach", new Sfx("sounds/Blade_mach.ogg"));
      reflectedMap.put("blade_metal", new Sfx("sounds/Blade_metal.ogg"));
      reflectedMap.put("blade_slash", new Sfx("sounds/Blade_slash.ogg"));
      reflectedMap.put("blade_thunder", new Sfx("sounds/Blade_thunder.ogg"));
      reflectedMap.put("blade_LightningBlast", new Sfx("sounds/Blade_LightningBlast.ogg"));
      reflectedMap.put("blade_LightningSlash", new Sfx("sounds/Blade_LightningSlash.ogg"));
      reflectedMap.put("blade_LightningSonic", new Sfx("sounds/Blade_LightningSonic.ogg"));
      reflectedMap.put("PON", new Sfx("sounds/PON.mp3"));
      reflectedMap.put("PATA", new Sfx("sounds/PATA.mp3"));
      reflectedMap.put("CHAKA", new Sfx("sounds/CHAKA.mp3"));
      reflectedMap.put("DON", new Sfx("sounds/DON.mp3"));
      reflectedMap.put("Forward", new Sfx("sounds/PATA-PATA-PATA-PON.mp3"));
      reflectedMap.put("Back", new Sfx("sounds/PON-PATA-PON-PATA.mp3"));
      reflectedMap.put("Attack", new Sfx("sounds/PON-PON-PATA-PON.mp3"));
      reflectedMap.put("Defend", new Sfx("sounds/CHAKA-CHAKA-PATA-PON.mp3"));
      reflectedMap.put("Accumulate", new Sfx("sounds/PON-PON-CHAKA-CHAKA.mp3"));
      reflectedMap.put("Jump", new Sfx("sounds/DON-DON-CHAKA-CHAKA.mp3"));
      reflectedMap.put("Relieve", new Sfx("sounds/PATA-PON-DON-CHAKA.mp3"));
      reflectedMap.put("Buff", new Sfx("sounds/DON-DONDON-DONDON.mp3"));
      reflectedMap.put("Fever", new Sfx("sounds/FEVER.mp3"));
      reflectedMap.put("hibiki_henshin", new Sfx("sounds/hibiki_henshin.wav"));
      reflectedMap.put("hibiki_kurenai_sound", new Sfx("sounds/hibiki_kurenai_sound.ogg"));
      reflectedMap.put("hibiki_kurenai", new Sfx("sounds/hibiki_kurenai.ogg"));
      reflectedMap.put("hibiki_attack1", new Sfx("sounds/hibiki_onibi.mp3"));
      reflectedMap.put("hibiki_attack2", new Sfx("sounds/hibiki_ongekibou_rekka.mp3"));
      reflectedMap.put("kabuto_henshin", new Sfx("sounds/kabuto_henshin.wav"));
      reflectedMap.put("kabuto_clockup", new Sfx("sounds/kabuto_clock_up.mp3"));
      reflectedMap.put("deno_henshin", new Sfx("sounds/deno_henshin.wav"));
      reflectedMap.put("deno_oresanjo", new Sfx("sounds/deno_oresanjo.mp3"));
      reflectedMap.put("deno_kotaewa_kiite_nai", new Sfx("sounds/deno_kotaewa_kiite_nai.mp3"));
      reflectedMap.put("deno_bokuni_tsurarete_miru", new Sfx("sounds/deno_bokuni_tsurarete_miru.mp3"));
      reflectedMap.put("deno_nakerude", new Sfx("sounds/deno_nakerude.mp3"));
      reflectedMap.put("BGM", new Sfx("audio/music/BGM.wav"));
      reflectedMap.put("decade_slash", new Sfx("sounds/decade_slash.wav"));
      reflectedMap.put("attack_slash", new Sfx("sounds/attack_slash.wav"));
      reflectedMap.put("kuuga_attack", new Sfx("sounds/kuuga_attack.wav"));
      reflectedMap.put("kuuga_boom", new Sfx("sounds/kuuga_boom.wav"));
      reflectedMap.put("titan_slash", new Sfx("sounds/titan_slash.ogg"));
      reflectedMap.put("kuuga_currentsound", new Sfx("sounds/kuuga_currentsound.wav"));
      reflectedMap.put("pegasus_attack", new Sfx("sounds/pegasus_attack.wav"));
      reflectedMap.put("pegasus_charge", new Sfx("sounds/pegasus_charge.ogg"));
      reflectedMap.put("gouram_sound", new Sfx("sounds/gouram_sound.wav"));
      reflectedMap.put("motorbike_sound", new Sfx("sounds/motorbike_sound.wav"));
      reflectedMap.put("ButtonSoundKey1", new Sfx("sounds/ButtonSoundKey1.wav"));
      reflectedMap.put("henshin(oilfish)", new Sfx("sounds/Hensin(oilfish).ogg"));
      reflectedMap.put("648", new Sfx("sounds/648.ogg"));
      reflectedMap.put("kiva_henshin", new Sfx("sounds/kiva_henshin.mp3"));
      reflectedMap.put("kiva_chainbreak", new Sfx("sounds/kiva_chainbreak.wav"));
      reflectedMap.put("agito_kick_charge", new Sfx("sounds/agito_kick_charge.ogg"));
      reflectedMap.put("jump", new Sfx("sounds/jump.wav"));
      reflectedMap.put("fire", new Sfx("sounds/fire.wav"));
      reflectedMap.put("FireSlash1", new Sfx("sounds/FireSlash1.wav"));
      reflectedMap.put("FireSlash2", new Sfx("sounds/FireSlash2.wav"));
   }

   private HashMap<String, Sfx> getSoundsMap() {
      return (HashMap)ReflectionHacks.getPrivate(CardCrawlGame.sound, SoundMaster.class, "map");
   }

   public void receiveEditCards() {
      DevConsole.logger.info("========================= 正在加载新的卡牌内容 =========================");
      RedCardsPatch.Postfix();
      GreenCardsPatch.Postfix();
      BlueCardsPatch.Postfix();
      BaseMod.addCard(new Decade_Attack());
      BaseMod.addCard(new Decade_Defend());
      BaseMod.addCard(new FinalAttackRide());
      BaseMod.addCard(new KamenRide());
      BaseMod.addCard(new Decade_SpecialSkill());
      BaseMod.addCard(new KamenRideDecade());
      BaseMod.addCard(new RideBooker());
      BaseMod.addCard(new FormRideDragon());
      BaseMod.addCard(new FormRideTitan());
      BaseMod.addCard(new FormRidePegasus());
      BaseMod.addCard(new Decade_Dash1());
      BaseMod.addCard(new AutoVajin());
      BaseMod.addCard(new Decade_Defend2());
      BaseMod.addCard(new FormRideStorm());
      BaseMod.addCard(new FormRideFlame());
      BaseMod.addCard(new UnarmedAttack1());
      BaseMod.addCard(new UnarmedAttack3());
      BaseMod.addCard(new UnarmedAttack5());
      BaseMod.addCard(new UnarmedAttack7());
      BaseMod.addCard(new Blade_Slash());
      BaseMod.addCard(new Blade_Beat());
      BaseMod.addCard(new Hibiki_Attack1());
      BaseMod.addCard(new Hibiki_Attack2());
      BaseMod.addCard(new DenO_Ax());
      BaseMod.addCard(new DenO_Sword());
      BaseMod.addCard(new DenO_Gun());
      BaseMod.addCard(new DenO_Rod());
      BaseMod.addCard(new RideBooker_Skill_1());
      BaseMod.addCard(new FormRideGaruru());
      BaseMod.addCard(new FormRideBasshaa());
      BaseMod.addCard(new FormRideDogga());
      BaseMod.addCard(new Kuuga_Rising());
      BaseMod.addCard(new TimeVent());
      BaseMod.addCard(new NMDAZYYGL());
      BaseMod.addCard(new UnarmedAttack9());
      BaseMod.addCard(new FormRideAxel());
      BaseMod.addCard(new Hibiki_FEVER());
      BaseMod.addCard(new Kabuto_ClockUp());
      BaseMod.addCard(new DecadeViolentEmotion());
      BaseMod.addCard(new DenO_Climax());
      BaseMod.addCard(new Kiva_Eat());
      BaseMod.addCard(new Kiva_Swallow());
      BaseMod.addCard(new Decade_Invisible());
      BaseMod.addCard(new Decade_Illusion());
      BaseMod.addCard(new KamenRideKuuga());
      BaseMod.addCard(new Decade_Dash2());
      BaseMod.addCard(new Decade_Defend3());
      BaseMod.addCard(new KamenRideAgito());
      BaseMod.addCard(new UnarmedAttack6());
      BaseMod.addCard(new UnarmedAttack4());
      BaseMod.addCard(new UnarmedAttack8());
      BaseMod.addCard(new PrometheusPower());
      BaseMod.addCard(new FormRide());
      BaseMod.addCard(new KamenRideRyuki());
      BaseMod.addCard(new MirrorWorld());
      BaseMod.addCard(new KamenRideFaiz());
      BaseMod.addCard(new KamenRideBlade());
      BaseMod.addCard(new Blade_Kick());
      BaseMod.addCard(new Blade_Mach());
      BaseMod.addCard(new Blade_Metal());
      BaseMod.addCard(new KamenRideHibiki());
      BaseMod.addCard(new Hibiki_Attack3());
      BaseMod.addCard(new KamenRideKabuto());
      BaseMod.addCard(new PhotonAcceleration());
      BaseMod.addCard(new DragClaw());
      BaseMod.addCard(new DragShield());
      BaseMod.addCard(new Dragreder());
      BaseMod.addCard(new AttackRide());
      BaseMod.addCard(new Kuuga_GouramAttack());
      BaseMod.addCard(new KamenRideDenO());
      BaseMod.addCard(new FormRideJack());
      BaseMod.addCard(new RideBooker_Skill_2());
      BaseMod.addCard(new RideBooker_Skill_3());
      BaseMod.addCard(new KamenRideKiva());
      BaseMod.addCard(new Kiva_Frozen());
      BaseMod.addCard(new Kiva_Bats());
      BaseMod.addCard(new Decade_Blast());
      BaseMod.addCard(new Decade_Slash());
      BaseMod.addCard(new RideBooker_Attack());
      BaseMod.addCard(new RideBooker_Shoot());
      BaseMod.addCard(new PegasusDefend());
      BaseMod.addCard(new PegasusAttack());
      BaseMod.addCard(new AgitoPower());
      BaseMod.addCard(new StormSpecialCard());
      BaseMod.addCard(new FlameSpecialCard());
      BaseMod.addCard(new CrimsonSmash());
      BaseMod.addCard(new SparkleCut());
      BaseMod.addCard(new PunchingUnit());
      BaseMod.addCard(new Blade_Thunder());
      BaseMod.addCard(new Kabuto_PutOn());
      BaseMod.addCard(new Kabuto_CastOff());
      UnlockTracker.unlockCard("Decade_Attack");
      UnlockTracker.unlockCard("Decade_Defend");
      UnlockTracker.unlockCard("FinalAttackRide");
      UnlockTracker.unlockCard("KamenRide");
      UnlockTracker.unlockCard("Decade_SpecialSkill");
      UnlockTracker.unlockCard("KamenRideDecade");
      UnlockTracker.unlockCard("RideBooker");
      UnlockTracker.unlockCard("FormRideDragon");
      UnlockTracker.unlockCard("FormRideTitan");
      UnlockTracker.unlockCard("FormRidePegasus");
      UnlockTracker.unlockCard("Decade_Dash1");
      UnlockTracker.unlockCard("AutoVajin");
      UnlockTracker.unlockCard("Decade_Defend2");
      UnlockTracker.unlockCard("FormRideStorm");
      UnlockTracker.unlockCard("FormRideFlame");
      UnlockTracker.unlockCard("UnarmedAttack1");
      UnlockTracker.unlockCard("UnarmedAttack3");
      UnlockTracker.unlockCard("UnarmedAttack5");
      UnlockTracker.unlockCard("UnarmedAttack7");
      UnlockTracker.unlockCard("Blade_Slash");
      UnlockTracker.unlockCard("Blade_Beat");
      UnlockTracker.unlockCard("Hibiki_Attack1");
      UnlockTracker.unlockCard("Hibiki_Attack2");
      UnlockTracker.unlockCard("DenO_Ax");
      UnlockTracker.unlockCard("DenO_Sword");
      UnlockTracker.unlockCard("DenO_Gun");
      UnlockTracker.unlockCard("DenO_Rod");
      UnlockTracker.unlockCard("RideBooker_Skill_1");
      UnlockTracker.unlockCard("FormRideGaruru");
      UnlockTracker.unlockCard("FormRideBasshaa");
      UnlockTracker.unlockCard("FormRideDogga");
      UnlockTracker.unlockCard("Kuuga_Rising");
      UnlockTracker.unlockCard("TimeVent");
      UnlockTracker.unlockCard("NMDAZYYGL");
      UnlockTracker.unlockCard("UnarmedAttack9");
      UnlockTracker.unlockCard("FormRideAxel");
      UnlockTracker.unlockCard("Hibiki_FEVER");
      UnlockTracker.unlockCard("Kabuto_ClockUp");
      UnlockTracker.unlockCard("DecadeViolentEmotion");
      UnlockTracker.unlockCard("DenO_Climax");
      UnlockTracker.unlockCard("Kiva_Eat");
      UnlockTracker.unlockCard("Kiva_Swallow");
      UnlockTracker.unlockCard("Decade_Invisible");
      UnlockTracker.unlockCard("Decade_Illusion");
      UnlockTracker.unlockCard("KamenRideKuuga");
      UnlockTracker.unlockCard("Decade_Dash2");
      UnlockTracker.unlockCard("Decade_Defend3");
      UnlockTracker.unlockCard("KamenRideAgito");
      UnlockTracker.unlockCard("UnarmedAttack6");
      UnlockTracker.unlockCard("UnarmedAttack4");
      UnlockTracker.unlockCard("UnarmedAttack8");
      UnlockTracker.unlockCard("PrometheusPower");
      UnlockTracker.unlockCard("FormRide");
      UnlockTracker.unlockCard("KamenRideRyuki");
      UnlockTracker.unlockCard("MirrorWorld");
      UnlockTracker.unlockCard("KamenRideFaiz");
      UnlockTracker.unlockCard("KamenRideBlade");
      UnlockTracker.unlockCard("Blade_Kick");
      UnlockTracker.unlockCard("Blade_Mach");
      UnlockTracker.unlockCard("Blade_Metal");
      UnlockTracker.unlockCard("KamenRideHibiki");
      UnlockTracker.unlockCard("Hibiki_Attack3");
      UnlockTracker.unlockCard("KamenRideKabuto");
      UnlockTracker.unlockCard("PhotonAcceleration");
      UnlockTracker.unlockCard("DragClaw");
      UnlockTracker.unlockCard("DragShield");
      UnlockTracker.unlockCard("Dragreder");
      UnlockTracker.unlockCard("AttackRide");
      UnlockTracker.unlockCard("Kuuga_GouramAttack");
      UnlockTracker.unlockCard("KamenRideDenO");
      UnlockTracker.unlockCard("FormRideJack");
      UnlockTracker.unlockCard("RideBooker_Skill_2");
      UnlockTracker.unlockCard("RideBooker_Skill_3");
      UnlockTracker.unlockCard("KamenRideKiva");
      UnlockTracker.unlockCard("Kiva_Frozen");
      UnlockTracker.unlockCard("Kiva_Bats");
      UnlockTracker.unlockCard("Decade_Blast");
      UnlockTracker.unlockCard("Decade_Slash");
      UnlockTracker.unlockCard("RideBooker_Attack");
      UnlockTracker.unlockCard("RideBooker_Shoot");
      UnlockTracker.unlockCard("PegasusDefend");
      UnlockTracker.unlockCard("PegasusAttack");
      UnlockTracker.unlockCard("AgitoPower");
      UnlockTracker.unlockCard("StormSpecialCard");
      UnlockTracker.unlockCard("FlameSpecialCard");
      UnlockTracker.unlockCard("CrimsonSmash");
      UnlockTracker.unlockCard("SparkleCut");
      UnlockTracker.unlockCard("PunchingUnit");
      UnlockTracker.unlockCard("Blade_Thunder");
      UnlockTracker.unlockCard("Kabuto_PutOn");
      UnlockTracker.unlockCard("Kabuto_CastOff");
      DevConsole.logger.info("=========================加载新的卡牌内容成功===============================");
   }

   public void receiveEditRelics() {
      DevConsole.logger.info("========================= 正在加载新的遗物内容 =========================");
      BaseMod.addRelicToCustomPool(new Decaderiver(), AbstractCardEnum.DCD);
      BaseMod.addRelicToCustomPool(new TOEIDeficitBill(), AbstractCardEnum.DCD);
      DevConsole.logger.info("==========================加载新的遗物内容成功===========================");
   }

   public void receiveEditStrings() {
      DevConsole.logger.info("正在加载对应语言文本信息");
      String card;
      String relic;
      String power;
      String orb;
      String ui;
      if (Settings.language == GameLanguage.ZHS) {
         DevConsole.logger.info("简体中文");
         card = "localization/zhs/DCDCards.json";
         relic = "localization/zhs/DCDRelics.json";
         power = "localization/zhs/DCDPower.json";
         orb = "localization/zhs/DCDOrbs.json";
         ui = "localization/zhs/DCDUi.json";
      } else if (Settings.language == GameLanguage.ZHT) {
         DevConsole.logger.info("繁体中文");
         card = "localization/zhs/DCDCards.json";
         relic = "localization/zhs/DCDRelics.json";
         power = "localization/zhs/DCDPower.json";
         orb = "localization/zhs/DCDOrbs.json";
         ui = "localization/zhs/DCDUi.json";
      } else {
         DevConsole.logger.info("英文");
         card = "localization/eng/DCDCards.json";
         relic = "localization/eng/DCDRelics.json";
         power = "localization/eng/DCDPower.json";
         orb = "localization/eng/DCDOrbs.json";
         ui = "localization/eng/DCDUi.json";
      }

      String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
      String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
      String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
      String orbStrings = Gdx.files.internal(orb).readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
      String uiStrings = Gdx.files.internal(ui).readString(String.valueOf(StandardCharsets.UTF_8));
      BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
      DevConsole.logger.info("语言文本加载成功");
   }

   public void receiveCardUsed(AbstractCard c) {
      if (c.type == CardType.ATTACK && AbstractDungeon.player.hasPower("AutoVajinPower")) {
         AutoVajinPower.AutoVajinAttack = true;
      }

   }

   public void receiveOnBattleStart(AbstractRoom arg0) {
      TurnTimer.atBattleStart();
      TurnTimer.StopBGM(true);
   }

   public void receivePostBattle(AbstractRoom abstractRoom) {
      if (abstractRoom instanceof MonsterRoom && AbstractDungeon.player.hasPower("DecadeSpecialSkillPower")) {
         AbstractDungeon.getCurrRoom().addCardToRewards();
      }

      SpecialTaikoEffects.TaikoTimer = 0.1F;
   }

   public void receiveStartGame() {
      if (AbstractDungeon.player instanceof Decade) {
         AbstractDungeon.uncommonRelicPool.remove("MummifiedHand");
         AbstractDungeon.uncommonRelicPool.remove("Mummified Hand");
         AbstractDungeon.uncommonRelicPool.remove("SingingBowl");
         AbstractDungeon.uncommonRelicPool.remove("Singing Bowl");
      }

      AbstractSummonedAnimation.clearAll();
      AbstractAnimation.clearAll();
      AbstractHandAnimation.clearAll();
      TurnTimer.StopBGM(true);
      loadConfig2();
   }

   public void receiveAddAudio() {
   }
}
