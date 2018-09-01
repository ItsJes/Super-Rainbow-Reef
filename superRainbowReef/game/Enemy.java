package game;

import java.awt.*;

import images.SpriteLoader;

public class Enemy 
  extends Brick 
{
   Enemy(double x, double y, int width, int height, boolean visible, int tier)
   {
      super(x,y,width,height,visible,tier);
   }
   
   public void ballCollision(Ball ball) 
   {
      Rectangle ballBox = new Rectangle((int)ball.getX(),(int) ball.getY(),
            ball.getWidth(),ball.getHeight());
        
      Rectangle blockBox = new Rectangle((int)this.getX(), (int)this.getY(),
            this.getWidth(),this.getHeight());
      
      if(this.isVisible() && ballBox.intersects(blockBox)) 
      {
         if(this.getTier() != 10) 
            this.setHealth(this.getHealth() - 10);
         
         ball.setYdir(-ball.getYdir());
         ball.setXdir(-ball.getXdir());
      }
   }
   
   public void draw(Graphics g) 
   {
      if(this.getTier() == 30 && this.isVisible())
         g.drawImage(SpriteLoader.images.get("Enemy"), (int)this.getX(), (int)this.getY(),
               this.getWidth(), this.getHeight(), null);
   }
   
   public void checkVisible() 
   {
      if(this.getHealth() <= 0)
         this.setVisible(false);
   }
}
