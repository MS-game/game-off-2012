package msgame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EntityPlayer extends Entity {
    public InputHandler inputHandler;
    public int walkingAnimation;
    public boolean left;

    public EntityPlayer(World world) {
        super(world);
        inputHandler = world.inputHandler;
        x = 20;
        y = 20;
        width = height = 10;
        walkingAnimation = 0;
        updateAABB();
    }

    public void tick() {
        if (walkingAnimation++ >= 39) walkingAnimation = 0;
        
        boolean jump = inputHandler.isKeyDown(KeyEvent.VK_UP)
                || inputHandler.isKeyDown(KeyEvent.VK_W)
                || inputHandler.isKeyDown(KeyEvent.VK_SPACE);
        boolean left = inputHandler.isKeyDown(KeyEvent.VK_LEFT)
                || inputHandler.isKeyDown(KeyEvent.VK_A);
        boolean right = inputHandler.isKeyDown(KeyEvent.VK_RIGHT)
                || inputHandler.isKeyDown(KeyEvent.VK_D);

        double speed = 1.0;
        xspeed = 0;
        yspeed += 0.05;
        if (onground)
            yspeed = 0;
        if (jump && onground)
            yspeed -= 2;
        if (right)
            xspeed += speed;
        if (left)
            xspeed -= speed;
        if (xspeed == 0)
            walkingAnimation = 0;
        if (xspeed > 0)
            this.left = true;
        else if (xspeed < 0)
            this.left = false;
        move();
    }

    public void render(Graphics g) {
        g.drawImage(world.spriteholder.getSprite(
                10 + (int) Math.floor((walkingAnimation / 20))),
                (int) x, (int) y,
                (int) (x + 10), (int) (y + 10), 
                (left) ? 0 : 10, 0,
                (left) ? 10 : 0, 10, null);
    }
}
