package msgame;

import java.awt.Color;

public class Colors {
    public static final Color black = new Color(0x000000);
    public static final Color white = new Color(0xFFFFFF);
    public static final Color grey = new Color(0x888888);
    public static final Color red = new Color(0xFF0000);
    public static final Color green = new Color(0x0000FF);
    public static final Color blue = new Color(0x0000FF);
    public static final Color darkBlue = new Color(0x0000AA);
    public static final Color[] blood = new Color[]{
        Colors.red,
        Colors.red.darker(),
        Colors.red.brighter(),
        Colors.red.darker().darker(),
        Colors.red.darker().darker().darker(),
    };
}
