package opgave1;

import opgave1.models.Cook;
import opgave1.models.OrderResource;
import opgave1.models.Waiter;

public class Main {

    public static void main(String[] args) {
       OrderResource resource = new OrderResource();

        Cook gordon = new Cook(resource, "Gordon");
        Waiter jessica = new Waiter(resource, "Jessica");
        Waiter floyd = new Waiter(resource, "Floyd");

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
