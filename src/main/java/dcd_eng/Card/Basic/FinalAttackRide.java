package dcd_eng.Card.Basic;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.KivaDoggaFARAction;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.RyukiAttackAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Card.Status.Kabuto_Photon;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Patches.HibikiTaikoKeyEvent;
import dcd_eng.Power.KivaFARFrozenPower;
import dcd_eng.Vfx.Agito_FAR_Flame;
import dcd_eng.Vfx.Agito_FAR_Storm;
import dcd_eng.Vfx.Agito_FAR_kick;
import dcd_eng.Vfx.Agito_FAR_sounds;
import dcd_eng.Vfx.Agito_t_FAR_kick;
import dcd_eng.Vfx.Axel_CrimsonSmash_Timer;
import dcd_eng.Vfx.Blade_FAR_sounds1;
import dcd_eng.Vfx.Blade_FAR_sounds2;
import dcd_eng.Vfx.Blade_FAR_sounds3;
import dcd_eng.Vfx.Decade_FAR_kick;
import dcd_eng.Vfx.DenO_FAR_sounds;
import dcd_eng.Vfx.FAR_SoundsAndAnimation;
import dcd_eng.Vfx.Faiz_CrimsonSmash;
import dcd_eng.Vfx.Faiz_FAR_SoundsAndAnimation;
import dcd_eng.Vfx.FormRide_FAR_SoundsAndAnimation;
import dcd_eng.Vfx.Hibiki_FAR_SoundsAndAnimation;
import dcd_eng.Vfx.Kabuto_FAR_sounds;
import dcd_eng.Vfx.Kiva_FAR_sounds;
import dcd_eng.Vfx.Kuuga_Dragon_FAR;
import dcd_eng.Vfx.Kuuga_FAR_Background;
import dcd_eng.Vfx.Kuuga_FAR_R_kick;
import dcd_eng.Vfx.Kuuga_FAR_kick;
import dcd_eng.Vfx.Kuuga_Pegasus_FAR;
import dcd_eng.Vfx.Kuuga_Pegasus_FAR2;
import dcd_eng.Vfx.Kuuga_Titan_FAR;
import dcd_eng.Vfx.Ryuki_FAR_sounds;
import java.util.ArrayList;
import java.util.List;

public class FinalAttackRide extends AbstractCustomCardWithType {
   public static final String ID = "FinalAttackRide";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FinalAttackRide.png";
   private static final int COST = 2;
   private static final int ATTACK_DMG = 11;
   private static boolean Kick = true;
   private int FARDCD = 0;
   private int FARDenO = 0;
   private int FARKiva = 0;
   private int t = 0;
   private int s = 0;
   private int FARnumber = 0;
   private final List<TooltipInfo> tips;
   private final List<TooltipInfo> tips2;
   private final List<TooltipInfo> tips3;
   private final AbstractPlayer p;

   public FinalAttackRide() {
      super("FinalAttackRide", NAME, "img/cards/FinalAttackRide.png", 2, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.p = AbstractDungeon.player;
      this.tags.add(DCDmod.RiderCard);
      this.baseDamage = 11;
      this.setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[30], EXTENDED_DESCRIPTION[31]));
      this.tips2 = new ArrayList();
      this.tips2.add(new TooltipInfo(EXTENDED_DESCRIPTION[54], EXTENDED_DESCRIPTION[55]));
      this.tips3 = new ArrayList();
      this.tips3.add(new TooltipInfo(EXTENDED_DESCRIPTION[62], EXTENDED_DESCRIPTION[63]));
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (!this.name.equals(EXTENDED_DESCRIPTION[41])) {
         TurnTimer.StopBGM(false);
      }

      if (!p.hasPower("KamenRideKabutoPower")) {
         CardCrawlGame.sound.playA("FAR", 0.0F);
      }

      switch (this.FARnumber) {
         case 0:
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            break;
         case 1:
            if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
               this.addToTop(new VFXAction(new FAR_SoundsAndAnimation(p.drawX, p.drawY, "Kuuga", true), 3.88F));
               this.addToTop(new VFXAction(new Kuuga_FAR_Background(false, false)));
            }

            if (!p.hasPower("KuugaDragonPower") && !p.hasPower("RisingDragonPower")) {
               if (!p.hasPower("KuugaTitanPower") && !p.hasPower("RisingTitanPower")) {
                  if (p.hasPower("KuugaPegasusPower") || p.hasPower("RisingPegasusPower")) {
                     if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
                        if (p.hasPower("KuugaPegasusPower")) {
                           this.addToBot(new VFXAction(new Kuuga_Pegasus_FAR(p, m, this.damage)));
                        } else {
                           this.addToBot(new VFXAction(new Kuuga_Pegasus_FAR2(p, m, this.damage)));
                        }
                     } else {
                        int x = 2;
                        if (p.hasPower("RisingPegasusPower")) {
                           ++x;
                        }

                        for(int i = 0; i < x; ++i) {
                           for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                              if (!monster.isDead && !monster.isDying) {
                                 this.addToBot(new DamageAction(monster, new DamageInfo(p, this.damage, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));
                              }
                           }
                        }
                     }
                  } else if (p.hasPower("RisingMightyPower")) {
                     if (m.hasPower("KuugaSpecialPower") && m.getPower("KuugaSpecialPower").amount >= 3) {
                        this.damage *= 2;
                     }

                     if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
                        this.addToBot(new VFXAction(new Kuuga_FAR_R_kick(p, m, this.damage)));
                     } else {
                        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                        this.addToBot(new RemoveSpecificPowerAction(m, p, "KuugaSpecialPower"));
                     }
                  } else {
                     if (m.hasPower("KuugaSpecialPower") && m.getPower("KuugaSpecialPower").amount >= 3) {
                        this.damage = (int)((double)this.damage * (double)1.5F);
                     }

                     if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
                        this.addToBot(new VFXAction(new Kuuga_FAR_kick(p, m, this.damage)));
                     } else {
                        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                        this.addToBot(new RemoveSpecificPowerAction(m, p, "KuugaSpecialPower"));
                     }
                  }
               } else if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
                  this.addToBot(new VFXAction(new Kuuga_Titan_FAR(p, m, this.damage)));
               } else {
                  if (p.hasPower("RisingMightyPower")) {
                     this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                  }

                  this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                  this.addToBot(new RemoveSpecificPowerAction(m, p, "KuugaSpecialPower"));
               }
            } else if (m.hasPower("KuugaSpecialPower")) {
               if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
                  this.addToBot(new VFXAction(new Kuuga_Dragon_FAR(p, m, this.damage, m.getPower("KuugaSpecialPower").amount / 3), 0.0F));
               } else {
                  for(int i = 0; i < m.getPower("KuugaSpecialPower").amount / 3; ++i) {
                     this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                  }

                  this.addToBot(new RemoveSpecificPowerAction(m, p, "KuugaSpecialPower"));
               }
            }
            break;
         case 2:
            if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
               if (!p.hasPower("AgitoFlamePower") && !p.hasPower("AgitoStormPower")) {
                  this.addToTop(new VFXAction(new FAR_SoundsAndAnimation(p.drawX, p.drawY, "Agito", true), 3.88F));
                  this.addToBot(new VFXAction(new Agito_FAR_kick(p, m, this.damage), 5.04F));
               } else if (p.hasPower("AgitoFlamePower") && p.hasPower("AgitoStormPower")) {
                  this.addToTop(new VFXAction(new FormRide_FAR_SoundsAndAnimation(p.drawX, p.drawY, "Agito"), 3.88F));
                  this.addToBot(new VFXAction(new Agito_t_FAR_kick(p, m, this.damage), 4.5F));
               } else if (p.hasPower("AgitoFlamePower") && !p.hasPower("AgitoStormPower")) {
                  this.addToTop(new VFXAction(new FormRide_FAR_SoundsAndAnimation(p.drawX, p.drawY, "Agito"), 3.88F));
                  this.addToBot(new VFXAction(new Agito_FAR_Flame(p, m, this.damage), 4.43F));
               } else if (!p.hasPower("AgitoFlamePower") && p.hasPower("AgitoStormPower")) {
                  this.addToTop(new VFXAction(new FormRide_FAR_SoundsAndAnimation(p.drawX, p.drawY, "Agito"), 3.88F));
                  int n = 1;
                  if (p.hasPower("SpecialStormPower") && p.getPower("SpecialStormPower").amount >= 3) {
                     n = p.getPower("SpecialStormPower").amount / 3 + 1;
                  }

                  this.addToBot(new VFXAction(new Agito_FAR_Storm(p, m, this.damage, n), 0.0F));
               }
            } else {
               this.addToTop(new VFXAction(new Agito_FAR_sounds(), 4.0F));
               this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
               this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
               if (p.hasPower("SpecialStormPower") && p.getPower("SpecialStormPower").amount >= 3 && !p.hasPower("AgitoFlamePower") && p.hasPower("AgitoStormPower")) {
                  int x = p.getPower("SpecialStormPower").amount / 3;

                  for(int i = 0; i < x; ++i) {
                     this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                  }

                  AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "SpecialStormPower"));
               }
            }
            break;
         case 3:
            this.addToTop(new VFXAction(new Ryuki_FAR_sounds(), 4.0F));
            if (p.hasPower("DragClawPower") && p.hasPower("DragrederPower")) {
               this.addToBot(new RemoveSpecificPowerAction(p, p, "DragClawPower"));
               this.addToBot(new RyukiAttackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 10, AttackEffect.BLUNT_HEAVY));
            } else {
               this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            }
            break;
         case 4:
            if (SpecialFaizBox.AxelForm) {
               ++SpecialFaizBox.PointerPoint;
               if (SpecialFaizBox.PointerPoint == 1) {
                  this.addToTop(new VFXAction(new Axel_CrimsonSmash_Timer(this.damage), 0.0F));
               }
            } else {
               if (!DCDmod.BGMTrigger) {
                  CardCrawlGame.music.playTempBGM("faiz_BGM2.ogg");
               }

               this.addToTop(new VFXAction(new Faiz_CrimsonSmash(m.drawX, m.drawY, m, this.damage), 0.0F));
               if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
                  this.addToTop(new VFXAction(new Faiz_FAR_SoundsAndAnimation(p.drawX, p.drawY), 4.425F));
               } else {
                  this.addToTop(new VFXAction(new Faiz_FAR_SoundsAndAnimation(p.drawX, p.drawY), 2.0F));
               }
            }
            break;
         case 5:
            if (!this.name.equals(EXTENDED_DESCRIPTION[37]) && !this.name.equals(EXTENDED_DESCRIPTION[39])) {
               if (this.name.equals(EXTENDED_DESCRIPTION[41])) {
                  for(int i = 0; i < 5; ++i) {
                     this.addToTop(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.SLASH_DIAGONAL));
                  }

                  AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "BladeSlashPower"));
               } else if (this.name.equals(EXTENDED_DESCRIPTION[22]) || this.name.equals(EXTENDED_DESCRIPTION[43]) || this.name.equals(EXTENDED_DESCRIPTION[45])) {
                  if (this.name.equals(EXTENDED_DESCRIPTION[43]) || this.name.equals(EXTENDED_DESCRIPTION[45])) {
                     if (this.name.equals(EXTENDED_DESCRIPTION[43])) {
                        this.addToTop(new VFXAction(new Blade_FAR_sounds2(), 4.0F));
                     } else {
                        this.addToTop(new VFXAction(new Blade_FAR_sounds3(), 4.0F));
                     }

                     this.addToBot(new SFXAction("THUNDERCLAP", 0.05F));
                     this.addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
                  }

                  this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
               }
            } else {
               this.addToBot(new SFXAction("ATTACK_HEAVY"));
               this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
               if (this.name.equals(EXTENDED_DESCRIPTION[39])) {
                  this.addToTop(new VFXAction(new Blade_FAR_sounds1(), 4.0F));

                  for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                     if (!monster.isDead && !monster.isDying && !monster.halfDead) {
                        this.addToBot(new SFXAction("THUNDERCLAP", 0.05F));
                        this.addToBot(new VFXAction(new LightningEffect(monster.drawX, monster.drawY), 0.05F));
                     }
                  }
               }

               this.addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), DamageType.NORMAL, AttackEffect.NONE));
            }
            break;
         case 6:
            if (!p.hasPower("HibikiKurenaiPower")) {
               this.addToTop(new VFXAction(new Hibiki_FAR_SoundsAndAnimation(this.damage, DamageType.NORMAL), 0.0F));
            }
            break;
         case 7:
            this.addToTop(new VFXAction(new Kabuto_FAR_sounds(), 4.0F));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            if (p.hasPower("KabutoDexterityPower") || p.hasPower("KabutoStrengthPower")) {
               this.addToBot(new MakeTempCardInHandAction(new Kabuto_Photon(), 1));
            }

            if (p.hasPower("KabutoDexterityPower")) {
               this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
            }
            break;
         case 8:
            this.addToBot(new VFXAction(new DenO_FAR_sounds(), 3.5F));

            for(AbstractCard c : p.hand.group) {
               if (c.hasTag(DCDmod.DenOActionCard) || c.type == CardType.CURSE || c.type == CardType.STATUS) {
                  this.addToBot(new ExhaustSpecificCardAction(c, p.hand));
               }
            }

            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage + this.FARDenO, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage + this.FARDenO, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            break;
         case 9:
            this.addToBot(new VFXAction(new Kiva_FAR_sounds(), 3.5F));
            if (this.name.equals(EXTENDED_DESCRIPTION[59])) {
               int d = m.currentBlock;
               this.addToBot(new DamageAction(m, new DamageInfo(p, d, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));

               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying && !monster.halfDead) {
                     this.addToBot(new DamageAction(monster, new DamageInfo(p, this.damage + this.FARKiva, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                  }
               }
            } else if (this.name.equals(EXTENDED_DESCRIPTION[64])) {
               this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
               this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
               if (this.FARKiva > 0) {
                  this.addToBot(new DamageAction(m, new DamageInfo(p, this.FARKiva, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
               }
            } else if (this.name.equals(EXTENDED_DESCRIPTION[67])) {
               for(int i = 0; i < 8; ++i) {
                  this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
               }

               this.addToBot(new GainBlockAction(m, p, 10));
               if (!m.hasPower("KivaFrozenPower")) {
                  this.addToBot(new ApplyPowerAction(m, p, new KivaFARFrozenPower(m)));
               }
            } else if (this.name.equals(EXTENDED_DESCRIPTION[69])) {
               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying && !monster.halfDead) {
                     this.addToBot(new DamageAction(monster, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.SMASH));

                     for(AbstractPower mp : monster.powers) {
                        if (mp.type.equals(PowerType.BUFF)) {
                           this.addToBot(new RemoveSpecificPowerAction(monster, p, mp.ID));
                        }
                     }

                     this.addToBot(new KivaDoggaFARAction(monster));
                  }
               }
            }
            break;
         case 10:
            if (!DCDmod.AnimationTrigger && !this.purgeOnUse) {
               this.addToTop(new VFXAction(new FAR_SoundsAndAnimation(p.drawX, p.drawY, "Decade", true), 3.88F));
               this.addToBot(new VFXAction(new Decade_FAR_kick(p, m, this.damage + this.FARDCD), 0.0F));
            } else {
               this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage + this.FARDCD, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
               this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage + this.FARDCD, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            }
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (this.name.equals(EXTENDED_DESCRIPTION[45]) && this.p.hasPower("Dexterity") && this.p.getPower("Dexterity").amount > 0) {
         this.damage += this.p.getPower("Dexterity").amount;
         this.isDamageModified = true;
      }

      if (this.p.hasPower("BladeKickPower") && Kick) {
         this.damage += this.damage;
         this.isDamageModified = true;
      }

      if (this.FARnumber == 9 && this.p.hand.group.size() <= 1) {
         this.damage += this.damage;
         this.isDamageModified = true;
      }

      if ((this.name.equals(EXTENDED_DESCRIPTION[64]) || this.name.equals(EXTENDED_DESCRIPTION[59])) && arg0.hasPower("KivaLacerationPower")) {
         this.FARKiva = arg0.getPower("KivaLacerationPower").amount;
         this.initializeDescription();
      } else if ((this.name.equals(EXTENDED_DESCRIPTION[64]) || this.name.equals(EXTENDED_DESCRIPTION[59])) && !arg0.hasPower("KivaLacerationPower")) {
         this.FARKiva = 0;
         this.initializeDescription();
      }

   }

   public AbstractCard makeCopy() {
      return new FinalAttackRide();
   }

   public List<TooltipInfo> getCustomTooltips() {
      if (this.p instanceof Decade && this.FARnumber == 3) {
         return this.tips;
      } else if (this.p instanceof Decade && this.p.hasPower("DecadeViolentEmotionPower")) {
         return this.tips2;
      } else {
         return this.p instanceof Decade && this.FARnumber == 9 ? this.tips3 : null;
      }
   }

   public void optionDecade() {
      this.FARnumber = 10;
      this.loadCardImage("img/cards/FinalAttackRide_Decade.png");
      this.exhaust = true;
      Kick = true;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 11;
      this.rawDescription = EXTENDED_DESCRIPTION[0] + EXTENDED_DESCRIPTION[2] + this.FARDCD + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[1];
      this.name = EXTENDED_DESCRIPTION[21];
      this.initializeDescription();
   }

   public void optionKuuga() {
      this.FARnumber = 1;
      this.target = CardTarget.ENEMY;
      this.exhaust = true;
      this.loadCardImage("img/cards/FinalAttackRide_Kuuga.png");
      if (this.p.hasPower("KuugaDragonPower")) {
         Kick = false;
         this.rawDescription = EXTENDED_DESCRIPTION[4];
         this.baseDamage = 6;
         this.name = EXTENDED_DESCRIPTION[8];
      } else if (this.p.hasPower("KuugaTitanPower")) {
         Kick = false;
         this.rawDescription = EXTENDED_DESCRIPTION[7];
         this.baseDamage = 11 + this.p.getPower("KuugaTitanPower").amount;
         this.name = EXTENDED_DESCRIPTION[9];
      } else if (this.p.hasPower("KuugaPegasusPower")) {
         Kick = false;
         this.baseDamage = 11;
         this.rawDescription = EXTENDED_DESCRIPTION[27];
         this.name = EXTENDED_DESCRIPTION[11];
      } else if (this.p.hasPower("RisingDragonPower")) {
         Kick = false;
         this.rawDescription = EXTENDED_DESCRIPTION[4];
         this.baseDamage = 6;
         this.name = EXTENDED_DESCRIPTION[15];
      } else if (this.p.hasPower("RisingTitanPower")) {
         Kick = false;
         this.rawDescription = EXTENDED_DESCRIPTION[18];
         this.baseDamage = 11 + this.p.getPower("RisingTitanPower").amount + this.p.currentBlock;
         this.name = EXTENDED_DESCRIPTION[12];
         if (this.p.hasPower("RisingMightyPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[53];
            this.name = EXTENDED_DESCRIPTION[52];
         }
      } else if (this.p.hasPower("RisingPegasusPower")) {
         Kick = false;
         this.rawDescription = EXTENDED_DESCRIPTION[16];
         this.name = EXTENDED_DESCRIPTION[14];
      } else if (this.p.hasPower("RisingMightyPower")) {
         if (!this.p.hasPower("RisingTitanPower") && !this.p.hasPower("RisingPegasusPower") && !this.p.hasPower("RisingDragonPower")) {
            Kick = true;
            this.rawDescription = EXTENDED_DESCRIPTION[17];
            this.name = EXTENDED_DESCRIPTION[13];
         }
      } else {
         Kick = true;
         this.baseDamage = 11;
         this.name = EXTENDED_DESCRIPTION[10];
         this.rawDescription = EXTENDED_DESCRIPTION[6];
      }

      this.initializeDescription();
   }

   public void optionAgito() {
      this.FARnumber = 2;
      this.loadCardImage("img/cards/FinalAttackRide_Agito.png");
      this.baseDamage = 11;
      this.target = CardTarget.ENEMY;
      this.exhaust = true;
      if (this.p.hasPower("AgitoStormPower") && !this.p.hasPower("AgitoFlamePower")) {
         Kick = false;
         this.rawDescription = EXTENDED_DESCRIPTION[20];
         this.name = EXTENDED_DESCRIPTION[19];
      } else if (this.p.hasPower("AgitoFlamePower") && !this.p.hasPower("AgitoStormPower")) {
         Kick = false;
         this.rawDescription = EXTENDED_DESCRIPTION[24];
         this.name = EXTENDED_DESCRIPTION[23];
      } else if (this.p.hasPower("AgitoFlamePower") && this.p.hasPower("AgitoStormPower")) {
         Kick = true;
         this.rawDescription = EXTENDED_DESCRIPTION[26];
         this.name = EXTENDED_DESCRIPTION[25];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[24];
         this.name = EXTENDED_DESCRIPTION[22];
      }

      this.initializeDescription();
   }

   public void optionRyuki() {
      this.FARnumber = 3;
      this.loadCardImage("img/cards/FinalAttackRide_Ryuki.png");
      Kick = true;
      this.baseDamage = 20;
      this.target = CardTarget.ENEMY;
      this.exhaust = true;
      if (this.p.hasPower("DragClawPower")) {
         int x = this.p.getPower("DragClawPower").amount * 6;
         this.baseDamage += x;
      }

      if (this.p.hasPower("MirrorWorldPower")) {
         this.baseDamage += 10;
      }

      this.rawDescription = EXTENDED_DESCRIPTION[29];
      this.name = EXTENDED_DESCRIPTION[28];
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.FARnumber = 4;
      this.loadCardImage("img/cards/FinalAttackRide_Faiz.png");
      Kick = true;
      this.baseDamage = 1;
      this.target = CardTarget.ENEMY;
      if (SpecialFaizBox.AxelForm) {
         this.rawDescription = EXTENDED_DESCRIPTION[34];
         this.name = EXTENDED_DESCRIPTION[35];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[33];
         this.name = EXTENDED_DESCRIPTION[32];
      }

      this.initializeDescription();
   }

   public void optionBlade() {
      this.FARnumber = 5;
      this.loadCardImage("img/cards/FinalAttackRide_Blade.png");
      this.name = NAME;
      this.exhaust = false;
      if (!this.p.hasPower("BladeSlashPower") && !this.p.hasPower("BladeKickPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[36];
      } else if ((!this.p.hasPower("BladeSlashPower") || this.p.hasPower("BladeKickPower") || this.p.hasPower("BladeThunderPower") || this.p.hasPower("BladeMachPower")) && (!this.p.hasPower("BladeSlashPower") || this.p.hasPower("BladeKickPower") || !this.p.hasPower("BladeThunderPower") || !this.p.hasPower("BladeMachPower"))) {
         if ((this.p.hasPower("BladeSlashPower") || !this.p.hasPower("BladeKickPower") || this.p.hasPower("BladeThunderPower") || this.p.hasPower("BladeMachPower")) && (this.p.hasPower("BladeSlashPower") || !this.p.hasPower("BladeKickPower") || this.p.hasPower("BladeThunderPower") || !this.p.hasPower("BladeMachPower"))) {
            if (this.p.hasPower("BladeSlashPower") && !this.p.hasPower("BladeKickPower") && this.p.hasPower("BladeThunderPower") && !this.p.hasPower("BladeMachPower")) {
               Kick = false;
               this.baseDamage = 20 + 6 * this.t;
               this.target = CardTarget.ALL_ENEMY;
               this.rawDescription = EXTENDED_DESCRIPTION[40];
               this.name = EXTENDED_DESCRIPTION[39];
            } else if (this.p.hasPower("BladeSlashPower") && !this.p.hasPower("BladeKickPower") && !this.p.hasPower("BladeThunderPower") && this.p.hasPower("BladeMachPower")) {
               Kick = false;
               this.baseDamage = 3 + this.s;
               this.target = CardTarget.ALL_ENEMY;
               this.rawDescription = EXTENDED_DESCRIPTION[42];
               this.name = EXTENDED_DESCRIPTION[41];
            } else if (!this.p.hasPower("BladeSlashPower") && this.p.hasPower("BladeKickPower") && this.p.hasPower("BladeThunderPower") && !this.p.hasPower("BladeMachPower")) {
               Kick = true;
               this.baseDamage = 15 + 3 * this.t;
               this.target = CardTarget.ENEMY;
               this.rawDescription = EXTENDED_DESCRIPTION[44];
               this.name = EXTENDED_DESCRIPTION[43];
            } else if (!this.p.hasPower("BladeSlashPower") && this.p.hasPower("BladeKickPower") && this.p.hasPower("BladeThunderPower") && this.p.hasPower("BladeMachPower")) {
               Kick = true;
               this.baseDamage = 20 + 4 * this.t;
               this.target = CardTarget.ENEMY;
               this.rawDescription = EXTENDED_DESCRIPTION[46];
               this.name = EXTENDED_DESCRIPTION[45];
            }
         } else {
            Kick = true;
            this.baseDamage = 10;
            this.target = CardTarget.ENEMY;
            this.rawDescription = EXTENDED_DESCRIPTION[47];
            this.name = EXTENDED_DESCRIPTION[22];
         }
      } else {
         Kick = false;
         this.baseDamage = 15;
         this.target = CardTarget.ALL_ENEMY;
         this.rawDescription = EXTENDED_DESCRIPTION[38];
         this.name = EXTENDED_DESCRIPTION[37];
      }

      this.initializeDescription();
   }

   public void optionHibiki() {
      this.FARnumber = 6;
      this.loadCardImage("img/cards/FinalAttackRide_Hibiki.png");
      Kick = false;
      this.exhaust = true;
      this.baseDamage = HibikiTaikoKeyEvent.ComboPoint / 3;
      this.target = CardTarget.ALL_ENEMY;
      this.rawDescription = EXTENDED_DESCRIPTION[49];
      this.name = EXTENDED_DESCRIPTION[48];
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.FARnumber = 7;
      Kick = true;
      this.exhaust = true;
      this.loadCardImage("img/cards/FinalAttackRide_Kabuto.png");
      this.baseDamage = 10;
      this.target = CardTarget.ENEMY;
      this.rawDescription = EXTENDED_DESCRIPTION[51];
      this.name = EXTENDED_DESCRIPTION[22];
      this.initializeDescription();
   }

   public void optionDenO() {
      this.FARnumber = 8;
      Kick = false;
      this.exhaust = true;
      this.loadCardImage("img/cards/FinalAttackRide_DenO.png");
      this.baseDamage = 11;
      this.target = CardTarget.ENEMY;
      this.rawDescription = EXTENDED_DESCRIPTION[57] + this.FARDenO + EXTENDED_DESCRIPTION[58];
      this.name = EXTENDED_DESCRIPTION[56];
      this.initializeDescription();
   }

   public void optionKiva() {
      this.FARnumber = 9;
      Kick = false;
      this.exhaust = true;
      this.loadCardImage("img/cards/FinalAttackRide_Kiva.png");
      if (this.p.hasPower("KivaGaruruPower")) {
         this.baseDamage = 5;
         if (this.upgraded) {
            this.baseDamage = 7;
         }

         this.target = CardTarget.ENEMY;
         this.rawDescription = EXTENDED_DESCRIPTION[65] + this.FARKiva + EXTENDED_DESCRIPTION[66];
         this.name = EXTENDED_DESCRIPTION[64];
      } else if (this.p.hasPower("KivaBasshaaPower")) {
         this.baseDamage = 1;
         this.target = CardTarget.ENEMY;
         this.rawDescription = EXTENDED_DESCRIPTION[68];
         this.name = EXTENDED_DESCRIPTION[67];
      } else if (this.p.hasPower("KivaDoggaPower")) {
         this.baseDamage = 15;
         if (this.upgraded) {
            this.baseDamage = 20;
         }

         this.target = CardTarget.ALL_ENEMY;
         this.rawDescription = EXTENDED_DESCRIPTION[70];
         this.name = EXTENDED_DESCRIPTION[69];
      } else {
         Kick = true;
         this.baseDamage = 10;
         this.target = CardTarget.ENEMY;
         this.rawDescription = EXTENDED_DESCRIPTION[60] + this.FARKiva + EXTENDED_DESCRIPTION[61];
         this.name = EXTENDED_DESCRIPTION[59];
      }

      this.initializeDescription();
   }

   public void optionNeutral() {
      this.FARnumber = 0;
      this.loadCardImage("img/cards/FinalAttackRide.png");
      Kick = true;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 11;
      this.rawDescription = DESCRIPTION;
      this.name = NAME;
      this.initializeDescription();
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("KamenRideBladePower") && !p.hasPower("BladeSlashPower") && !p.hasPower("BladeKickPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[36];
         }

         if (p.hasPower("KuugaDragonPower") || p.hasPower("RisingDragonPower")) {
            if (m != null && m.hasPower("KuugaSpecialPower")) {
               if (m.getPower("KuugaSpecialPower").amount < 3) {
                  canUse = false;
                  this.cantUseMessage = EXTENDED_DESCRIPTION[5];
               }
            } else {
               canUse = false;
               this.cantUseMessage = EXTENDED_DESCRIPTION[5];
            }
         }

         if (p.hasPower("KamenRideHibikiPower") && !HibikiTaikoKeyEvent.Fever) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[50];
         }

         return canUse;
      }
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(1);
      }

   }

   public void update() {
      super.update();
      if (this.p != null) {
         int count = 0;
         int DenO = 0;
         super.applyPowers();

         for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.hasTag(DCDmod.RiderCard)) {
               ++count;
               this.FARDCD = count;
            }

            if (!c.hasTag(DCDmod.RiderCard)) {
               ++count;
               this.FARDCD = count;
            }
         }

         if (this.p.hasPower("KamenRideDenOPower")) {
            for(AbstractCard c : this.p.hand.group) {
               if (c.hasTag(DCDmod.DenOActionCard) || c.type == CardType.CURSE || c.type == CardType.STATUS) {
                  ++DenO;
               }
            }

            this.FARDenO = DenO;
         }

         if (this.p.hasPower("BladeThunderPower")) {
            this.t = this.p.getPower("BladeThunderPower").amount;
         }

         if (this.p.hasPower("BladeSlashPower")) {
            this.s = this.p.getPower("BladeSlashPower").amount;
         }
      }

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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("FinalAttackRide");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
