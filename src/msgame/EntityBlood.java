package msgame;

import java.awt.Graphics;

public class EntityBlood extends Entity {
    public int live;

    public EntityBlood(World world, Entity entity) {
        super(world);

        live = (50 + world.random.nextInt(40));
        this.x = entity.x + (world.random.nextDouble() * entity.width);
        this.y = entity.y + (world.random.nextDouble() * entity.height);
        keep = true;
        colliding = false;
        xspeed = (world.random.nextFloat() - 0.5) * 2;
        yspeed = world.random.nextFloat() * -1;

        width = world.random.nextInt(5) + 1;
        height = world.random.nextInt(5) + 1;
        updateAABB();
    }

    public void tick() {
        if (--live <= 0) {
            dead = true;
            return;
        }
        xspeed *= 0.85;
        yspeed += 0.08;
        move();
    }

    public void render(Graphics g) {
        g.setColor(Colors.red);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }
}
