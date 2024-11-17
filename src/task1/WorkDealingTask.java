package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WorkDealingTask {
    public static int findMin(int[][] array) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int[] row : array) {
            tasks.add(() -> {
                int min = Integer.MAX_VALUE;
                for (int value : row) {
                    min = Math.min(min, value);
                }
                return min;
            });
        }

        try {
            List<Future<Integer>> results = executor.invokeAll(tasks);
            int globalMin = Integer.MAX_VALUE;
            for (Future<Integer> result : results) {
                globalMin = Math.min(globalMin, result.get());
            }
            return globalMin;
        } catch (Exception e) {
            e.printStackTrace();
            return Integer.MAX_VALUE;
        } finally {
            executor.shutdown();
        }
    }
}
