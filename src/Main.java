import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static final TaskService taskService = new TaskService();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/tickets", new TaskHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Serveur démarré sur le port 8080");
    }
}