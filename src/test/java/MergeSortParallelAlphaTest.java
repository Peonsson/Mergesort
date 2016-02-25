import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Peonsson and roppe546 on 25/02/16.
 */
public class MergeSortParallelAlphaTest {

    //TODO: print graph or average an sequence interval of 10,8,6,4,2 numbers.

    @Test
    public void testMergeSortParallel() throws Exception {

        Random r = new Random();
        float[] floats = new float[10000000];

        for (int i = 0; i < floats.length; i++) {
            floats[i] = r.nextFloat();
        }

        long min = Long.MAX_VALUE;
        int threshold;

        for (int i = 1; i < 1000; i++) {

            float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
            System.gc();
            Thread.sleep(1000);

            long test = System.currentTimeMillis();

            new MergeSortParallelAlpha(floatCopy, i).compute();

            long data = System.currentTimeMillis() - test;

            if (data < min) {
                min = data;
                threshold = i;
                System.out.println("new min: " + min + ", threshold: " + threshold);
            }

            System.out.println("threshold: " + i + ", time: " + data);
        }


//        boolean isSorted = true;
//
//        for (int i = 1; i < floats.length - 1; i++) {
//            if (floats[i - 1] > floats[i])
//                isSorted = false;
//        }
    }
}
