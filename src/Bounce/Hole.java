package Bounce;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Hole {

    private final Color color = Color.BLACK;
    public static final int XSIZE = 30;
    public static final int YSIZE = 30;
    public final int x;
    public final int y;
    public Hole (int x, int y) {
        this.x = x;
        this.y = y;
    }

    void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public boolean collideWith(int x, int y) {
        double collideRadius = (XSIZE + YSIZE) / 2.0;
        double distance = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
        return distance < collideRadius;
    }
}
