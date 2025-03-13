public class TraadKlasse extends Thread{
    private int start;
    private int slut;
    private String[] stemmer;
    private int[] opstaelling = new int[Optaelling.getMuligeNavne().length];

    public TraadKlasse(int start, int slut, String[] stemmer) {
        this.start = start;
        this.slut = slut;
        this.stemmer = stemmer;
    }

    public void run(){
        for (int stemmeIndex = start; stemmeIndex < slut; stemmeIndex++) {
            for(int muligeNavneIndex = 0; muligeNavneIndex < opstaelling.length; muligeNavneIndex++){
                if(stemmer[stemmeIndex].equals(Optaelling.getMuligeNavne()[muligeNavneIndex])){
                    opstaelling[muligeNavneIndex] += 1;
                }
            }
        }
    }

    public int[] getOptaelling(){
        return opstaelling;
    }
}