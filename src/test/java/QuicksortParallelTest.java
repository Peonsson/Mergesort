import org.junit.Test;

/**
 * Created by Peonsson on 25/02/16.
 */
public class QuicksortParallelTest {

    @Test
    public void testQuicksortParallelSort() throws Exception {

        float[] inputArr = {45.5f, 23.0f, 11.2f, 89.9f, 77.3f, 98.1f, 4.4f, 28.01f, 65.2f, 43.8f};

        QuicksortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testQuicksortParallelSortOneElement() throws Exception {

        float[] inputArr = {45.5f};

        QuicksortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testQuicksortParallelSortEmptyArray() throws Exception {

        float[] inputArr = {};

        QuicksortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testQuicksortParallelSortMixedPositiveNegative() throws Exception {

        float[] inputArr = {-45.5f, 23.0f, -11.2f, 89.9f, -77.3f, 98.1f, -4.4f, 28.01f, -65.2f, 43.8f};

        QuicksortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }

    @Test
    public void testQuicksortParallelSortOnlyNegative() throws Exception {

        float[] inputArr = {-45.5f, -23.0f, -11.2f, -89.9f, -77.3f, -98.1f, -4.4f, -28.01f, -65.2f, -43.8f};

        QuicksortParallel.sort(inputArr);

        boolean isSorted = true;

        for (int i = 1; i < inputArr.length - 1; i++) {
            if (inputArr[i - 1] > inputArr[i])
                isSorted = false;
        }

        assert (isSorted);
    }
}
