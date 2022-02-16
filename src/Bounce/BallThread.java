package Bounce;

public class BallThread extends Thread {
    private final Ball b;

    public BallThread(Ball ball, int priority) {
        b = ball;
        Thread.currentThread().setPriority(priority);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++){
                if (b.isInHole()) {
                    return;
                }
                b.move();
                Thread.sleep(5);
            }
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
