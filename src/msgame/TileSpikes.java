package msgame;

public class TileSpikes extends Tile {
    public TileSpikes(int id) {
        super(id);
    }
    
    public void collidingEntity(World world, int x, int y, Entity entity) {
        if (entity instanceof EntityPlayer) {
            for (int i = 0; i < 10; i++) {
                world.spawn(new EntityBlood(world, entity));
            }
            world.restartLevel();
        }
    }
}
