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
        List<AABB> boxes = world.getBoundingBoxes(aabb.expand(xadd, yadd));
        double yo = yadd;
        
        for (AABB aabb2 : boxes) {
            xadd = aabb2.solveX(xadd, aabb);
        }
        aabb.move(xadd, 0);
        
        for (AABB aabb2 : boxes) {
            yadd = aabb2.solveY(yadd, aabb);
        }
        aabb.move(0, yadd);
        onground = (yo != yadd && yo > 0) ? true : false;
        System.out.println(onground);
        x = aabb.minX;
        y = aabb.minY;
        updateAABB();
    }
}
