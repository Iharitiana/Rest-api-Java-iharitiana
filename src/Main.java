public class Main {
    public static void main(String[] args) {
        FIFO fifo = new FIFO();
        
        fifo.enqueue(1);
        fifo.enqueue(2);
        fifo.enqueue(3);
        System.out.println("Prochain ticket : " + fifo.peek());
        System.out.println("Ticket traité : " + fifo.dequeue()); 
        System.out.println("En attente : " + fifo.size()); 
        System.out.println("File vide : " + fifo.isEmpty());
        fifo.dequeue();
        fifo.dequeue();
        System.out.println("En attente : " + fifo.size());
        System.out.println("File vide :  " + fifo.isEmpty()); 
        try {
            fifo.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("Erreur : " + e.getMessage()); 
        }
    }
}