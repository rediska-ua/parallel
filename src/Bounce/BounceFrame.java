package Bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BounceFrame extends JFrame {

    private final BallCanvas canvas;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    public BounceFrame(boolean showHoles) {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas(showHoles);
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonAddBlueBall = new JButton("Add blue ball");
        JButton buttonAddRedBall = new JButton("Add red ball");
        JButton buttonExperiment = new JButton("Experiment");
        JButton buttonJoin = new JButton("Join");
        JButton buttonStop = new JButton("Stop");

        var label = new JLabel("0 balls in the holes");
        canvas.label = label;

        buttonAddBlueBall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball ball = new Ball(canvas, canvas.getHoles(), Color.BLUE);
                canvas.add(ball);
                BallThread thread = new BallThread(ball, 1);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });

        buttonAddRedBall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball ball = new Ball(canvas, canvas.getHoles(), Color.RED);
                canvas.add(ball);
                BallThread thread = new BallThread(ball, 10);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });

        buttonExperiment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int blueBallQuantity = 2000;
                ArrayList<BallThread> threads = new ArrayList<>();

                for (int i = 0; i < blueBallQuantity; ++i) {
                    Ball blue = new Ball(canvas, canvas.getHoles(), Color.BLUE, 0, canvas.getHeight());
                    canvas.add(blue);
                    BallThread thread = new BallThread(blue, 1);
                    threads.add(thread);
                }

                Ball red = new Ball(canvas, canvas.getHoles(), Color.RED, 0, canvas.getHeight());
                canvas.add(red);
                BallThread thread = new BallThread(red, 10);
                threads.add(thread);

                for (BallThread ballThread: threads) {
                    ballThread.start();
                }

            }
        });

        buttonJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                canvas.freezeAll();
                Ball greenBall = new Ball(canvas, canvas.getHoles(), Color.GREEN);
                canvas.add(greenBall);
                BallThread thread = new BallThread(greenBall, 5);
                thread.start();

                Thread joinThread = new Thread(() -> {
                    try {
                        thread.join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        canvas.unfreezeAll();
                    }
                });
                joinThread.start();
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(label);
        buttonPanel.add(buttonAddBlueBall);
        buttonPanel.add(buttonAddRedBall);
        buttonPanel.add(buttonExperiment);
        buttonPanel.add(buttonJoin);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
