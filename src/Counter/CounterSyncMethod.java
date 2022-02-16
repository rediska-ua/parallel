package Counter;

public class CounterSyncMethod implements Counter {
    private int value = 0;

    public CounterSyncMethod() {}

    public int getValue() {
        return value;
    }

    @Override
    public synchronized void increment() {
        value += 1;
    }

    @Override
    public synchronized void decrement() {
        value -= 1;
    }
}
