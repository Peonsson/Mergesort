import org.junit.Test;

import java.util.Random;

/**
 * Created by Peonsson & roppe546 on 2016-02-25.
 */
public class QuicksortTest {

    @Test
    public void testQuicksortSort() throws Exception {
        float[] inputArr = generateArray(10000);
        Quicksort.sort(inputArr);
        assert(isSorted(inputArr));
    }

    @Test
    public void testQuicksortSortOneElement() throws Exception {
        float[] inputArr = {45.5f};
        Quicksort.sort(inputArr);
        assert(isSorted(inputArr));
    }

    @Test
    public void testQuicksortSortEmptyArray() throws Exception {
        float[] inputArr = {};
        Quicksort.sort(inputArr);
        assert(isSorted(inputArr));
    }

    @Test
    public void testQuicksortSortMixedPositiveNegative() throws Exception {
        float[] inputArr = {-45.5f, 23.0f, -11.2f, 89.9f, -77.3f, 98.1f, -4.4f, 28.01f, -65.2f, 43.8f};
        Quicksort.sort(inputArr);
        assert(isSorted(inputArr));
    }

    @Test
    public void testQuicksortSortOnlyNegative() throws Exception {
        float[] inputArr = {-45.5f, -23.0f, -11.2f, -89.9f, -77.3f, -98.1f, -4.4f, -28.01f, -65.2f, -43.8f};
        Quicksort.sort(inputArr);
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
}
