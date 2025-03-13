package lotto.Opgave1;

import lotto.Opgave2.LottoThread;

import java.util.Random;

public class Lotto {
    private static final int antalRaekker = 10_000_000;
    private static final int antalNumre = 7;
    private static final int[][] lottoRaekker = new int[antalRaekker][antalNumre];
    private static final int[] udtrukkenRaekke = new int[antalNumre];
    private static final int[] antalKorrekteTal = new int[antalNumre + 1];

    public static void fyldAntalKorrekteTal(){
        for(int[] raekke : lottoRaekker){
            int antalKorrekte = 0;
            for (int tal : raekke) {
                if (matchFundet(tal)) {
                    antalKorrekte++;
                }
            }
            antalKorrekteTal[antalKorrekte]++;
        }
    }

    public static void fyldAntalKorrekteTalThread(){
        int middle = antalRaekker / 2;

        LottoThread venstreTaeller = new LottoThread(lottoRaekker, 0, middle);
        LottoThread hoejreTaeller = new LottoThread(lottoRaekker, middle, antalRaekker);

        try{
            venstreTaeller.start();
            hoejreTaeller.start();

            venstreTaeller.join();
            hoejreTaeller.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(int index = 0; index < antalKorrekteTal.length; index++){
            int venstreKorrekte = venstreTaeller.getAntalKorrekteTal()[index];
            int hoejreKorrekte = hoejreTaeller.getAntalKorrekteTal()[index];
            antalKorrekteTal[index] = venstreKorrekte + hoejreKorrekte;
        }
    }

    private static boolean matchFundet(int kandidat){
        for(int tal : udtrukkenRaekke){
            if(tal == kandidat){
                return true;
            }
        }
        return false;
    }

    public static void printStatistik(){
        for(int index = 0; index < antalNumre; index++){
            System.out.printf("%d korrekte tal: %d%n",
                    index + 1,
                    antalKorrekteTal[index]);
        }
    }

    public static void fyldArray(){
        int[] alleradeTrukketTal = new int[antalNumre];
        for(int raekkeIndex = 0; raekkeIndex < lottoRaekker.length; raekkeIndex++){
            for(int nummerIndex = 0; nummerIndex < lottoRaekker[raekkeIndex].length; nummerIndex++){
                int trukketTal = new Random().nextInt(1,36);
                boolean alleradeTrukket = false;
                for(int tal : alleradeTrukketTal){
                    if (trukketTal == tal) {
                        alleradeTrukket = true;
                        break;
                    }
                }
                if(!alleradeTrukket){
                    lottoRaekker[raekkeIndex][nummerIndex] = new Random().nextInt(1,36);
                }
            }
        }

    }

    public static void traekRaekke(){
        for(int index = 0; index < antalNumre; index++){
            udtrukkenRaekke[index] =  new Random().nextInt(1,36);
        }
    }

    public static int[] udtraekRaekke(){
        return udtrukkenRaekke;
    }

    public static int getAntalNumre(){
        return antalNumre;
    }

}
