package msgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Main implements Runnable {
    /** Ticks per second */
    public static final int TPS = 60;
    /** Frames per second */
    public static final int FPS = 60;
    public static final String TITLE = "Tha best game ever";
    public static final int WIDTH = 240;
    public static final int HEIGHT = 180;
    public static final int SCALE = 3;

    public static boolean running;
    public int fpsNow;
    public GameCanvas gameCanvas;
    public static JFrame frame;

    public InputHandler inputHandler;
    public World world;

    public Main(boolean frame) {
        initDisplay(frame);
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void init() {
        initScene();
        startLoop();
    }

    public void stop() {
        running = false;
    }

    public void initDisplay(boolean frame) {
        gameCanvas = new GameCanvas();
        gameCanvas.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        gameCanvas.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        gameCanvas.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        if (frame) {
            Main.frame = new JFrame(Main.TITLE);
            Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.frame.setLayout(new BorderLayout());
            Main.frame.add(gameCanvas, BorderLayout.CENTER);
            Main.frame.pack();
            Main.frame.setResizable(false);
            Main.frame.setLocationRelativeTo(null);
            Main.frame.setVisible(true);
        }
    }

    public void initScene() {
        inputHandler = new InputHandler(this);
        world = new World(this);
    }

    public void startLoop() {
        long lastTime = System.currentTimeMillis();
        double unprocessed = 0;
        int fps = 0;
        long fpsCounter = lastTime;

        while (running) {
            long now = System.currentTimeMillis();
            long delta = (now - lastTime);
            lastTime = now;
            unprocessed += delta / (1000.0 / TPS);
            
            boolean ticked = false;
            while (unprocessed > 1) {
                ticked = true;
                if (gameCanvas.hasFocus()) tick();
                unprocessed--;
            }
            if (ticked) {
                render();
                gameCanvas.endRender();
                fps++;
            }
            if ((now - fpsCounter) > 1000) {
                fpsCounter += 1000;
                fpsNow = fps;
                fps = 0;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void render() {
        Graphics g = gameCanvas.getGraphics();

        if (g == null)
            return;
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(3, 3);
        
        world.render(g);
        
        if (!gameCanvas.hasFocus()) {
            g.setColor(Colors.black);
            String str = "Click to get focus";
            g.drawString(str, 
                         WIDTH / 2 - (g.getFontMetrics().stringWidth(str)) / 2, 
                         HEIGHT / 2 - (g.getFontMetrics().getHeight() / 2));
        }
        
        g.setColor(new Color(0x000000));
        g.drawString("FPS: " + fpsNow, 10, 10);
    }

    public void tick() {
        world.tick();
        inputHandler.tick();
    }

    public void clean() {
    }

    public static void main(String[] args) {
        Main main = new Main(true);
        main.start();
    }

    public void run() {
        init();
    }
}
