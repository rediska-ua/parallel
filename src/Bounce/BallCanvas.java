package Bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {

    private final ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Hole> holes;
    private final boolean spawnHoles;

    public JLabel label;
    private int inHoleCounter = 0;

    public BallCanvas(boolean showHoles) {
        spawnHoles = showHoles;
    }

    private void incrementCounter(int count) {
        inHoleCounter += count;
        label.setText(inHoleCounter + " balls");
    }

    public void freezeAll() {
        for (Ball ball : balls) {
            ball.Freeze();
        }
    }

    public void unfreezeAll() {
        for (Ball ball : balls) {
            ball.UnFreeze();
        }
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }

    private ArrayList<Hole> makeHoles() {

        int w = getWidth();
        int h = getHeight();

        ArrayList<Hole> holes = new ArrayList<>();

        if (spawnHoles) {
            holes.add(new Hole(0, 0));
            holes.add(new Hole(w - Hole.XSIZE, 0));
            holes.add(new Hole(0, h - Hole.YSIZE));
            holes.add(new Hole(w - Hole.XSIZE, h - Hole.YSIZE));
        }

        return holes;
    }

    public void add(Ball b) {
        this.balls.add(b);
    }

    void paintHoles(Graphics2D g2) {
        for (Hole hole : holes) {
            hole.draw(g2);
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        holes = makeHoles();
        paintHoles(g2);
        incrementCounter((int)balls.stream().filter(Ball :: isInHole).count());
        balls.removeIf(Ball :: isInHole);
        for (Ball ball : balls) {
            ball.draw(g2);
        }
    }
}
