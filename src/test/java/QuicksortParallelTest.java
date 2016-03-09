import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Peonsson on 25/02/16.
 */
public class QuicksortParallelTest {

    @Test
    public void testQuicksortParallelSort() throws Exception {
        float[] inputArr = generateArray(5000);

        ForkJoinPool pool = new ForkJoinPool();
        RecursiveAction task = new QuicksortParallel(inputArr, 0, inputArr.length - 1);
        pool.invoke(task);

        assert(isSorted(inputArr));
    }

    @Test
    public void testQuicksortParallelSortOneElement() throws Exception {
        float[] inputArr = {45.5f};

        ForkJoinPool pool = new ForkJoinPool();
        RecursiveAction task = new QuicksortParallel(inputArr, 0, inputArr.length - 1);
        pool.invoke(task);

        assert(isSorted(inputArr));
    }

    @Test
    public void testQuicksortParallelSortEmptyArray() throws Exception {
        float[] inputArr = {};

        ForkJoinPool pool = new ForkJoinPool();
        RecursiveAction task = new QuicksortParallel(inputArr, 0, inputArr.length - 1);
        pool.invoke(task);

        assert(isSorted(inputArr));
    }

    @Test
    public void testQuicksortParallelSortMixedPositiveNegative() throws Exception {
        float[] inputArr = {-45.5f, 23.0f, -11.2f, 89.9f, -77.3f, 98.1f, -4.4f, 28.01f, -65.2f, 43.8f};

        ForkJoinPool pool = new ForkJoinPool();
        RecursiveAction task = new QuicksortParallel(inputArr, 0, inputArr.length - 1);
        pool.invoke(task);

        assert(isSorted(inputArr));
    }

    @Test
    public void testQuicksortParallelSortOnlyNegative() throws Exception {
        float[] inputArr = {-45.5f, -23.0f, -11.2f, -89.9f, -77.3f, -98.1f, -4.4f, -28.01f, -65.2f, -43.8f};

        ForkJoinPool pool = new ForkJoinPool();
        RecursiveAction task = new QuicksortParallel(inputArr, 0, inputArr.length - 1);
        pool.invoke(task);

        assert(isSorted(inputArr));
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
