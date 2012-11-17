package msgame;

public class Tile {
    public int id = 0;
    public boolean passable = false;
    public int textureId = 0;

    public static final Tile air;
    public static final Tile stone;
    public static final Tile antiGravity;

    public Tile(int id) {
        this.id = id;
    }
    public Tile setTextureId(int textureId) {
        this.textureId = textureId;
        return this;
    }

    public Tile setPassable(boolean flag) {
        passable = flag;
        return this;
    }

    static {
        air = new Tile(0).setTextureId(0).setPassable(true);
        stone = new Tile(1).setTextureId(1);
        antiGravity = new TileAntiGravity(2).setTextureId(5);
    }

    public void collidingEntity(World world, int x, int y, Entity entity) {
    }
}
