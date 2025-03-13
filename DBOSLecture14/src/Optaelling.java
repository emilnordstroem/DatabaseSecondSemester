import java.util.Random;

public class Optaelling {

    private static String[] muligeNavne = {"Allan", "Bodil", "Camilla", "Dorthe", "Erik", "Frede", "Gitte", "Henning", "Ib", "John" };

    private static int[] optaelling = new int[muligeNavne.length];

    private static int antalStemmer = 100_000_000;

    private static String[] stemmer = new String[antalStemmer];

    public static void optaelling(){
        for (String stemme : stemmer) {
            for(int muligeNavneIndex = 0; muligeNavneIndex < muligeNavne.length; muligeNavneIndex++){
                if(stemme.equals(muligeNavne[muligeNavneIndex])){
                    optaelling[muligeNavneIndex] += 1;
                }
            }
        }
    }

    public static void optaellingtraad(){
        int middle = antalStemmer / 2;
        TraadKlasse optaellingLeft = new TraadKlasse(0, middle, stemmer);
        TraadKlasse opstaellingRight = new TraadKlasse(middle, antalStemmer, stemmer);

        try{
            optaellingLeft.start();
            opstaellingRight.start();
            optaellingLeft.join();
            opstaellingRight.join();

            for(int currentCandidate = 0; currentCandidate < optaelling.length; currentCandidate++){
                int stemmerFraFoerste = optaellingLeft.getOptaelling()[currentCandidate];
                int stemmerFraAnden = opstaellingRight.getOptaelling()[currentCandidate];
                optaelling[currentCandidate] = stemmerFraFoerste + stemmerFraAnden;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fillArray(){
        Random random = new Random();
        for(int i = 0; i < stemmer.length; i++){
            stemmer[i] = muligeNavne[random.nextInt(10)];
        }
    }

    public static void printArray(){
        for(int i = 0; i<stemmer.length; i++){
            System.out.print(stemmer[i] + " ");
        }
        System.out.println();
    }

    public static void printOptaelling(){
        for(int i = 0; i < optaelling.length; i++){
            System.out.println(muligeNavne[i] + " har fået " + optaelling[i] + " stemmer");
        }
    }

    public static String[] getMuligeNavne(){
        return muligeNavne;
    }
}