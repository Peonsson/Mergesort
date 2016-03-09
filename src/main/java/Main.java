import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by robin on 25/2/16.
 */
public class Main {
    public static void main(String[] args) {

        float[] originalFloats = generateArray(10000000);
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
                    MergeSort.sort(copyFloats, 0, copyFloats.length);
                    stop = System.currentTimeMillis();

                    long diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (2) :
                    System.out.println("Merge sort: parallel");

                    ForkJoinPool pool = new ForkJoinPool();
                    RecursiveAction task = new MergeSortParallel(copyFloats, 0, copyFloats.length);

                    start = System.currentTimeMillis();
                    pool.invoke(task);
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

                    ForkJoinPool pool = new ForkJoinPool();
                    RecursiveAction task = new QuicksortParallel(copyFloats, 0, copyFloats.length - 1);

                    start = System.currentTimeMillis();
                    pool.invoke(task);
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

    private static float[] generateArray(int size) {
        Random r = new Random();
        float[] floats = new float[size];

        for (int i = 0; i < size; i++) {
            floats[i] = r.nextFloat();
        }

        return floats;
    }
}
