import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * This class runs the Arrays.sort() and Arrays.parallelSort() 20 times each and prints them so
 * the time values can be used for measurements.
 *
 * Created by Peonsson & roppe546 on 2016-02-29.
 */
public class VanillaSortTest {

    @Test
    public void testVanillaSortParallel() throws Exception {
        float[] floats = generateArray(100000000);

        // Warm up the virtual machine
        System.out.println("SortParallel: Warming up...");
        for (int i = 0; i < 10; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            Thread.sleep(200);

            Arrays.parallelSort(floatCopy);
        }

        // Run the actual test
        System.out.println("SortParallel: Running threshold tests...");
        for (int i = 0; i < 20; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            Thread.sleep(200);

            long test = System.currentTimeMillis();
            Arrays.parallelSort(floatCopy);
            long timeElapsed = System.currentTimeMillis() - test;
            System.out.println(timeElapsed);
        }
        System.out.println("Done!");
    }

    @Test
    public void testVanillaSort() throws Exception {
        float[] floats = generateArray(100000000);

        // Warm up the virtual machine
        System.out.println("Sort: Warming up...");
        for (int i = 0; i < 10; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            Thread.sleep(200);

            Arrays.sort(floatCopy);
        }

        // Run the actual test
        System.out.println("Sort: Running threshold tests...");
        for (int i = 0; i < 20; i++) {
            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            Thread.sleep(200);

            long test = System.currentTimeMillis();
            Arrays.sort(floatCopy);
            long timeElapsed = System.currentTimeMillis() - test;
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
