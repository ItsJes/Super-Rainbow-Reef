package images;

import java.awt.*;
import java.util.Observable;

public abstract class Sprites 
  extends Observable
{
   private double x;
   private double y;
   private int width;
   private int height;
   protected SpriteLoader images;
   
   public Sprites(double x2, double y2, int width, int height)
   {
      this.x = x2;
      this.y = y2;
      this.width = width;
      this.height = height;
   }
   
   public abstract void draw(Graphics graphic);
   

   public double getX()
   {
      return x;
   }
   
   public void setX(double d)
   {
      this.x = d;
   }
   
   public double getY()
   {
      return y;
   }
   
   public void setY(double y)
   {
      this.y = y;
   }
   
   public int getWidth()
   {
      return width;
   }
   
   public int getHeight()
   {
      return height;
   }
}