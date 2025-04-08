package opgave2;

import opgave2.models.Cook;
import opgave2.models.OrderResource;
import opgave2.models.Waiter;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        OrderResource resource = new OrderResource();

        Semaphore waiterSemaphore = new Semaphore(1);
        Semaphore cookSemaphore = new Semaphore(0);

        Cook gordon = new Cook(resource, "Gordon", cookSemaphore);
        Waiter jessica = new Waiter(resource, "Jessica", cookSemaphore, waiterSemaphore);
        Waiter floyd = new Waiter(resource, "Floyd", cookSemaphore, waiterSemaphore);

        jessica.start();
        floyd.start();
        gordon.start();

        try {
            jessica.join();
            floyd.join();
            gordon.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
