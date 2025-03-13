// Extend Thread or implement interface runnable to enable use of threading
// Not always the same execution
public class SnaksagligPerson extends Thread{

    private final String navn;
    private final int ventetid;

    public SnaksagligPerson(String navn, int ventetid){
        this.navn = navn;
        this.ventetid = ventetid;
    }

    // instructions for the thread
    // don't invoke run()
    public void run(){
        for(int i = 0; i < 5; i++){
            System.out.println(navn + ": bla bla bla " + i);
            try {
                // pause a thread for a defined time span
                Thread.sleep(ventetid);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}