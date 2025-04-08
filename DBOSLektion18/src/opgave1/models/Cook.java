package opgave1.models;

import java.util.Random;

public class Cook extends Thread {
    private final OrderResource orderCompletionResource;
    private final String identification;

    public Cook(OrderResource orderCompletionResource, String identification) {
        this.orderCompletionResource = orderCompletionResource;
        this.identification = identification;
    }

    @Override
    public void run() {
        int completeOrderLimit = new Random().nextInt(20,60);
        for (int orderNo = 1; orderNo <= completeOrderLimit; orderNo++) {
            orderCompletionResource.completeOrder();
            try {
                sleep(new Random().nextInt(3000, 5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
