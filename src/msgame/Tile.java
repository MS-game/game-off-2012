package msgame;

public class Tile {
    public int id;
    
    public static final Tile air;
    public static final Tile stone;
    
    public Tile (int id)
    {
        this.id = id;
    }
    static {
        air = new Tile(0);
        stone = new Tile(1);
    }
}
