package game;

import java.awt.*;

import images.SpriteLoader;

public class Power 
  extends Brick 
{
   Power(int x, int y, int width, int height, boolean visible, int tier)
   {
      super(x,y,width,height,visible,tier);
   }
   
   public void ballCollision(Ball ball)
   {
      Rectangle ballBounds = new Rectangle((int) ball.getX(), (int) ball.getY(), 
            ball.getWidth(), ball.getHeight());
      
      Rectangle blockBounds = new Rectangle((int) this.getX(), (int) this.getY(),
            this.getWidth(), this.getHeight());
      
      if(this.isVisible() && ballBounds.intersects(blockBounds))
      {
         if (this.getTier() != 10)
         {
            this.setHealth(this.getHeight() - 10);
            
            if(this.getHealth() <= 0)
            {
               ball.setDivisible(true);
            }
         }
         ball.setYdir(-ball.getYdir());
         ball.setXdir(-ball.getXdir());
      }
   }
   
   public void checkVisible()
   {
      if(!(this.getHealth() <= 0))
          this.setVisible(true);
   }
   
   public void draw(Graphics g)
   {
      if(this.getTier() == 20 && this.isVisible())
      {
         g.drawImage(SpriteLoader.images.get("powerUp"),(int)this.getX(), (int)this.getY(), 
               this.getWidth(), this.getHeight(), null);
      }
   }
}
