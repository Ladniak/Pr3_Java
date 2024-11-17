package task1;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Task1Main {
    public static void runTask() {
        int rows = 1000;
        int cols = 1000;

        int[][] array = generateArray(rows, cols);

        // Work stealing
        try (ForkJoinPool pool = new ForkJoinPool()) {
            long startTime = System.nanoTime();
            int resultWorkStealing = pool.invoke(new WorkStealingTask(array, 0, array.length));
            long endTime = System.nanoTime();
            System.out.println("Work stealing мінімум: " + resultWorkStealing);
            System.out.println("Час виконання: " + (endTime - startTime) / 1e6 + " ms");
        }

        // Work dealing
        long startTime = System.nanoTime();
        int resultWorkDealing = WorkDealingTask.findMin(array);
        long endTime = System.nanoTime();
        System.out.println("Work dealing мінімум: " + resultWorkDealing);
        System.out.println("Час виконання: " + (endTime - startTime) / 1e6 + " ms");
    }

    private static int[][] generateArray(int rows, int cols) {
        Random random = new Random();
        int[][] array = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextInt(1000);
            }
        }
        return array;
    }
}

