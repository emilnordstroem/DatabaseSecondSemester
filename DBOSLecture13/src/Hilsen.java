public class Hilsen extends Thread {
    private final String tekst;

    public Hilsen(String tekst){
        this.tekst = tekst;
    }

    @Override
    public void run(){
        boolean run = true;
        while(run){
            try{
                Thread.sleep(200);
                int iterationLimit = 5;
                if(tekst.toLowerCase().equals("godmorgen")){
                    iterationLimit += 5;
                }
                for(int index = 0; index <= iterationLimit; index++ ){
                    System.out.println(tekst);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}