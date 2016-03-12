import org.junit.Test;

import java.util.Random;

/**
 * This class tests the MergeSort class to make sure it functions properly.
 *
 * Created by Peonsson & roppe546 on 2016-02-25.
 */
public class MergeSortTest {

    @Test
    public void testMergeSortSort() throws Exception {
        float[] inputArr = generateArray(10000);
        MergeSort.sort(inputArr, 0, inputArr.length);
        assert(isSorted(inputArr));
    }

    @Test
    public void testMergeSortSortOneElement() throws Exception {
        float[] inputArr = {45.5f};
        MergeSort.sort(inputArr, 0, inputArr.length);
        assert(isSorted(inputArr));
    }

    @Test
    public void testMergeSortSortEmptyArray() throws Exception {
        float[] inputArr = {};
        MergeSort.sort(inputArr, 0, inputArr.length);
        assert(isSorted(inputArr));
    }

    @Test
    public void testMergeSortSortMixedPositiveNegative() throws Exception {
        float[] inputArr = {-45.5f, 23.0f, -11.2f, 89.9f, -77.3f, 98.1f, -4.4f, 28.01f, -65.2f, 43.8f};
        MergeSort.sort(inputArr, 0, inputArr.length);
        assert(isSorted(inputArr));
    }

    @Test
    public void testMergeSortSortOnlyNegative() throws Exception {
        float[] inputArr = {-45.5f, -23.0f, -11.2f, -89.9f, -77.3f, -98.1f, -4.4f, -28.01f, -65.2f, -43.8f};
        MergeSort.sort(inputArr, 0, inputArr.length);
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