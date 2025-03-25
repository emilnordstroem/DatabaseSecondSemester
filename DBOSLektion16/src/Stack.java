public class Stack {

    private class Element {
        int info;
        Element next;
        Element(int n, Element e) {
            info = n;
            next = e;
        }
    }

    private Element first;

    public Stack() {
        first = null;
    }

    public synchronized void push(int n) {
        first = new Element(n, first);
        System.out.println("Push: " + n);
        notifyAll();
    }

    public synchronized int pop() {
        if (first == null) {
            try{
                System.out.println("Stacken er tom");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        int n = first.info;
        first = first.next;
        return n;
    }

    public boolean is_empty() {
        return first == null;
    }

}
