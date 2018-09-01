package images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class SpriteLoader 
{
   public static HashMap<String, BufferedImage> images = new HashMap<>();
   
   public SpriteLoader()
   {
      loadImages();
   }
   
   private BufferedImage loadImages(String path)
   {
      BufferedImage image = null;
      try
      {
         image = ImageIO.read(getClass().getResource(path));
      }  
      catch(IOException e)
      {
         e.printStackTrace();
         System.exit(1);
      }
      return image;
   }
   
   public void loadImages()
   {
      images.put("Background", loadImages("/resources/Background1.bmp"));
      images.put("Ball", loadImages("/resources/Reef.gif"));
      images.put("Reefer", loadImages("/resources/Katch.gif"));
      images.put("Wall", loadImages("/resources/Wall.gif"));
      images.put("Block1", loadImages("/resources/Block1.gif"));
      images.put("Block2", loadImages("/resources/Block2.gif"));
      images.put("Block3", loadImages("/resources/Block3.gif"));
      images.put("Block4", loadImages("/resources/Block4.gif"));
      images.put("Block5", loadImages("/resources/Block5.gif"));
      images.put("Block6", loadImages("/resources/Block6.gif"));
      images.put("Block_Solid", loadImages("/resources/Block_solid.gif"));
      images.put("HealthBlock", loadImages("/resources/Block_life.gif"));
      images.put("Enemy", loadImages("/resources/Enemy.gif"));
      images.put("powerUp", loadImages("/resources/Block_split.gif"));
    }

}