import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Peonsson on 17/03/16.
 */
public interface Strategy {

    void doTest();

}

class Framework implements Strategy {

    private int numberOfTests = 20;
    private int numberOfWarmups = 10;
    private boolean doWarmup = true;
    private boolean doSleep = true;
    private boolean doGC = true;
    private int sleepTime = 200;

    private Strategy strategy;
    private RecursiveAction task;
    private float[] floats;
    private ForkJoinPool pool;

    public Framework(Strategy strategy) {
        floats = generateArray(100000000);
        pool = new ForkJoinPool();
        this.strategy = strategy;
    }

    public void doTest() {

        if (doWarmup)
            doWarmup();

        // Run the actual test
        System.out.println("Running tests...");
        for (int i = 0; i < numberOfTests; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);

            if (doGC) {
                System.gc();
            }

            try {
                Thread.sleep(sleepTime);
            } catch (Exception ex) { }

            if (strategy instanceof MergeSortParallel) {
                task = new MergeSortParallel(floatCopy, 0, floatCopy.length);
            } else if (strategy instanceof QuicksortParallel) {
                task = new QuicksortParallel(floatCopy, 0, floatCopy.length - 1);
            }

            long startTime = System.currentTimeMillis();
            pool.invoke(task);
            long timeElapsed = System.currentTimeMillis() - startTime;

            System.out.println(timeElapsed);
        }
        System.out.println("Done!");
    }

    private void doWarmup() {
        System.out.println("Warming up...");
        for (int i = 0; i < numberOfWarmups; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);

            if (doGC) {
                System.gc();
            }

            if (doSleep) {
                try {
                    Thread.sleep(sleepTime);
                } catch (Exception ex) { }
            }

            if (strategy instanceof MergeSortParallel) {
                task = new MergeSortParallel(floatCopy, 0, floatCopy.length);
            } else if (strategy instanceof QuicksortParallel) {
                task = new QuicksortParallel(floatCopy, 0, floatCopy.length - 1);
            }

            pool.invoke(task);
        }
    }

    private float[] generateArray(int size) {
        Random r = new Random();
        float[] floats = new float[size];

        for (int i = 0; i < size; i++) {
            floats[i] = r.nextFloat();
        }

        return floats;
    }

    public int getNumberOfTests() {
        return numberOfTests;
    }

    public void setNumberOfTests(int numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    public int getNumberOfWarmups() {
        return numberOfWarmups;
    }

    public void setNumberOfWarmups(int numberOfWarmups) {
        this.numberOfWarmups = numberOfWarmups;
    }

    public boolean isDoWarmup() {
        return doWarmup;
    }

    public void setDoWarmup(boolean doWarmup) {
        this.doWarmup = doWarmup;
    }

    public boolean isDoSleep() {
        return doSleep;
    }

    public void setDoSleep(boolean doSleep) {
        this.doSleep = doSleep;
    }

    public boolean isDoGC() {
        return doGC;
    }

    public void setDoGC(boolean doGC) {
        this.doGC = doGC;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}