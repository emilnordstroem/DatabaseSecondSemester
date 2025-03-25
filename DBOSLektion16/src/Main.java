public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack();

        Thread pushThread = new Thread(() -> {
            int n = 0;
            while(n < 10){
                try {
                    Thread.sleep(1000);
                    stack.push(n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                n++;
            }
        });

        Thread popThread = new Thread(() -> {
            int n = 0;
            while(n < 10){
                try {
                    Thread.sleep(1000);
                    stack.pop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                n++;
            }
        });

        pushThread.start();
        popThread.start();

        try {
            pushThread.join();
            popThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(stack.is_empty());
    }

}
