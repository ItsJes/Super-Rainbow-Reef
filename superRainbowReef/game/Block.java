package game;

import images.SpriteLoader;
import images.Sprites;

public abstract class Block 
extends Sprites 
{
   private boolean visible;
   SpriteLoader images;
   Block(double x, double y, int width, int height, boolean visible)
   {
      super(x,y,width,height);
      images = new SpriteLoader();
      this.visible = visible;
   }
   public abstract void ballCollision(Ball ball);

   public abstract void checkVisible();

   public boolean isVisible()
   {
      return visible;
   }
   
   public void setVisible(boolean visible)
   {
      this.visible = visible;
   }
}
