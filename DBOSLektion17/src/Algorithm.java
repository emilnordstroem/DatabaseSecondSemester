import java.util.ArrayList;
import java.util.Random;

public class Algorithm {

    static ArrayList<Integer> randomNumbersList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        fillArray(10_000_000);

        Thread counterThread = new Thread(() -> {
            int primaryInteger = 0;
            int index = 0;

            long start = System.nanoTime();
            while(index < randomNumbersList.size()){
                if (isPrime(randomNumbersList.get(index))) {
                    primaryInteger++;
                }
                index++;
            }
            long end = System.nanoTime();
            long result = (end - start) / 1_000_000;

            System.out.println("Found " + primaryInteger + " primary numbers in " + result + " ms");
        });

        counterThread.start();

        counterThread.join();

    }


    private static boolean isPrime(int candidate) {
        if (candidate <= 1) {
            return false;
        }
        for (int i = 2; i <= candidate / 2; i++) {
            if (candidate % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static void fillArray(int limit){
        for(int counter = 1; counter <= limit; counter++){
            randomNumbersList.add(new Random().nextInt(1, 1_000));
        }
    }


}
