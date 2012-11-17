package msgame;

import java.awt.Graphics;

public class EntityDoor extends Entity {
    public EntityDoor(World world, double x, double y) {
        super(world);

        this.x = x;
        this.y = y;
        this.height = 10;
        this.width = 10;
        updateAABB();
    }

    public void tick() {
        if (world.thePlayer.aabb.intersects(aabb))
            world.nextLevel();
    }

    public void render(Graphics g) {
        g.drawImage(world.spriteholder.getSprite(2), (int) x, (int) y,
                (int) (x + 10), (int) (y + 10), 0, 0, 10, 10, null);
    }
}
