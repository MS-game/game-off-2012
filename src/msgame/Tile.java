package msgame;

public class Tile {
    public int id = 0;
    public boolean transparent = false;
    public int color = 0xFFFF00;
    
    public static final Tile air;
    public static final Tile stone;

    public Tile(int id) {
        this.id = id;
    }
    public Tile setTransparent (boolean flag)
    {
        transparent = flag;
        return this;
    }
    public Tile setColor (int color)
    {
        this.color = color;
        return this;
    }
    static {
        air = new Tile(0).setTransparent(true);
        stone = new Tile(1).setColor(0x888888);
    }
}
