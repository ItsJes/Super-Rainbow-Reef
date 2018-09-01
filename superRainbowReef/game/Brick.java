package game;

import java.awt.*;

import images.SpriteLoader;

public class Brick 
  extends Block 
{
   private int block;
   private int health;
   private boolean disappear;
   
   Brick(double x, double y, int width, int height, boolean visible, int tier)
   {
      super(x,y,width,height,visible);
      this.block = tier;
      this.disappear = false;
      
      if (this.block == 20 || this.block == 30 || this.block == 40)
         this.health = 10;
      
      else
         this.health = this.block * 5;
        
   }
   
   public void ballCollision(Ball ball)
   {
      Rectangle ballBox = new Rectangle((int) ball.getX(), (int) ball.getY(),
            ball.getWidth(),ball.getHeight());
      
      Rectangle blockBox = new Rectangle((int) this.getX(), (int) this.getY(),
            this.getWidth(), this.getHeight());
      
      if (this.isVisible() && ballBox.intersects(blockBox)) 
      {
         if (this.block != 10)
            this.health -= ball.getPower();
         
         ball.setXdir(ball.getXdir() * -1);
         ball.setYdir(ball.getYdir() * -1);
      }
   }
   
   public void checkVisible()
   {
      if (this.health <= 0 && this.isVisible())
      {
         this.setVisible(false);
         this.disappear = true;
      }
   }
   
   public void draw(Graphics graphics) 
   {
      if(this.block == 0 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("Wall"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
      
      if(this.block == 1 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("Block1"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
      
      if(this.block == 2 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("Block2"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
      
      if(this.block == 3 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("Block3"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
      
      if(this.block == 4 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("Block4"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
      
      if(this.block == 5 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("Block5"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
        
      if(this.block == 6 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("Block6"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
        //unbreakable wall
      if(this.block == 10 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("Block_Solid"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
        
      // healing
      if(this.block == 40 && this.isVisible())
         graphics.drawImage(SpriteLoader.images.get("HealthBlock"), (int) this.getX(), (int) this.getY(),
               this.getWidth(), this.getHeight(), null);
    }

    public int getTier()
    {
       return this.block;
    }

    public void setHealth(int health)
    {
       this.health = health;
    }

    public int getHealth()
    {
       return health;
    }

    public boolean isDisappear()
    {
       return disappear;
    }

    public void setDisappear(boolean disappear)
    {
       this.disappear = disappear;
    }
}
