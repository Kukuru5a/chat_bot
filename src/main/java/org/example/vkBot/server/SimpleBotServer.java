package org.example.vkBot.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.InetSocketAddress;

public class SimpleBotServer {


    static class ParseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("POST")) {
                var requestBody = exchange.getRequestBody();
                BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
                StringBuilder requestBodyString = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBodyString.append(line);
                }
                String url = requestBodyString.toString();

                // Выполняем парсинг
                String result = parseUrl(url);

                // Отправляем результат
                exchange.sendResponseHeaders(200, result.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(result.getBytes());
                os.close();
            }
        }

        public static String parseUrl(String url) {
            try {
                Document doc = Jsoup.connect(url).get();
                Elements links = doc.select("a[href]");

                StringBuilder parsedContent = new StringBuilder();
                parsedContent.append("Parsed content for URL: ").append(url).append("\n");

                for (Element link : links) {
                    String href = link.attr("href");
                    String text = link.text();
                    parsedContent.append("Link: ").append(href).append("\n");
                    parsedContent.append("Text: ").append(text).append("\n");
                }

                return parsedContent.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error parsing URL: " + url;
            }
        }
    }
}
