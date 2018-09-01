package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import images.SpriteLoader;
import images.Sprites;

public class Paddle 
  extends Sprites 
  implements KeyListener 
{
   private int xDir;
   
   public Paddle(double x, double y, int width, int height)
   {
      super(x,y,width,height);
      this.xDir = 100;
   }
   
   private void moveRight()
   {
      this.setX(this.getX() + this.xDir);
   }

   private void moveLeft()
    {
       this.setX(this.getX() - this.xDir);
    }
    
    public void keyPressed (KeyEvent e)
    {
       int keyCode = e.getKeyCode();
       switch( keyCode ) 
       { 
           case KeyEvent.VK_LEFT:
               moveLeft();
               break;
           case KeyEvent.VK_RIGHT :
               moveRight();
               break;
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
       int keyCode = e.getKeyCode();
       switch( keyCode ) 
       { 
           case KeyEvent.VK_LEFT:
               // handle left
               break;
           case KeyEvent.VK_RIGHT :
               // handle right
               break;
        }
        
    }

    public void keyTyped(KeyEvent e) {}

    public void draw(Graphics graphic)
    {
       graphic.drawImage(SpriteLoader.images.get("Reefer"), (int)this.getX(),(int) this.getY(),
             this.getWidth(), this.getHeight(), null);
    }

}
