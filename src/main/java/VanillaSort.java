import java.util.Arrays;

/**
 * Created by Peonsson on 29/02/16.
 */
public class VanillaSort {

    public static void sort(float[] floatCopy) {
        Arrays.sort(floatCopy);
    }

    public static void sortParallel(float[] floatCopy) {
        Arrays.parallelSort(floatCopy);
    }
}
