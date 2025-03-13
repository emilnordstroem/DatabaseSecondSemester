package lotto;

import lotto.Opgave1.Lotto;

public class Main {

    public static void main(String[] args) {
        long l1,l2;

        Lotto.fyldArray();
        Lotto.traekRaekke();

        int[] vinderRaekke = Lotto.udtraekRaekke();
        System.out.println("Udtrukken række:");
        for(int tal : vinderRaekke){
            System.out.printf("%d, ", tal);
        }

        l1 = System.nanoTime();
//        Lotto.fyldAntalKorrekteTal();
//        Lotto.fyldAntalKorrekteTalThread();
        l2 = System.nanoTime();

        System.out.printf("%n==============================%n");
        Lotto.printStatistik();

        long koeretid = (l2-l1) / 1000000;
        System.out.printf("%nKoeretid: %d",
                koeretid
        );
    }

}
