package opgave2.models;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Waiter extends Thread {
    private final OrderResource orderPlacementResource;
    private final String identification;
    private final Semaphore cookSemaphore;
    private final Semaphore waiterSemaphore;

    public Waiter(OrderResource orderPlacementResource, String identification, Semaphore cookSemaphore, Semaphore waiterSemaphore) {
        this.orderPlacementResource = orderPlacementResource;
        this.identification = identification;
        this.cookSemaphore = cookSemaphore;
        this.waiterSemaphore = waiterSemaphore;
    }

    @Override
    public void run() {
        int receiveOrderLimit = new Random().nextInt(10,30);
        for (int orderNo = 1; orderNo <= receiveOrderLimit; orderNo++) {
            try {
                waiterSemaphore.acquire();
                orderPlacementResource.placeOrder();
                cookSemaphore.release();
                sleep(new Random().nextInt(500, 1500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                waiterSemaphore.release();
            }
        }
    }

}
