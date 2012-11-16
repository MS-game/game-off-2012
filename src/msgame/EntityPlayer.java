package msgame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EntityPlayer extends Entity {
    public InputHandler inputHandler;
    
    public EntityPlayer(World world) {
        super(world);
        inputHandler = world.inputHandler;
        x = 20;
        y = 20;
        width = height = 10;
        updateAABB();
    }

    public void tick() {
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
        if (onground) yspeed = 0;
        if (jump && onground)
            yspeed -= 2;
        if (right)
            xspeed += speed;
        if (left)
            xspeed -= speed;
        move();
    }

    public void render(Graphics g) {
        g.setColor(Colors.red);
        g.fillRect((int) x, (int) y, 10, 10);
    }
}
