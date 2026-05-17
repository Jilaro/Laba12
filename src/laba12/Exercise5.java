package laba12;

public class Exercise5 {

    private static int max = Integer.MIN_VALUE;

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

                int localMax = array[start];

                for (int j = start; j < end; j++) {

                    if (array[j] > localMax) {
                        localMax = array[j];
                    }
                }

                synchronized (Exercise5.class) {

                    if (localMax > max) {
                        max = localMax;
                    }
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Максимальный элемент: " + max);
    }
}