package msgame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
    public Main main;
    public Tile[][] tiles;
    
    public World (Main main)
    {
        this.main = main;
        tiles = new Tile[24][18];
    }
    public void loadLevel (int l)
    {
        /*try {
            BufferedImage image = ImageIO.read(new File("res/level" + l + ".png"));
            int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, 0);
            
            for (int i = 0; i < pixels.length; i++) {
                int pixel = pixels[i];
                int y = (int) Math.floor(i / image.getWidth());
                int x = i - (y * image.getHeight());
                if (pixel == 0xFFFFFF) {}
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }*/
    }
}
