package opgave3.models;

import java.util.Random;

public class Waiter extends Thread {
    private final OrderResource orderPlacementResource;
    private final String identification;

    public Waiter(OrderResource orderPlacementResource, String identification) {
        this.orderPlacementResource = orderPlacementResource;
        this.identification = identification;
    }

    @Override
    public void run() {
        int receiveOrderLimit = new Random().nextInt(10,30);
        for (int orderNo = 1; orderNo <= receiveOrderLimit; orderNo++) {
            try {
                orderPlacementResource.placeOrder();
                sleep(new Random().nextInt(500, 1500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
