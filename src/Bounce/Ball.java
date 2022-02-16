package Bounce;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

public class Ball {
    private final Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private final Color color;
    private int x;
    private int y;
    private int dx = 2;
    private int dy = 2;
    private final ArrayList<Hole> holes;
    private boolean frozen = false;
    private boolean inHole = false;

    public void Freeze() {
        frozen = true;
    }

    public void UnFreeze() {
        frozen = false;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public boolean isInHole() {
        return inHole;
    }



    public Ball(Component c, ArrayList<Hole> holes, Color BallColor) {
        this.canvas = c;
        this.holes = holes;
        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
        this.color = BallColor;
    }

    public Ball(Component c, ArrayList<Hole> holes, Color BallColor, int posX, int posY) {
        this.color = BallColor;
        this.canvas = c;
        this.holes = holes;
        x = posX;
        y = posY;
        dy = 1;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(this.color);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    private boolean isBallInHole() {
        return holes.stream().anyMatch((hole) -> hole.collideWith(x, y));
    }

    public void move() {

        if (frozen) return;

        x += dx;
        y += dy;

        if (isBallInHole()) {
            inHole = true;
            this.canvas.repaint();
            return;
        }
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }

        this.canvas.repaint();
    }
}

