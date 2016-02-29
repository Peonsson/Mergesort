import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Peonsson on 29/02/16.
 */
public class VanillaSortTest {

    @Test
    public void testVanillaSortParallel() throws Exception {

        Random r = new Random();
        float[] floats = new float[10000000];

        for (int i = 0; i < floats.length; i++) {
            floats[i] = r.nextFloat();
        }

        long avg = 0;

        for (int i = 1; i < 51; i++) {

            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);

            System.gc();
            Thread.sleep(200);

            long test = System.currentTimeMillis();
            VanillaSort.sortParallel(floatCopy);
            long data = System.currentTimeMillis() - test;

            if (i > 1)
                avg += data;
        }
        System.out.println("multi avg: " + avg / 50);
    }

    @Test
    public void testVanillaSort() throws Exception {

        Random r = new Random();
        float[] floats = new float[10000000];

        for (int i = 0; i < floats.length; i++) {
            floats[i] = r.nextFloat();
        }

        long avg = 0;

        for (int i = 1; i < 51; i++) {

            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);

            System.gc();
            Thread.sleep(200);

            long test = System.currentTimeMillis();
            VanillaSort.sort(floatCopy);
            long data = System.currentTimeMillis() - test;

            if (i > 1)
                avg += data;
        }
        System.out.println("single avg: " + avg / 50);
    }
}
