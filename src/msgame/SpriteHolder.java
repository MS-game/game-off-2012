package msgame;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteHolder {
    public Image sprites[];
    public int width;

    public SpriteHolder(String ref, int spriteSize) {
        Image spritesheet = loadSprite(ref);
        width = (spritesheet.getWidth(null) / spriteSize);
        sprites = new Image[width * (spritesheet.getHeight(null) / spriteSize)];

        for (int i = 0; i < sprites.length; i++) {
            int x = i % width;
            int y = i - x * width;
            sprites[i] = getAcceleratedImage(spriteSize, spriteSize);
            sprites[i].getGraphics().drawImage(spritesheet, 0, 0, spriteSize,
                    spriteSize, x * spriteSize, y * spriteSize,
                    (x + 1) * spriteSize, (y + 1) * spriteSize, null);
        }
    }

    public Image getSprite(int key) {
        return sprites[key];
    }

    public Image getAcceleratedImage(int width, int height) {
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        return gc.createCompatibleImage(width, height, Transparency.BITMASK);
    }

    public Image loadSprite(String ref) {
        URL url = this.getClass().getClassLoader().getResource(ref);
        BufferedImage sourceImage;
        try {
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        Image image = gc.createCompatibleImage(sourceImage.getWidth(),
                sourceImage.getHeight(), Transparency.BITMASK);
        image.getGraphics().drawImage(sourceImage, 0, 0, null);
        return image;
    }
}
