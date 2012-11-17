package msgame;

import java.awt.Graphics;

public class EntityBox extends Entity {
    public EntityBox(World world, double x, double y) {
        super(world);
        this.x = x;
        this.y = y;
        height = width = 10;
        updateAABB();
    }
    public void tick () {
        if (world.thePlayer.aabb.intersects(aabb) && aabb.solveY(1, world.thePlayer.aabb) > 0) {
            move(((world.thePlayer.x > x) ? -1 : 1), 0);
        }
    }
    public void render (Graphics g) {
        g.drawImage(world.spriteholder.getSprite(3), (int) x, (int) y,
                (int) (x + 10), (int) (y + 10), 0, 0, 10, 10, null);
    }
}
