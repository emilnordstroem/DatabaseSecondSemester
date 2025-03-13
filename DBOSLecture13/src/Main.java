public class Main {

    public static void main(String[] args) {
        Hilsen velkommen = new Hilsen("velkommen");
        Hilsen godmorgen = new Hilsen("godmorgen");
        try{
            velkommen.start(); // opsætter tråden og kalder run()
            godmorgen.start();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
