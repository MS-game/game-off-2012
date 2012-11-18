package msgame;

import java.awt.Graphics;

public class EntityBox extends Entity {
    public TileInfo antiGravity;
    public double ty, tx;

    public EntityBox(World world, double x, double y) {
        super(world);
        this.x = x;
        this.y = y;
        height = width = 10;
        updateAABB();
    }

    public void tick() {
        xspeed = 0;
        if (ty != 0) {
            yspeed += ty;
            if (Math.abs(ty) < 0.05)
                ty = 0;
            else
                ty -= 0.05 * Math.signum(ty);
        }
        if (tx != 0) {
            xspeed += tx;
            if (Math.abs(tx) < 0.05)
                tx = 0; // Stupid floating point errors :(
            else
                tx -= 0.05 * Math.signum(tx);
        }
        if (world.thePlayer.carrying != this) {
            yspeed += 0.05;
            if (antiGravity != null) {
                if (!antiGravity.aabb.intersects(aabb.expand(0, 16)))
                    antiGravity = null;
                else {
                    yspeed = 0;
                    if (antiGravity.aabb.intersects(aabb.expand(0, 15)))
                        yspeed = -1;
                }
            }
        } else {
            ty = tx = 0;
        }
        if (world.thePlayer.carrying != this
                && world.thePlayer.aabb.intersects(aabb)
                && aabb.solveY(1, world.thePlayer.aabb) > 0) {
            if (world.thePlayer.aabb.minX < aabb.minX)
                xspeed += 1;
            else if (world.thePlayer.aabb.maxX > aabb.maxX)
                xspeed += -1;
            else if (tx == 0 && ty == 0 && world.thePlayer.carrying == null
                    && world.thePlayer.aabb.minY >= (aabb.maxY)) {
                world.thePlayer.carrying = this;
                world.thePlayer.carryingX = x - world.thePlayer.x;
                world.thePlayer.carryingY = y - world.thePlayer.y;
                xspeed = 0;
            }
        } else if (world.thePlayer.carrying == this
                && (world.thePlayer.aabb.minY < (aabb.maxY))) {
            world.thePlayer.carrying = null;
        }
        move();
        if (onground)
            yspeed = 0;
    }

    public void render(Graphics g) {
        g.drawImage(world.spriteholder.getSprite(3), (int) x, (int) y,
                (int) (x + 10), (int) (y + 10), 0, 0, 10, 10, null);
    }
}
