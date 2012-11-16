package msgame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EntityPlayer extends Entity {
    public InputHandler inputHandler;
    
    public EntityPlayer(World world) {
        super(world);
        inputHandler = world.inputHandler;
    }
    public void tick () {
        boolean up = inputHandler.isKeyDown(KeyEvent.VK_UP) || inputHandler.isKeyDown(KeyEvent.VK_W);
        boolean down = inputHandler.isKeyDown(KeyEvent.VK_DOWN) || inputHandler.isKeyDown(KeyEvent.VK_S);
        boolean left = inputHandler.isKeyDown(KeyEvent.VK_LEFT) || inputHandler.isKeyDown(KeyEvent.VK_A);
        boolean right = inputHandler.isKeyDown(KeyEvent.VK_RIGHT) || inputHandler.isKeyDown(KeyEvent.VK_D);
        if (up) y--;
        if (down) y++;
        if (right) x++;
        if (left) x--;
    }
    public void render (Graphics g) {
        g.setColor(Colors.red);
        g.fillRect((int)x, (int)y, 10, 10);
    }
}
