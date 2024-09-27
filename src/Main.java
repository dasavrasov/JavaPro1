public class Main {

    public static void main(String[] args) {

        MyThreadPool myThreadPool = new MyThreadPool(3);
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            myThreadPool.execute(() -> {
                System.out.println("Task " + taskId + " is being processed by " + Thread.currentThread().getName());
                try {
                    // Simulate some work with Thread.sleep
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore the interrupt status
                }
                System.out.println("Task " + taskId + " completed by " + Thread.currentThread().getName());
            });
        }

        myThreadPool.shutdown();

        // Ждем завершения всех задач
        myThreadPool.awaitTermination();

        System.out.println("Все задачи выполнены");
    }
}