package msgame;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class HintText {
    class Hint {
        public String hint;
        public int level;
        
        public Hint (int level, String hint) {
            this.hint = hint;
            this.level = level;
        }
    }
    public Main main;
    public List<Hint> hints;
    
    public HintText(Main main) {
        this.main = main;
        hints = new ArrayList<Hint>();
        
        hints.add(new Hint(0, "Use the arrow keys or A an D to proceed to the door"));
        hints.add(new Hint(1, "Press R to restart a level"));
        hints.add(new Hint(2, "Hold SHIFT or CTRL to pull a box"));
        hints.add(new Hint(3, "Press X or E to throw a box"));
        hints.add(new Hint(5, "Stand on a antigravity tile to let the box fall again"));
    }

    public String getHintByLevel(int level) {
        for (Hint hint : hints) {
            if (hint.level == level) return hint.hint;
        }
        return null;
    }
    public void render (Graphics g) {
        String hint = getHintByLevel(main.world.currentLevel);
        if (hint == null) return;
        
        g.setFont(Fonts.hintText);
        g.setColor(Colors.black);
        g.drawString(hint, (Main.WIDTH / 2) - (g.getFontMetrics().stringWidth(hint) / 2)+1, 21);
        g.setColor(Colors.white);
        g.drawString(hint, (Main.WIDTH / 2) - (g.getFontMetrics().stringWidth(hint) / 2), 20);
    }
}
