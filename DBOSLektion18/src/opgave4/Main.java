package opgave4;

import opgave4.models.Cook;
import opgave4.models.OrderResource;
import opgave4.models.Waiter;

public class Main {

    public static void main(String[] args) {
        OrderResource resource = new OrderResource();

        Cook gordon = new Cook(resource, "Gordon");
        Cook francais = new Cook(resource, "Francais");
        Waiter jessica = new Waiter(resource, "Jessica");
        Waiter floyd = new Waiter(resource, "Floyd");

        jessica.start();
        floyd.start();
        gordon.start();
        francais.start();

        try {
            jessica.join();
            floyd.join();
            gordon.join();
            francais.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
