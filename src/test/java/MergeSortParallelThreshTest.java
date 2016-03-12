import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * This class runs the MergeSortParallelThresh sorting methods with different threshold values, and prints
 * the duration taken, so the data can be used for measurements.
 *
 * Created by Peonsson and roppe546 on 2016-02-25.
 */
public class MergeSortParallelThreshTest {

    @Test
    public void testMergeSortParallel() throws Exception {
        float[] floats = generateArray(100000000);

        // Warm up the virtual machine
        System.out.println("Warming up...");
        for (int i = 0; i < 10; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            Thread.sleep(200);

            ForkJoinPool pool = new ForkJoinPool();
            RecursiveAction task = new MergeSortParallelThresh(floatCopy, 0, floatCopy.length, 1000);
            pool.invoke(task);
        }

        // Thresholds used to try to find optimal value for merge sort
        int[] thresholds = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000, 11000, 12000, 13000, 14000, 15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000, 100000, 175000, 250000, 350000, 450000, 500000, 600000 };

        // Run the actual test
        System.out.println("Running threshold tests...");
        for (int threshold : thresholds) {
            for (int i = 0; i < 20; i++) {
                float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                System.gc();
                Thread.sleep(200);

                ForkJoinPool pool = new ForkJoinPool();
                RecursiveAction task = new MergeSortParallelThresh(floatCopy, 0, floatCopy.length, threshold);

                long startTime = System.currentTimeMillis();
                pool.invoke(task);
                long timeElapsed = System.currentTimeMillis() - startTime;
                System.out.print(timeElapsed + "\t");
            }
            System.out.println();
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

    private boolean isSorted(float[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i - 1] > array[i])
                return false;
        }

        return true;
    }
}
