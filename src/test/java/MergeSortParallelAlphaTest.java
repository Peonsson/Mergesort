import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

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

        int[] thresholds = {100, 150, 200, 250, 300, 350, 500, 750, 1000, 1500, 2000, 2500, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

        for (int threshold : thresholds) {
            for (int i = 0; i < 6; i++) {
                float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);

                System.gc();
                Thread.sleep(150);

                ForkJoinPool pool = new ForkJoinPool();
                RecursiveAction task = new MergeSortParallelAlpha(floatCopy, threshold);
                long starTime = System.currentTimeMillis();
                pool.invoke(task);
                long data = System.currentTimeMillis() - starTime;

                if (threshold > 0)
                    avg += data;
            }

            System.out.println("thresh: " + threshold + ", avg: " + avg / 5);
            avg = 0;
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
//                new MergeSortParallelAlpha(floatCopy, i).compute();
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


    private boolean isSorted(float[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i - 1] > array[i])
                return false;
        }

        return true;
    }
}
