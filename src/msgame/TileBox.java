package msgame;

public class TileBox extends Tile {
    public TileBox(int id) {
        super(id);
    }
    
    public void collidingEntity(World world, int x, int y, Entity entity) {
        if (entity instanceof EntityPlayer) {
            int newx = x + 1;
            if ((x * 10) > entity.x) newx = x - 1;
            
            Tile tile = world.getTileAt(newx, y);
            if (tile != null && tile.id == Tile.air.id) {
                world.setTileAt(x, y, Tile.air);
                world.setTileAt(newx, y, this);
            }
        }
    }
}
