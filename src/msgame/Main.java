package msgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Main {
    /** Ticks per second */
    public static final int TPS = 60;
    /** Frames per second */
    public static final int FPS = 60;
    public static final String TITLE = "Tha best game ever";
    public static final int WIDTH = 240;
    public static final int HEIGHT = 180;
    public static final int SCALE = 3;

    public GameCanvas gameCanvas;
    public JFrame frame;
    
    public World world;

    public Main() {
        initDisplay();
        initScene();
        startLoop();
        clean();
    }

    public void initDisplay() {
        gameCanvas = new GameCanvas();
        gameCanvas.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        gameCanvas.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        gameCanvas
                .setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(gameCanvas, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void initScene() {
        world = new World(this);
        world.loadLevel(0);
    }

    public void startLoop() {
        long lastTime = System.currentTimeMillis();
        double unprocessed = 0;
        int fps = 0;
        long fpsCounter = lastTime;
        
        for (;;) {
            long now = System.currentTimeMillis();
            long delta = (now - lastTime);
            lastTime = now;
            unprocessed += delta / (1000.0 / TPS);

            boolean ticked = false;
            while (unprocessed > 1) {
                ticked = true;
                tick();
                unprocessed--;
            }
            if (ticked) {
                render();
                gameCanvas.endRender();
                fps++;
            }
            if ((now - fpsCounter) > 1000) {
                fpsCounter += 1000;
                frame.setTitle("FPS: " + fps);
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
        
        g.setColor(new Color((int) (Math.random() * 0xFFFFFF)));
        g.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
    }

    public void tick() {
    }

    public void clean() {
    }

    public static void main(String[] args) {
        new Main();
    }
}
