import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by robin on 25/2/16.
 */
public class Main {
    public static void main(String[] args) {

        float[] originalFloats = generateArray();

        Scanner scan = new Scanner(System.in);
        int nextLine = scan.nextInt();

        float[] copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);

        while (nextLine != 0) {
            long start = 0;
            long stop = 0;

            System.gc();

            if (nextLine == 1) {
                System.out.println("non-parallel");

                start = System.currentTimeMillis();
                MergeSort.sort(copyFloats);
                stop = System.currentTimeMillis();

                long diff = stop - start;
                System.out.println("Difference = " + diff);
            } else if (nextLine == 2) {
                System.out.println("parallel");

                start = System.currentTimeMillis();
                new MergeSortParallel(copyFloats).compute();
                stop = System.currentTimeMillis();

                long diff = stop - start;
                System.out.println("Difference = " + diff);
            } else {
                System.out.println("You are doing it wrong, retard.");
            }

            copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);
            nextLine = scan.nextInt();
        }
    }

    private static float[] generateArray() {
        Random r = new Random();
        float[] floats = new float[10000000];

        for (int i = 0; i < floats.length; i++) {
            floats[i] = r.nextFloat();
        }

        return floats;
    }
}
