package game;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Map 
{
   private BufferedReader level;
   private int width;
   private int height;
   private int numBricks;
   private int stgBlocks;
   private String file;
   private Brick[][] bricks;
   private boolean brickCheck;
   private boolean life;
   private int enemies;
   
   public Map(String file)
   {
      String line;
      this.file = file;
      try
      {
         this.brickCheck = false;
         this.life = false;
         this.enemies = 0;
         this.numBricks = 0;
         this.stgBlocks = 0;
         this.level = new BufferedReader(new InputStreamReader(super.getClass().getResource(this.file).openStream()));
         line = level.readLine();
         this.width = line.length();
         this.height = 0;
         while (line != null)
         {
            this.height++;
            line = level.readLine();
         }
         this.level.close();
         this.bricks = new Brick[this.height][this.width];
         this.level = new BufferedReader(new InputStreamReader(super.getClass().getResource(this.file).openStream()));
         line = this.level.readLine();
         int height = 0;
         while(line != null)
         {
            int blockCount = 0;
            
            for (int i = 0; i < this.width; i++) 
            {
               char c = line.charAt(i);
               
               if (c == '0') 
                  this.bricks[height][i] = new Brick( i * 25, height * 20, 25, 20, true, 0);
               
               if (c == '1') 
               {
                  blockCount++;
                  this.bricks[height][i] = new Brick(blockCount * 50 - 25, height * 20, 50, 20, true, 1);
                  this.numBricks++;
               }
               
               if (c == '2') 
               {
                  blockCount++;
                  this.bricks[height][i] = new Brick(blockCount * 50 - 25, height * 20, 50, 20, true, 2);
                  this.numBricks++;
               }
                    
               if (c == '3') 
               {
                  blockCount++;
                  this.bricks[height][i] = new Brick(blockCount * 50 - 25, height * 20, 50, 20, true, 3);
                  this.numBricks++;
               }
               
               if (c == '4') 
               {
                  blockCount++;
                  this.bricks[height][i] = new Brick(blockCount * 50 - 25, height * 20, 50, 20, true, 4);
                  this.numBricks++;
               }
                    
               if (c == '5') 
               {
                  blockCount++;
                  this.bricks[height][i] = new Brick(blockCount * 50 - 25, height * 20, 50, 20, true, 5);
                  this.numBricks++;
               }
                    
               if (c == '6') 
               {
                  blockCount++;
                  this.bricks[height][i] = new Brick(blockCount * 50 - 25, height * 20, 50, 20, true, 6);
                  this.numBricks++;
               }
                   
               if (c == '7') 
               {
                  blockCount++;
                  this.bricks[height][i] = new Brick(blockCount * 50 - 25, height * 20, 50, 20, true, 10);
                  this.numBricks++;
               }
                    
               if (c == '8') 
               {      
                  blockCount++;
                  this.bricks[height][i] = new Power(blockCount * 50 - 25, height * 20, 50, 20, true, 20);
               }
                    
               if (c == '9') 
               {        
                  blockCount++;
                  this.bricks[height][i] = new Enemy(blockCount * 50 - 25, height * 20, 50, 40, true, 30);
                  this.enemies++;
               }
               
               if (c == 'a') 
               {
                  blockCount++;
                  this.bricks[height][i] = new Brick(blockCount * 50 - 25, height * 20, 50, 20, true, 40);
               }
                    
               if (c == 'b') 
                  blockCount++;
                    
            }
            height++;
            line = this.level.readLine();
         }
         this.level.close();
         this.stgBlocks = this.numBricks;
      }   
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }
   public void ballCollision(Ball ball)
   {
      for (int i = 0; i < this.height; i++)
      {
         for (int j = 0; j < this.width; j++)
         {
            try{
               Brick brick = this.bricks[i][j];
               if (brick != null)
                  brick.ballCollision(ball);
                    
            }   
            catch(Exception e)
            {
               e.printStackTrace();
            }
         }
      }
   }

   public void draw(Graphics graphic)
   {
      for (int i = 0; i < this.height; i++)
      {
         for (int j = 0; j < this.width; j++)
         {
            try{
               Brick brick = this.bricks[i][j];
               if (brick != null)
                  brick.draw(graphic);
                    
            } 
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      }
   }
   
   public void checkVisible()
   {
      for (int i = 0; i < this.height; i++)
      {
         for (int j = 0; j < this.width; j++)
         {
            try
            {
               Brick brick = this.bricks[i][j];
               if(brick != null)
               {
                  if (brick.getTier() != 0 && brick.getHealth() <= 0)
                  {
                     if (brick.getTier() == 30 && brick.isVisible())
                     {
                        this.enemies--;
                     }
                     if (brick.isDisappear() && !brick.isVisible() && brick.getTier() != 30 && brick.getTier() != 20 && brick.getTier() != 40)
                     {
                        this.numBricks--;
                        this.brickCheck = true;
                        brick.setDisappear(false);
                     }
                     brick.checkVisible();
                     if (!brick.isVisible() && brick.isDisappear() && brick.getTier() == 40)
                     {
                        this.life = true;
                        brick.setDisappear(false);
                     }
                  }
               }
            }   
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      }
   }
   
   public int getEnemies()
   {
      return enemies;
   }
   
   public String getFile()
   {
      return file;
   }
   
   public boolean isBrickCheck() 
   {
      return brickCheck;
   }
   
   public void setBrickCheck(boolean brickCheck) 
   {
      this.brickCheck = brickCheck;
   }

    public int getStgBlocks() 
    {
        return stgBlocks;
    }

    public int getNumBricks()
    {
       return numBricks;
    }

    public boolean isLife() 
    {
       return life;
    }

    public void setLife(boolean life) 
    {
       this.life = life;
    }
}
