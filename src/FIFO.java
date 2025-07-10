import java.util.ArrayList;

public class FIFO {
    private ArrayList<Integer> queue = new ArrayList<>();

    public void enqueue(int element) {
        queue.add(element);
    }

    public int dequeue() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("La file est vide");
        }
        return queue.remove(0);
    }

    public int peek() {
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