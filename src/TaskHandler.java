import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TaskHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String methode = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery();
        String reponse;
        int codeStatut;

        Map<String, String> params = new HashMap<>();
        if (methode.equals("POST")) {
            params = parseQuery(getBody(exchange));
        } else if (query != null) {
            params = parseQuery(query);
        }

        if (path.equals("/tickets") && methode.equals("POST") && params.containsKey("ticket") && params.containsKey("numeroGuichet")) {
            try {
                int ticket = Integer.parseInt(params.get("ticket"));
                int numeroGuichet = Integer.parseInt(params.get("numeroGuichet"));
                Main.taskService.addTicket(ticket, numeroGuichet);
                reponse = "Ticket numero : "+ ticket + " ajouter Guichet numero : " + numeroGuichet + " . En attente : " + Main.taskService.countTickets();
                codeStatut = 200;
            } catch (NumberFormatException e) {
                reponse = "Erreur : Numero de ticket ou guichet invalide";
                codeStatut = 400;
            } catch (Exception e) {
                reponse = "Erreur : Donnees invalides";
                codeStatut = 400;
            }
        } else if (path.equals("/tickets/next") && methode.equals("GET")) {
            try {
                FIFO.Ticket ticket = Main.taskService.processNextTicket();
                reponse = "Ticket traiter Guichet: " + ticket.getNumeroGuichet() + ", ticket numero: " + ticket.getNumero() + " . En attente : " + Main.taskService.countTickets();
                codeStatut = 200;
            } catch (IllegalStateException e) {
                reponse = "Erreur : File vide";
                codeStatut = 400;
            }
        } else if (path.equals("/tickets/peek") && methode.equals("GET")) {
            try {
                FIFO.Ticket ticket = Main.taskService.peekNextTicket();
                reponse = "Prochain ticket : tickets numero: " + ticket.getNumero()  + " Guichet numero: " + ticket.getNumeroGuichet() + " . En attente : " + Main.taskService.countTickets();
                codeStatut = 200;
            } catch (IllegalStateException e) {
                reponse = "Erreur : File vide";
                codeStatut = 400;
            }
        } else if (path.equals("/tickets/count") && methode.equals("GET")) {
            reponse = "Personnes en attente : " + Main.taskService.countTickets();
            codeStatut = 200;
        } else {
            reponse = "Methode ou chemin non autorise";
            codeStatut = 405;
        }

        byte[] reponseBytes = reponse.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(codeStatut, reponseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(reponseBytes);
            os.flush();
        }
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=", 2);
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

    private String getBody(HttpExchange exchange) throws IOException {
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }
        return body.toString();
    }
}