package msgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
    public Main main;
    public Tile[][] tiles;

    public World(Main main) {
        this.main = main;
        tiles = new Tile[24][18];
    }

    public void render(Graphics g) {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                g.setColor(new Color(tiles[x][y].color));
                g.fillRect(x * 10, y * 10, 10, 10);
            }
        }
    }

    public void loadLevel(int l) {
        try {
            BufferedImage image = ImageIO.read(World.class
                    .getResourceAsStream("res/level" + l + ".png"));
            // BufferedImage image = ImageIO.read(new File("/res/level" + l +
            // ".png"));
            int[] pixels = image.getRGB(0, 0, image.getWidth(),
                    image.getHeight(), null, 0, image.getWidth());

            for (int i = 0; i < pixels.length; i++) {
                int pixel = pixels[i] & 0xFFFFFF;
                int y = (int) Math.floor(i / image.getWidth());
                int x = i - (y * image.getWidth());
                if (pixel == 0xFFFFFF) {
                    tiles[x][y] = Tile.air;
                } else if (pixel == 0xFF0000) {
                    tiles[x][y] = Tile.stone;
                } else {
                    throw new Exception("Unkown pixel " + pixel + " at level "
                            + l + ", " + x + "," + y);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
