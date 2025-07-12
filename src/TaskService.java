public class TaskService {
    private final FIFO task = new FIFO();

    public void addTicket(int numero, int numeroGuichet) {
        task.enqueue(numero, numeroGuichet);
    }

    public FIFO.Ticket processNextTicket() {
        return task.dequeue();
    }

    public FIFO.Ticket peekNextTicket() {
        return task.peek();
    }

    public int countTickets() {
        return task.size();
    }
}