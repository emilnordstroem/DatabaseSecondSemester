package lotto.Opgave2;

import lotto.Opgave1.Lotto;

public class LottoThread extends Thread {
    private final int[][] lottoRaekker;
    private final int start;
    private final int slut;
    private final int[] antalKorrekteTal = new int[Lotto.getAntalNumre() + 1];

    public LottoThread(int[][] lottoRaekker, int start, int slut) {
        this.lottoRaekker = lottoRaekker;
        this.start = start;
        this.slut = slut;
    }

    @Override
    public void run() {
        for(int raekke = start; raekke < slut; raekke++){
            int antalKorrekte = 0;
            for (int tal : lottoRaekker[raekke]) {
                if (matchFundet(tal)) {
                    antalKorrekte++;
                }
            }
            antalKorrekteTal[antalKorrekte]++;
        }
    }

    private static boolean matchFundet(int kandidat){
        for(int tal : Lotto.udtraekRaekke()){
            if(tal == kandidat){
                return true;
            }
        }
        return false;
    }

    public int[] getAntalKorrekteTal() {
        return antalKorrekteTal;
    }
}
