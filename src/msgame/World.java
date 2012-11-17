package msgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class World {
    public Main main;
    public Tile[][] tiles;
    public List<Entity> entities;
    public EntityPlayer thePlayer;
    public InputHandler inputHandler;
    public SpriteHolder spriteholder;
    public int currentLevel;

    public World(Main main) {
        this.main = main;
        tiles = new Tile[24][18];
        inputHandler = main.inputHandler;
        spriteholder = new SpriteHolder("res/sprites.png", 10);        
        currentLevel = 0;
        loadLevel(currentLevel);
    }

    public void spawn(Entity entity) {
        entities.add(entity);
    }

    public void render(Graphics g) {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                g.drawImage(spriteholder.getSprite(tiles[x][y].textureId),
                        x * 10, y * 10, (x + 1) * 10, (y + 1) * 10, 0, 0, 10,
                        10, null);
            }
        }
        for (Entity entity : entities) {
            entity.render(g);
        }
    }

    public void tick() {
        for (Entity entity : entities) {
            entity.tick();
        }
    }
    public void nextLevel () {
        loadLevel(++currentLevel);
    }
    public void spawnThePlayer (double x, double y) {
        thePlayer = new EntityPlayer(this, x, y);
        spawn(thePlayer);
    }
    public void loadLevel(int l) {
        entities = new ArrayList<Entity>();
        try {
            BufferedImage image = ImageIO.read(World.class
                    .getResourceAsStream("/res/level" + l + ".png"));
            int[] pixels = image.getRGB(0, 0, image.getWidth(),
                    image.getHeight(), null, 0, image.getWidth());

            for (int i = 0; i < pixels.length; i++) {
                int pixel = pixels[i] & 0xFFFFFF;
                int y = (int) Math.floor(i / image.getWidth());
                int x = i - (y * image.getWidth());
                tiles[x][y] = Tile.air;
                if (pixel == 0xFFFFFF) {
                } else if (pixel == 0xFF0000) {
                    tiles[x][y] = Tile.stone;
                } else if (pixel == 0x00FF00) {
                    spawn(new EntityBox(this, x*10, y*10));
                } else if (pixel == 0xFF00FF) {
                    spawn(new EntityDoor(this, x*10, y*10));
                } else if (pixel == 0x00FFFF) {
                    spawnThePlayer(x * 10, y * 10);
                } else {
                    throw new Exception("Unkown pixel " + pixel + " at level "
                            + l + ", " + x + "," + y);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public boolean isTileAt(int x, int y) {
        if (x < 0 || y < 0 || x >= tiles.length || y >= tiles[0].length)
            return false;
        return !tiles[x][y].passable;
    }

    public List<AABB> getBoundingBoxes(AABB aabb) {
        List<AABB> ret = new ArrayList<AABB>();

        int sx = (int) Math.floor(aabb.minX);
        sx = sx - (sx % 10);
        int sy = (int) Math.floor(aabb.minY);
        sy = sy - (sy % 10);
        for (int x = sx; x <= aabb.maxX + 10; x += 10) {
            for (int y = sy; y <= aabb.maxY + 10; y += 10) {
                if (isTileAt(x / 10, y / 10))
                    ret.add(new AABB(x, y, x + 10, y + 10));
            }
        }
        return ret;
    }

    public Tile getTileAt(int x, int y) {
        if (x < 0 || y < 0 || x >= tiles.length || y >= tiles[0].length)
            return null;
        return tiles[x][y];
    }
    public void setTileAt(int x, int y, Tile tile) {
        if (x < 0 || y < 0 || x >= tiles.length || y >= tiles[0].length)
            return;
        tiles[x][y] = tile;
    }
    public List<TileInfo> getCollidingTiles(AABB aabb) {
        List<TileInfo> ret = new ArrayList<TileInfo>();

        int sx = (int) Math.floor(aabb.minX);
        sx = sx - (sx % 10);
        int sy = (int) Math.floor(aabb.minY);
        sy = sy - (sy % 10);
        for (int x = sx; x <= aabb.maxX + 10; x += 10) {
            for (int y = sy; y <= aabb.maxY + 10; y += 10) {
                if (isTileAt(x / 10, y / 10)) {
                    TileInfo tileinfo = new TileInfo(getTileAt(x / 10, y / 10));
                    tileinfo.aabb = new AABB(x, y, x + 10, y + 10);
                    tileinfo.x = x / 10;
                    tileinfo.y = y / 10;
                    ret.add(tileinfo);
                }
            }
        }
        return ret;
    }
}
