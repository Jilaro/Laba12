package laba12;

public class Exercise4 {

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++) {

            int threadNumber = i;

            Thread t = new Thread(() -> {

                System.out.println(
                        "Поток № " + threadNumber
                );
            });

            t.start();
        }
    }
}