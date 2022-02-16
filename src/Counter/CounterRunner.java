package Counter;

public class CounterRunner implements Runnable {

    private final Counter value;
    private final boolean toIncrement;

    CounterRunner(Counter counter, boolean toInc) {
        value = counter;
        toIncrement = toInc;
    }

    private void increment() {
        for (int i = 0; i < 100_000; ++i) {
            value.increment();
        }
    }
    public void decrement() {
        for (int i = 0; i < 100_000; ++i) {
            value.decrement();
        }
    }

    @Override
    public void run() {
        if (toIncrement) increment();
        else decrement();
    }
}
