import org.junit.Test;

import java.util.Random;

/**
 * Created by Peonsson on 25/02/16.
 */
public class MergeSortParallelAlphaTest {


    @Test
    public void testMergeSortParallel() throws Exception {

        Random r = new Random();
        float[] floats = new float[10000000];

        for (int i = 0; i < floats.length; i++) {
            floats[i] = r.nextFloat();
        }

        for (int i = 1; i < 1000; i++) {

            System.gc();
            Thread.sleep(1000);

            long test = System.currentTimeMillis();

            new MergeSortParallelAlpha(floats, i).compute();

            long data = System.currentTimeMillis() - test;

            System.out.println("threshhold: " + i + ", time: " + data);
        }


//        boolean isSorted = true;
//
//        for (int i = 1; i < floats.length - 1; i++) {
//            if (floats[i - 1] > floats[i])
//                isSorted = false;
//        }
    }
}
