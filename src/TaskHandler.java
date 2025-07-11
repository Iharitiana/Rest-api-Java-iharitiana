import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TaskHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String methode = exchange.getRequestMethod();
        String reponse;
        int codeStatut;

        if (path.equals("/tickets") && methode.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder Requete = new StringBuilder();
            String ligne;
            while ((ligne = br.readLine()) != null) {
                Requete.append(ligne);
            }
            br.close();

            try {
                String numeroTicket = Requete.toString().split("=")[1];
                int ticket = Integer.parseInt(numeroTicket);
                Main.taskService.addTicket(ticket);
                reponse = "Ticket ajoute : " + ticket + ". En attente : " + Main.taskService.countTickets();
                codeStatut = 200;
                System.out.println("Requete POST tickets : ticket " + ticket);
            } catch (Exception e) {
                reponse = "Erreur : Numero ticket invalide";
                codeStatut = 400;
                System.out.println("Erreur POST tickets : " + e.getMessage());
            }
        } else if (path.equals("/tickets/next") && methode.equals("GET")) {
            try {
                int ticket = Main.taskService.processNextTicket();
                reponse = "Ticket traite : " + ticket + ". En attente : " + Main.taskService.countTickets();
                codeStatut = 200;
                System.out.println("Requete GET tickets/next : ticket " + ticket);
            } catch (IllegalStateException e) {
                reponse = "Erreur : File vide";
                codeStatut = 400;
                System.out.println("Erreur GET tickets/next : " + e.getMessage());
            }
        } else if (path.equals("/tickets/peek") && methode.equals("GET")) {
            try {
                int ticket = Main.taskService.peekNextTicket();
                reponse = "Prochain ticket : " + ticket + ". En attente : " + Main.taskService.countTickets();
                codeStatut = 200;
                System.out.println("Requete GET tickets/peek : ticket " + ticket);
            } catch (IllegalStateException e) {
                reponse = "Erreur : File vide";
                codeStatut = 400;
                System.out.println("Erreur GET tickets/peek : " + e.getMessage());
            }
        } else if (path.equals("/tickets/count") && methode.equals("GET")) {
            reponse = "Personnes en attente : " + Main.taskService.countTickets();
            codeStatut = 200;
            System.out.println("Requete GET tickets/count : " + Main.taskService.countTickets());
        } else {
            reponse = "Methode ou chemin non autorise";
            codeStatut = 405;
            System.out.println("Requete non autorise : " + methode + " " + path);
        }

        byte[] reponseBytes = reponse.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(codeStatut, reponseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(reponseBytes);
            os.flush();
        }
    }
}