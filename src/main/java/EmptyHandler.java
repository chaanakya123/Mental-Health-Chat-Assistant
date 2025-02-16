import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;


public class EmptyHandler  implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        File file = new File("src/main/frontend/dist/index.html");
        if(!file.exists()) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }
        try(InputStream inputStream = new FileInputStream(file)){
            byte[] response = inputStream.readAllBytes();
            exchange.getResponseHeaders().set("Content-type", "text/html");

            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, response.length);

            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        }
    }
}
