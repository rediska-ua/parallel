package Counter;

public class CounterTask {


    static void experiment(Counter value, Thread incrementThread, Thread decrementThread) {

        incrementThread.start();
        decrementThread.start();
        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(value.getValue());

    }

    public static void main(String[] args) {

        Counter counterNotSafe = new CounterUnSafe();

        Thread incrementThread = new Thread(new CounterRunner(counterNotSafe, true));
        Thread decrementThread = new Thread(new CounterRunner(counterNotSafe, false));

        experiment(counterNotSafe, incrementThread, decrementThread);

        System.out.println("Unsafe counter");
        System.out.println("---------------------------------------------------------------");

        Counter counterSyncMethod = new CounterSyncMethod();

        Thread incrementThread2 = new Thread(new CounterRunner(counterSyncMethod, true));
        Thread decrementThread2 = new Thread(new CounterRunner(counterSyncMethod, false));

        experiment(counterSyncMethod, incrementThread2, decrementThread2);

        System.out.println("Counter with synchronized methods");
        System.out.println("---------------------------------------------------------------");


        Counter counterSyncBlock = new CounterSyncBlock();
        Thread incrementThread3 = new Thread(new CounterRunner(counterSyncBlock, true));
        Thread decrementThread3 = new Thread(new CounterRunner(counterSyncBlock, false));

        experiment(counterSyncBlock, incrementThread3, decrementThread3);

        System.out.println("Counter with synchronized block");
        System.out.println("---------------------------------------------------------------");

        Counter counterLock = new CounterLock();
        Thread incrementThread4 = new Thread(new CounterRunner(counterLock, true));
        Thread decrementThread4 = new Thread(new CounterRunner(counterLock, false));

        experiment(counterLock, incrementThread4, decrementThread4);

        System.out.println("Counter with lock");
        System.out.println("---------------------------------------------------------------");


    }
}
