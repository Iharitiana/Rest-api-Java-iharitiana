public class FIFO {
    public static class Ticket {
        private final int numero;
        private final int numeroGuichet;

        public Ticket(int numero, int numeroGuichet) {
            this.numero = numero;
            this.numeroGuichet = numeroGuichet;
        }

        public int getNumero() { 
            return numero; 
        }
        public int getNumeroGuichet() { 
            return numeroGuichet; 
        }
    }

    private java.util.ArrayList<Ticket> queue = new java.util.ArrayList<>();

    public void enqueue(int numero, int numeroGuichet) {
        queue.add(new Ticket(numero, numeroGuichet));
    }

    public Ticket dequeue() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("La file est vide");
        }
        return queue.remove(0);
    }

    public Ticket peek() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("La file est vide");
        }
        return queue.get(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}