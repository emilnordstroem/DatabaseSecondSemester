package opgave2.models;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cook extends Thread {
    private final OrderResource orderCompletionResource;
    private final String identification;
    private final Semaphore cookSemaphore;

    public Cook(OrderResource orderCompletionResource, String identification, Semaphore cookSemaphore) {
        this.orderCompletionResource = orderCompletionResource;
        this.identification = identification;
        this.cookSemaphore = cookSemaphore;
    }

    @Override
    public void run() {
        int completeOrderLimit = new Random().nextInt(20,60);
        for (int orderNo = 1; orderNo <= completeOrderLimit; orderNo++) {
            try {
                cookSemaphore.acquire();
                orderCompletionResource.completeOrder();
                sleep(new Random().nextInt(3000, 5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}