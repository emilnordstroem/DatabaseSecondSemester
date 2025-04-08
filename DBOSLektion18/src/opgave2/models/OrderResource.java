package opgave2.models;

public class OrderResource {
    private int placedOrders = 0;
    private int completedOrders = 0;

    public OrderResource() {}

    public void placeOrder(){
        placedOrders++;
        System.out.printf("Waiter placed new order:%nCurrent number of orders: %d%n================%n",
                placedOrders
        );
    }

    public void completeOrder(){
        if (placedOrders != 0) {
            placedOrders--;
            completedOrders++;
            System.out.printf("Chief completed new order:%nTotal orders completed: %d%nCurrent number of orders: %d%n================%n",
                    completedOrders,
                    placedOrders
            );
        }
    }

}
