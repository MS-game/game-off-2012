package msgame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EntityPlayer extends Entity {
    public InputHandler inputHandler;
    public int animation;
    public boolean left, walking;
    public EntityBox carrying;
    public double carryingX, carryingY;

    public EntityPlayer(World world, double x, double y) {
        super(world);
        inputHandler = world.inputHandler;
        this.x = x;
        this.y = y;
        height = 10;
        width = 7;
        animation = 0;
        left = walking = false;
        updateAABB();
    }

    public void tick() {
        if (animation++ >= 39) animation = 0;
        
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
        walking = (xspeed != 0);
        if (xspeed > 0)
            this.left = false;
        else if (xspeed < 0)
            this.left = true;
        move();
        System.out.println(carrying);
        if (carrying != null) {
            carrying.move((x + carryingX - carrying.x), (y + carryingY - carrying.y));
        }
    }

    public void render(Graphics g) {
        int sid = 10 + (int) Math.floor((animation / 20));
        if (walking) sid++;
        g.drawImage(world.spriteholder.getSprite(
                sid),
                (int) x, (int) y,
                (int) (x + width), (int) (y + 10), 
                (left) ? (int)width : 0, 0,
                (left) ? 0 : (int)width, 10, null);
    }
}
