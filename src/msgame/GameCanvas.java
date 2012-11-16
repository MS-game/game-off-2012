package msgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {
    private static final long serialVersionUID = 8077355341462679865L;
    private Graphics g;
    private BufferStrategy bs;

    public Graphics getGraphics() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            requestFocus();
            return null;
        }
        g = bs.getDrawGraphics();
        if (g == null) {
            bs = null;
            return null;
        }

        g.setColor(new Color(0xFFFFFF));
        g.fillRect(0, 0, getWidth(), getHeight());

        return g;
    }

    public void endRender() {
        if (g != null) {
            g.dispose();
            g = null;
        }
        if (bs != null) {
            bs.show();
        }
    }
}
