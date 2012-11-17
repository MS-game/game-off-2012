package msgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    class Key {
        public boolean pressed;
        public boolean down;
    }
    public Key[] keys;

    public InputHandler(Main main) {
        main.gameCanvas.addKeyListener(this);
        
        keys = new Key[256];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new Key();
        }
    }

    public void keyPressed(KeyEvent ke) {
        toggle(ke, true);
    }

    public void keyReleased(KeyEvent ke) {
        toggle(ke, false);
    }
    public void tick () {
        for (int i = 0; i < keys.length; i++) {
            keys[i].pressed = false;
        }
    }
    public boolean isKeyDown (int key) {
        return keys[key].down;
    }
    public boolean isKeyPressed (int key) {
        return keys[key].pressed;
    }
    private void toggle(KeyEvent ke, boolean pressed) {
        int kc = ke.getKeyCode(); 
        if (kc > keys.length) return;
        if (pressed) {
            keys[kc].pressed = true;
        }
        keys[kc].down = pressed;
    }

    public void keyTyped(KeyEvent ke) {
    }
}
