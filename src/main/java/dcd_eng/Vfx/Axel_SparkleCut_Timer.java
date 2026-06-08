package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Power.XuanyunPower;

public class Axel_SparkleCut_Timer extends AbstractGameEffect {
   private float x;
   private float y;
   private Texture img;
   private int damage;
   private boolean Start = true;
   private boolean Final = false;
   private int n = 0;

   public Axel_SparkleCut_Timer(int d) {
      this.img = new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
      AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(true);
      if (m != null) {
         this.x = m.drawX;
         this.y = m.drawY;
      }

      this.damage = d;
      this.duration = 30.0F;
      this.startingDuration = 30.0F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (!SpecialFaizBox.AxelForm && this.Start) {
         for(int i = 0; i < SpecialFaizBox.EdgePoint; ++i) {
            if (this.n != SpecialFaizBox.EdgePoint) {
               ++this.n;
            }
         }

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new XuanyunPower(monster), 1));
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_SparkleCut(monster.drawX, monster.drawY), 0.0F));
            }
         }

         AbstractDungeon.actionManager.addToTop(new SwordBoomerangAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(AbstractDungeon.player, this.damage), SpecialFaizBox.EdgePoint));
         this.Start = false;
         this.Final = true;
      }

      if (this.Final && this.n == SpecialFaizBox.EdgePoint && this.duration < 30.0F - (float)SpecialFaizBox.EdgePoint * 2.0F) {
         this.dispose();
         this.isDone = true;
      }

      if (this.duration < 0.0F) {
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      sb.setColor(this.color);
      sb.draw(this.img, this.x, this.y);
   }

   public void dispose() {
      this.img = null;
   }
}
