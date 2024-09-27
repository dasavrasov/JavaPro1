import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    private volatile boolean stop = false;
    private final Queue<Runnable> workQueue = new LinkedList<>();
    private final Thread[] workers;

    private final ReentrantLock lock = new ReentrantLock();

    public MyThreadPool(int poolSize) {
        workers = new Thread[poolSize];
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Thread(() -> {
                while (!stop || !workQueue.isEmpty()) {
                    Runnable runnable;
                    try {
                        lock.lock();
                        runnable = workQueue.poll();
                    } finally {
                        lock.unlock();
                    }
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
            workers[i].start();
        }
    }

    public void execute(Runnable runnable) {
        if (stop) {
            throw new IllegalStateException("Pool is shutdown");
        }
        workQueue.add(runnable);
    }

    public void shutdown() {
        stop = true;
    }

    public boolean awaitTermination() {
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return true;
    }
}