package task1;

import java.util.concurrent.RecursiveTask;

public class WorkStealingTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 100; // Порогове значення для розділення задачі
    private final int[][] array;
    private final int startRow;
    private final int endRow;

    public WorkStealingTask(int[][] array, int startRow, int endRow) {
        this.array = array;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    protected Integer compute() {
        if (endRow - startRow <= THRESHOLD) {
            int min = Integer.MAX_VALUE;
            for (int i = startRow; i < endRow; i++) {
                for (int value : array[i]) {
                    min = Math.min(min, value);
                }
            }
            return min;
        } else {
            int mid = (startRow + endRow) / 2;
            WorkStealingTask leftTask = new WorkStealingTask(array, startRow, mid);
            WorkStealingTask rightTask = new WorkStealingTask(array, mid, endRow);
            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();
            return Math.min(leftResult, rightResult);
        }
    }
}
