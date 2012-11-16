package msgame;

import java.awt.Graphics;

public class Entity {
    public double x, y;
    public World world;
    
    public Entity (World world) {
        this.world = world;
    }
    public void tick() {
    }

    public void render(Graphics g) {
    }
}
