package msgame;

public class Tile {
    public int id = 0;
    public boolean transparent = false;
    public boolean passable = false;
    public int textureId = 0;

    public static final Tile air;
    public static final Tile stone;

    public Tile(int id) {
        this.id = id;
    }

    public Tile setTransparent(boolean flag) {
        transparent = flag;
        return this;
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
        air = new Tile(0).setTransparent(true).setPassable(true);
        ;
        stone = new Tile(1).setTextureId(0);
    }
}
