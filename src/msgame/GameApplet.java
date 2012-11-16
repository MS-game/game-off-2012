package msgame;

import java.applet.Applet;
import java.awt.BorderLayout;

public class GameApplet extends Applet {
    private static final long serialVersionUID = -6303980103523972798L;
    private Main main = new Main();

    public void init() {
        setSize(720, 540);
        setLayout(new BorderLayout());
        add(main.gameCanvas, BorderLayout.CENTER);
    }

    public void start() {
        main.start();
    }

    public void stop() {
        main.stop();
    }
}
