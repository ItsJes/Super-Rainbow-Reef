package game;

import java.awt.*;
import java.awt.geom.AffineTransform;

import images.SpriteLoader;
import images.Sprites;

public class Ball 
  extends Sprites 
{
   private int power;
   private int angle;
   private int angleSpeed;
   private double speed;
   private double x;
   private double y;
   private boolean visible;
   private boolean divisible;
   
   public Ball(double x, double y, int width, int height, boolean visible)
   {
      super(x,y,width,height);
      this.power = 10;
      this.angle = 0;
      this.angleSpeed = 10;
      this.speed = 2.5f;
      this.x = -2;
      this.y = -4;
      this.visible = visible;
      this.divisible = false;
   }

    public void paddleCollision(int playerX, int playerY, int playerWidth) 
    {
       Rectangle playerBox = new Rectangle(playerX, playerY, playerWidth / 2, 1);
       
       Rectangle ballBox = new Rectangle((int) this.getX(), (int) this.getY(), this.getWidth(), this.getHeight());
       if (playerBox.intersects(ballBox)) 
       {
          this.y += 0.5;
          if (this.y > 7.5)
             this.y = 4.5;
          this.y *= -1;
          this.x = -2;
          this.x -= 0.1;
       }
       
       playerBox = new Rectangle(playerX + playerWidth / 2, playerY, playerWidth / 2, 1);
       
       if(playerBox.intersects(ballBox)) 
       {
          this.y += 0.7;
          if (this.y > 7.5)
             this.y = 4.5;
          this.y *= -1;
       }
       
       playerBox = new Rectangle(playerX + 2 * playerWidth / 2, playerY, playerWidth / 2, 1);
       
       if(playerBox.intersects(ballBox)) 
       {
          this.y += 0.5;
          if (this.y > 7.5)
             this.y = 4.5;
          this.y = this.y * -1;
          this.x = 2;
          this.x += 0.1;
       }
    }
    
    public void update()
    {
       if(this.visible) 
       {
          this.angle += this.angleSpeed;
          this.setX(this.getX() + this.x);
          this.setY(this.getY() + this.y);
          this.y += 0.03;
       }
       
       if(this.getX() < 25) 
       {
          this.x *= -1;
          this.setX(26);
       }
       
       if(this.getY() < 20) 
       {
          this.y *= -1;
          this.setY(21);
       }
       
        if(this.getX() > 750) 
        {
           this.x *= -1;
           this.setX(749);
        }
    }
    
    public void draw(Graphics graphic)
    {
       if (this.visible) 
       {
          AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX(), this.getY());
          rotation.rotate(Math.toRadians(this.angle), 
                SpriteLoader.images.get("Ball").getWidth() / 2, SpriteLoader.images.get("Ball").getHeight() / 2);
          
          Graphics2D graphic2D = (Graphics2D) graphic;
          graphic2D.drawImage(SpriteLoader.images.get("Ball"), rotation, null);
       }
    }

    public void setXdir(double x)
    {
       this.x = x;
    }

    public void setYdir(double d)
    {
       this.y = d;
    }

    public double getYdir()
    {
       return y;
    }

    public double getXdir()
    {
       return x;
    }

    public void setDivisible(boolean divisible)
    {
       this.divisible = divisible;
    }

    public int getPower()
    {
        return power;
    }

    public boolean isDivisible()
    {
       return divisible;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public void setAngleSpeed(int angleSpeed)
    {
       this.angleSpeed = angleSpeed;
    }

    public int getAngleSpeed()
    {
       return angleSpeed;
    }
}
