public class TaskService {
    private final FIFO task = new FIFO();

    public void addTicket(int ticket) {
        task.enqueue(ticket);
    }

    public int processNextTicket() {
        return task.dequeue();
    }

    public int peekNextTicket() {
        return task.peek();
    }

    public int countTickets() {
        return task.size();
    }
}