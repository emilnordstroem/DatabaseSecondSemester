public class TestKlasse {

    public static void main(String[] args) {

        // each instance of a class is a thread which has a defined wait time for an execution
        SnaksagligPerson jacob = new SnaksagligPerson("Jacob", 200);
        SnaksagligPerson ulla = new SnaksagligPerson("Ulla", 100);
        SnaksagligPerson hans = new SnaksagligPerson("Hans", 50);

        // Invoke start() from the inherited method, not run()
        jacob.start();
        ulla.start();
        hans.start();

    }

}