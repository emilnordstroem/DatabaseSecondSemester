package opgave4.models;

public class OrderResource {
    private int placedOrders = 0;
    private int completedOrders = 0;

    public OrderResource() {}

    public synchronized void placeOrder(){
        placedOrders++;
        System.out.printf("Waiter placed new order:%nCurrent number of orders: %d%n================%n",
                placedOrders
        );
        notify();
    }

    public synchronized void completeOrder(){
        while (placedOrders == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        placedOrders--;
        completedOrders++;
        System.out.printf("Chief completed new order:%nTotal orders completed: %d%nCurrent number of orders: %d%n================%n",
                completedOrders,
                placedOrders
        );
    }

}
