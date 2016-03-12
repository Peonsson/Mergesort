import com.sun.scenario.effect.Merge;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.ExplicitGroup;

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

        float[] originalFloats = generateArray(100000000);
        boolean running = true;

        Scanner scan = new Scanner(System.in);

        while (running) {
            System.out.println("1 - Merge sort\n" +
                    "2 - Quicksort\n" +
                    "3 - Arrays.sort() (20 rounds)\n" +
                    "4 - Arrays.parallelSort() (20 rounds)\n" +
                    "0 - Quit\n");
            int nextLine = scan.nextInt();

            switch (nextLine) {
                case (1):
                    runMergeSort(originalFloats, scan);
                    break;
                case (2):
                    runQuicksort(originalFloats, scan);
                    break;
                case (3) :
                    float[] floats = generateArray(100000000);

                    // Warm up the virtual machine
                    System.out.println("Warming up...");
                    for (int i = 0; i < 10; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {

                        }

                        Arrays.sort(floatCopy);
                    }

                    // Run the actual test
                    System.out.println("Running tests...");
                    for (int i = 0; i < 20; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {
                        }

                        long startTime = System.currentTimeMillis();
                        Arrays.sort(floatCopy);
                        long timeElapsed = System.currentTimeMillis() - startTime;

                        System.out.println(timeElapsed);
                    }
                    System.out.println("Done!");
                    break;
                case (4) :
                    floats = generateArray(100000000);

                    // Warm up the virtual machine
                    System.out.println("Warming up...");
                    for (int i = 0; i < 10; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {

                        }

                        Arrays.parallelSort(floatCopy);
                    }

                    // Run the actual test
                    System.out.println("Running tests...");
                    for (int i = 0; i < 20; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {
                        }

                        long startTime = System.currentTimeMillis();
                        Arrays.parallelSort(floatCopy);
                        long timeElapsed = System.currentTimeMillis() - startTime;

                        System.out.println(timeElapsed);
                    }
                    System.out.println("Done!");
                    break;
                case (0):
                    running = false;
                    break;
                default:
                    break;
            }
        }
    }

    private static void runMergeSort(float[] originalFloats, Scanner scan) {
        float[] copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);

        ForkJoinPool pool = new ForkJoinPool();

        boolean runningMergeSort = true;

        System.out.println("Merge sort:\n" +
                "1 - Non-parallel\n" +
                "2 - Parallel\n" +
                "3 - Run 20 tests (non-parallel)\n" +
                "4 - Run 20 tests (parallel)\n" +
                "0 - Quit\n");

        while (runningMergeSort) {
            long start = 0;
            long stop = 0;

            int nextLine = scan.nextInt();
            System.gc();

            switch (nextLine) {
                case (1):
                    System.out.println("Merge sort: Non-parallel");

                    start = System.currentTimeMillis();
                    MergeSort.sort(copyFloats, 0, copyFloats.length);
                    stop = System.currentTimeMillis();

                    long diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (2):
                    System.out.println("Merge sort: parallel");

                    RecursiveAction task = new MergeSortParallel(copyFloats, 0, copyFloats.length);

                    start = System.currentTimeMillis();
                    pool.invoke(task);
                    stop = System.currentTimeMillis();

                    diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (3) :
                    float[] floats = generateArray(100000000);

                    // Warm up the virtual machine
                    System.out.println("Warming up...");
                    for (int i = 0; i < 10; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {

                        }

                        MergeSort.sort(floatCopy, 0, copyFloats.length);
                    }

                    // Run the actual test
                    System.out.println("Running tests...");
                    for (int i = 0; i < 20; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {
                        }

                        long startTime = System.currentTimeMillis();
                        MergeSort.sort(floatCopy, 0, copyFloats.length);
                        long timeElapsed = System.currentTimeMillis() - startTime;

                        System.out.println(timeElapsed);
                    }
                    System.out.println("Done!");
                    break;
                case (4):
                    floats = generateArray(100000000);

                    // Warm up the virtual machine
                    System.out.println("Warming up...");
                    for (int i = 0; i < 10; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {
                        }

                        task = new MergeSortParallel(floatCopy, 0, copyFloats.length);
                        pool.invoke(task);
                    }

                    // Run the actual test
                    System.out.println("Running tests...");
                    for (int i = 0; i < 20; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {
                        }

                        task = new MergeSortParallel(floatCopy, 0, copyFloats.length);

                        long startTime = System.currentTimeMillis();
                        pool.invoke(task);
                        long timeElapsed = System.currentTimeMillis() - startTime;

                        System.out.println(timeElapsed);
                    }
                    System.out.println("Done!");
                    break;
                case (0):
                    runningMergeSort = false;
                    break;
                default:
                    break;
            }

            if (runningMergeSort) {
                copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);
            }
        }
    }

    private static void runQuicksort(float[] originalFloats, Scanner scan) {
        float[] copyFloats = Arrays.copyOfRange(originalFloats, 0, originalFloats.length);

        ForkJoinPool pool = new ForkJoinPool();

        boolean runningQuickSort = true;

        System.out.println("Quick sort:\n" +
                "1 - Non-parallel\n" +
                "2 - Parallel\n" +
                "3 - Run 20 tests (non-parallel)\n" +
                "4 - Run 20 tests (parallel)\n" +
                "0 - Quit\n");

        while (runningQuickSort) {
            long start = 0;
            long stop = 0;

            int nextLine = scan.nextInt();
            System.gc();

            switch (nextLine) {
                case (1):
                    System.out.println("Quick sort: Non-parallel");

                    start = System.currentTimeMillis();
                    Quicksort.sort(copyFloats);
                    stop = System.currentTimeMillis();

                    long diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (2):
                    System.out.println("Quick sort: parallel");

                    RecursiveAction task = new QuicksortParallel(copyFloats, 0, copyFloats.length - 1);

                    start = System.currentTimeMillis();
                    pool.invoke(task);
                    stop = System.currentTimeMillis();

                    diff = stop - start;
                    System.out.println("Time: " + diff);
                    break;
                case (3) :
                    float[] floats = generateArray(100000000);

                    // Warm up the virtual machine
                    System.out.println("Warming up...");
                    for (int i = 0; i < 10; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {

                        }

                        Quicksort.sort(floatCopy);
                    }

                    // Run the actual test
                    System.out.println("Running tests...");
                    for (int i = 0; i < 20; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {
                        }

                        long startTime = System.currentTimeMillis();
                        Quicksort.sort(floatCopy);
                        long timeElapsed = System.currentTimeMillis() - startTime;

                        System.out.println(timeElapsed);
                    }
                    System.out.println("Done!");
                    break;
                case (4):
                    floats = generateArray(100000000);

                    // Warm up the virtual machine
                    System.out.println("Warming up...");
                    for (int i = 0; i < 10; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {
                        }

                        task = new QuicksortParallelThresh(floatCopy, 0, floatCopy.length - 1, 1000);
                        pool.invoke(task);
                    }

                    // Run the actual test
                    System.out.println("Running tests...");
                    for (int i = 0; i < 20; i++) {
                        float[] floatCopy = Arrays.copyOfRange(floats, 0, floats.length);
                        System.gc();
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) {
                        }

                        task = new QuicksortParallel(floatCopy, 0, floatCopy.length - 1);

                        long startTime = System.currentTimeMillis();
                        pool.invoke(task);
                        long timeElapsed = System.currentTimeMillis() - startTime;

                        System.out.println(timeElapsed);
                    }
                    System.out.println("Done!");
                    break;
                case (0):
                    runningQuickSort = false;
                    break;
                default:
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