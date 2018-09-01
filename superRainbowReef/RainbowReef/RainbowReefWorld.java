package RainbowReef;

import javax.swing.*;

import game.Ball;
import game.Map;
import game.Paddle;
import images.SpriteLoader;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class RainbowReefWorld extends JPanel implements ActionListener, KeyListener 
{
   private static final long serialVersionUID = 1L;
   public static RainbowReefWorld game;
   public static HashMap<String, BufferedImage> images;
   protected int width = 800;
   protected int height = 650;
   private Map map;
   private Ball ball;
   private Paddle paddle;
   private int score;
   private int lives;
   private boolean isRunning;
   private boolean respawn;
   private Timer timer;
   
   protected RainbowReefWorld()
   {
      this.isRunning = true;
      this.respawn = true;
      this.score = 0;
      this.lives = 3;
      this.paddle = new Paddle(310,550,100,30);
      this.ball = new Ball(450,340,20,20,true);
      this.map = new Map("/resources/testLevel.txt");
      images = new HashMap<>();
      this.addKeyListener(this.paddle);
      setFocusable(true);
      this.setFocusTraversalKeysEnabled(false);
      this.timer = new Timer(10,this);
      timer.start();
   }
   
   public void paint(Graphics graphic)
   {
      graphic.drawImage(SpriteLoader.images.get("Background"), 0, 0, this.width, this.height, null);
      this.map.draw(graphic);
      this.ball.draw(graphic);
      this.paddle.draw(graphic);
      graphic.setColor(Color.BLACK);
      graphic.setFont(new Font("Arial", Font.PLAIN, 20));
      graphic.drawString("SCORE: " + this.score, 650, 500);
      graphic.drawString("LIVES: " + this.lives, 650, 550);
      
      if(this.map.getEnemies() <= 0)
      {
         this.addKeyListener(this);
         this.isRunning = false;
         graphic.setColor(Color.BLUE);
         graphic.drawString("WINNER!",260,300);
         
         if(this.map.getFile().equals("/resources/testLevel.txt"))
         {
            graphic.setFont(new Font("Arial", Font.PLAIN, 25));
            graphic.drawString("Press SPACE for next level or ENTER to restart",230,350);
         }
         
         else if(this.map.getFile().equals("/resources/level1.txt"))
         {
                graphic.setFont(new Font("Arial", Font.PLAIN, 25));
                graphic.drawString("WINNER",230,300);
         }
      }
      if (this.lives <= 0)
      {
         this.addKeyListener(this);
         this.isRunning = false;
         graphic.setColor(Color.RED);
         graphic.setFont(new Font("Arial", Font.PLAIN, 25));
         graphic.drawString("GAME OVER! | SCORE: " + this.score,200,300);
         graphic.drawString("Press ENTER to restart",230,350);
        }
        graphic.dispose();
   }
   
   public static RainbowReefWorld getWorld()
   {
      return game;
   }
   
   
   @Override
   public void actionPerformed(ActionEvent e) 
   {
      this.timer.start();
      
      if (this.isRunning)
      {
         this.ball.update();
         if (this.ball.getY() > 600 && this.respawn == true)
         {
            this.lives--;
            this.respawn = false;
            this.ball = new Ball(350, 340, 20, 20, true);
         }
         this.respawn = true;
         this.ball.paddleCollision((int) this.paddle.getX(),
               (int) this.paddle.getY(), this.paddle.getWidth());
         this.map.ballCollision(this.ball);
         
         if (this.ball.isDivisible()) 
         {
            this.ball.setDivisible(false);
            this.ball.setPower(this.ball.getPower() * 2);
            this.ball.setAngleSpeed(this.ball.getAngleSpeed() + 10);
         }
         
         this.map.checkVisible();
         
         if (this.map.isBrickCheck()) 
         {
            this.score = (this.map.getStgBlocks() - this.map.getNumBricks()) * 10;
            this.map.setBrickCheck(false);
         }
         
         if (this.map.isLife()) 
         {
            this.lives++;
            this.map.setLife(false);
         }
         this.repaint();
      }
   }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) 
    {
       int keyCode = e.getKeyCode();
       if (keyCode == KeyEvent.VK_ENTER) 
       {
          System.out.println("Enter pressed");
          this.isRunning = true;
          this.lives = 10;
          this.score = 0;
          this.ball = new Ball(350, 340, 20, 20, true);
          this.paddle = new Paddle(310, 550, 100, 30);
          this.map = new Map("/resources/testLevel.txt");
          this.addKeyListener(this.paddle);
          this.removeKeyListener(this);
          this.setFocusable(true);
          this.setFocusTraversalKeysEnabled(false);
          this.timer.restart();
       }
       
       if (keyCode == KeyEvent.VK_SPACE) 
       {
          this.isRunning = true;
          this.ball = new Ball(350, 340, 20, 20, true);
          this.paddle = new Paddle(310, 550, 100, 30);
          this.map = new Map("/resources/level1.txt");
          this.addKeyListener(this.paddle);
          this.removeKeyListener(this);
          this.setFocusable(true);
          this.setFocusTraversalKeysEnabled(false);
          this.timer.restart();
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    public static void main(String[] args)
    {
       game = new RainbowReefWorld();
       final RainbowReefWorld game = RainbowReefWorld.getWorld();
       JFrame frame = new JFrame("Rainbow Reef");
       frame.addWindowListener(new WindowAdapter() 
       {
          @Override
          public void windowGainedFocus(WindowEvent e) 
          {
             game.requestFocusInWindow();
          }
       });
       
       frame.getContentPane().add("Center", game);
       frame.pack();
       Sound.player("resources/Music.wav", true);
       frame.setSize(game.width, game.height);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}