import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Peonsson and roppe546 on 25/02/16.
 */
public class QuicksortParallelThreshTest {

    @Test
    public void testMergeSortParallel() throws Exception {
        float[] floats = generateArray(10000000);

        // Warm up the virtual machine
        System.out.println("Warming up...");
        for (int i = 0; i < 10; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            Thread.sleep(200);

            ForkJoinPool pool = new ForkJoinPool();
            RecursiveAction task = new QuicksortParallelThresh(floatCopy, 0, floatCopy.length - 1, 1000);
            pool.invoke(task);
        }

        // Thresholds used to try to find optimal value for merge sort
        int[] thresholds = {100, 150, 200, 250, 300, 350, 500, 750, 1000, 1500, 2000, 2500, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000, 12000};

        // Run the actual test
        System.out.println("Running threshold tests...");
        for (int threshold : thresholds) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            Thread.sleep(200);

            ForkJoinPool pool = new ForkJoinPool();
            RecursiveAction task = new QuicksortParallelThresh(floatCopy, 0, floatCopy.length - 1, threshold);

            long startTime = System.currentTimeMillis();
            pool.invoke(task);
            long timeElapsed = System.currentTimeMillis() - startTime;

            System.out.println(timeElapsed);
        }

//        for (int i = 100; i <= 10000; i = i + 50) {
//
//            for (int j = 0; j < 21; j++) {
//
//                float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
//
//                System.gc();
//                Thread.sleep(150);
//
//                long test = System.currentTimeMillis();
//                new MergeSortParallelThresh(floatCopy, i).compute();
//                long data = System.currentTimeMillis() - test;
//
//                if (j > 0)
//                    avg += data;
//            }
//
//            System.out.println("thresh: " + i + ", avg: " + avg / 20);
//            avg = 0;
//
//        }
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

    private String arrayToString(float[] array) {
        StringBuilder builder = new StringBuilder("[ ");

        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                builder.append(array[i]);
                builder.append(", ");
            }
            else {
                builder.append(array[i]);
                builder.append(" ]");
            }
        }

        return builder.toString();
    }
}
