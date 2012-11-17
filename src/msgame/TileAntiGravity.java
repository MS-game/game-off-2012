package msgame;

public class TileAntiGravity extends Tile {
    public TileAntiGravity(int id) {
        super(id);
    }
    
    public void collidingEntity(World world, int x, int y, Entity entity) {
        if (entity instanceof EntityBox) {
            EntityBox box = (EntityBox) entity;
            box.antiGravity = new TileInfo(this);
            box.antiGravity.x = x;
            box.antiGravity.y = y;
            box.antiGravity.aabb = new AABB(x*10, y*10, (x+1)*10, (y+1)*10);
        }
    }
}
