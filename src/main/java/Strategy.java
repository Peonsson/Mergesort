import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Peonsson on 17/03/16.
 */
public interface Strategy {

    void sort();
}

class DoMergeSort implements Strategy {

    public void sort() {

        float[] floats = generateArray(100000000);
        ForkJoinPool pool = new ForkJoinPool();
        RecursiveAction task;

        // Warm up the virtual machine
        System.out.println("Warming up...");
        for (int i = 0; i < 10; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            try {
                Thread.sleep(200);
            } catch (Exception ex) {
            }

            task = new MergeSortParallel(floatCopy, 0, floatCopy.length);
            pool.invoke(task);
        }

        // Run the actual test
        System.out.println("Running tests...");
        for (int i = 0; i < 20; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            try {
                Thread.sleep(200);
            } catch (Exception ex) {
            }

            task = new MergeSortParallel(floatCopy, 0, floatCopy.length);

            long startTime = System.currentTimeMillis();
            pool.invoke(task);
            long timeElapsed = System.currentTimeMillis() - startTime;

            System.out.println(timeElapsed);
        }
        System.out.println("Done!");
    }

    private float[] generateArray(int size) {
        Random r = new Random();
        float[] floats = new float[size];

        for (int i = 0; i < size; i++) {
            floats[i] = r.nextFloat();
        }

        return floats;
    }
}

class DoQuicksort implements Strategy {

    public void sort() {

        float[] floats = generateArray(100000000);
        ForkJoinPool pool = new ForkJoinPool();
        RecursiveAction task;

        // Warm up the virtual machine
        System.out.println("Warming up...");
        for (int i = 0; i < 10; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            try {
                Thread.sleep(200);
            } catch (Exception ex) {
            }

            task = new QuicksortParallelThresh(floatCopy, 0, floatCopy.length - 1, 1000);
            pool.invoke(task);
        }

        // Run the actual test
        System.out.println("Running tests...");
        for (int i = 0; i < 20; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            try {
                Thread.sleep(200);
            } catch (Exception ex) {
            }

            task = new QuicksortParallel(floatCopy, 0, floatCopy.length - 1);

            long startTime = System.currentTimeMillis();
            pool.invoke(task);
            long timeElapsed = System.currentTimeMillis() - startTime;

            System.out.println(timeElapsed);
        }
        System.out.println("Done!");
    }

    private float[] generateArray(int size) {
        Random r = new Random();
        float[] floats = new float[size];

        for (int i = 0; i < size; i++) {
            floats[i] = r.nextFloat();
        }

        return floats;
    }
}