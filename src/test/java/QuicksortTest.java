import org.junit.Test;

/**
 * Created by Peonsson & roppe546 on 2016-02-25.
 */
public class QuicksortTest {

    @Test
    public void testQuicksortSort() throws Exception {

        float[] inputArr = {45.5f, 23.0f, 11.2f, 89.9f, 77.3f, 98.1f, 4.4f, 28.01f, 65.2f, 43.8f};

        Quicksort.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testQuicksortSortOneElement() throws Exception {

        float[] inputArr = {45.5f};

        Quicksort.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testQuicksortSortEmptyArray() throws Exception {

        float[] inputArr = {};

        Quicksort.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testQuicksortSortMixedPositiveNegative() throws Exception {

        float[] inputArr = {-45.5f, 23.0f, -11.2f, 89.9f, -77.3f, 98.1f, -4.4f, 28.01f, -65.2f, 43.8f};

        Quicksort.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testQuicksortSortOnlyNegative() throws Exception {

        float[] inputArr = {-45.5f, -23.0f, -11.2f, -89.9f, -77.3f, -98.1f, -4.4f, -28.01f, -65.2f, -43.8f};

        Quicksort.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }
}
