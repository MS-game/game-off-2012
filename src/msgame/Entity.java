package msgame;

import java.awt.Graphics;
import java.util.List;

public class Entity {
    public double x, y;
    public double width, height;
    public double xspeed, yspeed;
    public boolean onground = true;
    public AABB aabb;
    public World world;

    public Entity(World world) {
        this.world = world;
        updateAABB();
    }

    public void move() {
        move(xspeed, yspeed);
    }

    public void updateAABB() {
        if (aabb == null) {
            aabb = new AABB(x, y, width, height);
            return;
        }
        aabb.minX = x;
        aabb.minY = y;
        aabb.maxX = x + width;
        aabb.maxY = y + height;
    }

    public void tick() {
    }

    public void render(Graphics g) {
    }

    public void move(double xadd, double yadd) {
        List<TileInfo> tiles = world.getCollidingTiles(aabb.expand(xadd, yadd));
        double yo = yadd;
        
        for (TileInfo tile : tiles)
            xadd = tile.aabb.solveX(xadd, aabb);
        for (Entity entity : world.entities)
            xadd = entity.aabb.solveX(xadd, aabb);
        aabb.move(xadd, 0);
        
        for (TileInfo tile : tiles)
            yadd = tile.aabb.solveY(yadd, aabb);
        for (Entity entity : world.entities) {
            if (entity != this && ((this instanceof EntityPlayer) && ((EntityPlayer)this).carrying != entity)) {
                yadd = entity.aabb.solveY(yadd, aabb);
            }
        }
        aabb.move(0, yadd);
        
        onground = (yo != yadd && yo > 0) ? true : false;
        x = aabb.minX;
        y = aabb.minY;
        
        updateAABB();
        for (TileInfo tile : tiles) {
            if (tile.aabb.intersects(aabb)) {
                tile.tile.collidingEntity(world, tile.x, tile.y, this);
            }
        }
    }
}
