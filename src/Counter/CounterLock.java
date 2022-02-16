package Counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock implements Counter {
    private int value = 0;
    private final Lock lock;

    public CounterLock() {
        lock = new ReentrantLock();
    }

    public int getValue() {
        return value;
    }

    @Override
    public void increment() {
        lock.lock();
        try {
            value += 1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void decrement() {
        lock.lock();
        try {
            value -= 1;
        } finally {
            lock.unlock();
        }
    }

 }
