package Counter;

public class CounterUnSafe implements Counter {
    private int value = 0;
    public CounterUnSafe() {}
    public int getValue() {
        return value;
    }

    @Override
    public void increment() {
        value += 1;
    }
    @Override
    public void decrement() {
        value -= 1;
    }
}
