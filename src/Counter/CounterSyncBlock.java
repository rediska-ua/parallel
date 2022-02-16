package Counter;

public class CounterSyncBlock implements Counter {
    private int value = 0;
    private final Object lock = new Object();

    public CounterSyncBlock() {}

    public int getValue() {
        return value;
    }
    @Override
    public void increment() {
        synchronized (lock) {
            value += 1;
        }
    }

    @Override
    public void decrement() {
        synchronized (lock) {
            value -= 1;
        }
    }
}
