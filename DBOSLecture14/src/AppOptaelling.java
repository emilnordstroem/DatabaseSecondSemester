public class AppOptaelling {

    public static void main(String[] args) {
        long l1,l2;

        Optaelling.fillArray();
//        Optaelling.printArray();

        l1 = System.nanoTime();
//        Optaelling.optaelling();
        Optaelling.optaellingtraad();
        l2 = System.nanoTime();
        Optaelling.printOptaelling();

        System.out.println();
        System.out.println("Koeretiden er: " + (l2-l1)/1000000);

    }
}