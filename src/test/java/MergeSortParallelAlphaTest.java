import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Peonsson and roppe546 on 25/02/16.
 */
public class MergeSortParallelAlphaTest {

    @Test
    public void testMergeSortParallel() throws Exception {

        Random r = new Random();
        float[] floats = new float[10000000];

        for (int i = 0; i < floats.length; i++) {
            floats[i] = r.nextFloat();
        }

        long avg = 0;

        for (int i = 1; i < 110002; i = i + 1332) {

            for (int j = 0; j < 21; j++) {

                float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);

                System.gc();
                Thread.sleep(150);

                long test = System.currentTimeMillis();
                new MergeSortParallelAlpha(floatCopy, i).compute();
                long data = System.currentTimeMillis() - test;

                if (j > 0)
                    avg += data;
            }

            System.out.println("thresh: " + i + ", avg: " + avg / 20);
            avg = 0;

        }

//        boolean isSorted = true;
//
//        for (int i = 1; i < floats.length - 1; i++) {
//            if (floats[i - 1] > floats[i])
//                isSorted = false;
//        }
    }
}
