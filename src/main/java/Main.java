import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by robin on 25/2/16.
 */
public class Main {
    public static void main(String[] args) {

        float[] originalFloats = generateArray();
        boolean running = true;

        Scanner scan = new Scanner(System.in);

        while (running) {
            System.out.println("Do you want to try merge sort (1) or quicksort (2)? 0 to quit.");
            int nextLine = scan.nextInt();

            switch (nextLine) {
                case (1) :
                    runMergeSort(originalFloats, scan);
                    break;
                case (2) :
                    runQuicksort(originalFloats, scan);
                    break;
                case (0) :
                    running = false;
                    break;
                default :
                    break;
            }
        }
    }

    private static void runMergeSort(float[] originalFloats, Scanner scan) {
        float[] copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);

        boolean runningMergeSort = true;

        System.out.println("Merge sort:\n" +
                "1 - Non-parallel\n" +
                "2 - Parallel\n" +
                "0 - Quit\n");

        while (runningMergeSort) {
            long start = 0;
            long stop = 0;

            int nextLine = scan.nextInt();
            System.gc();

            switch (nextLine) {
                case (1) :
                    System.out.println("Merge sort: Non-parallel");

                    start = System.currentTimeMillis();
                    MergeSort.sort(copyFloats);
                    stop = System.currentTimeMillis();

                    long diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (2) :
                    System.out.println("Merge sort: parallel");

                    start = System.currentTimeMillis();
                    new MergeSortParallel(copyFloats).compute();
                    stop = System.currentTimeMillis();

                    diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (0) :
                    runningMergeSort = false;
                    break;
                default :
                    break;
            }

            if (runningMergeSort) {
                copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);
            }
        }
    }

    private static void runQuicksort(float[] originalFloats, Scanner scan) {
        float[] copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);

        boolean runningQuickSort = true;

        System.out.println("Quick sort:\n" +
                "1 - Non-parallel\n" +
                "2 - Parallel\n" +
                "0 - Quit\n");

        while (runningQuickSort) {
            long start = 0;
            long stop = 0;

            int nextLine = scan.nextInt();
            System.gc();

            switch (nextLine) {
                case (1) :
                    System.out.println("Quick sort: Non-parallel");

                    start = System.currentTimeMillis();
                    Quicksort.sort(copyFloats);
                    stop = System.currentTimeMillis();

                    long diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (2) :
                    System.out.println("Quick sort: parallel");

                    start = System.currentTimeMillis();
                    new QuicksortParallel(copyFloats).compute();
                    stop = System.currentTimeMillis();

                    diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (0) :
                    runningQuickSort = false;
                    break;
                default :
                    break;
            }

            if (runningQuickSort) {
                copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);
            }
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
