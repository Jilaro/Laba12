package laba12;

public class Exercise6 {

    private static int sum = 0;

    public static void main(String[] args) throws InterruptedException {

        int[] array = {
                5, 12, 7, 3, 25,
                18, 40, 9, 1, 33,
                17, 28, 50, 6, 11
        };

        int cores = Runtime.getRuntime().availableProcessors();

        System.out.println("Количество ядер: " + cores);

        Thread[] threads = new Thread[cores];

        int partSize = array.length / cores;

        for (int i = 0; i < cores; i++) {

            int start = i * partSize;

            int end;

            if (i == cores - 1) {
                end = array.length;
            } else {
                end = start + partSize;
            }

            threads[i] = new Thread(() -> {

                int localSum = 0;

                for (int j = start; j < end; j++) {
                    localSum += array[j];
                }

                synchronized (Exercise6.class) {
                    sum += localSum;
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Сумма элементов: " + sum);
    }
}