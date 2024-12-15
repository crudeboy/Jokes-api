package com.jazz.jokes.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jazz.jokes.Enum.JokeCategories;
import com.jazz.jokes.Enum.UrlParameters;
import com.simtechdata.jokes.Jokes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.simtechdata.jokes.enums.Category.*;

@Service
@Slf4j
public class JokesIntegration {

    // Static field for HttpClient
    private static HttpClient client;
    private static String jokeApiUrl = "https://v2.jokeapi.dev/joke/";

    // Static block for setup
    static {
        System.out.println("Initializing setup...");
        // Create a single instance of HttpClient for reuse
        client = HttpClient.newHttpClient();
    }

    private static String urlBuilder(JokeCategories category, Map<String, String> urlEntries){
        StringBuilder urlWithParams = new StringBuilder(jokeApiUrl);
        urlWithParams.append(category.toString());
        if(urlEntries != null && !urlEntries.isEmpty()){
            urlWithParams.append("?");
            urlEntries.forEach((key, value) -> {
                String encodedKey = URLEncoder.encode(key, StandardCharsets.UTF_8);
                String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8);
                urlWithParams.append(encodedKey).append("=").append(encodedValue).append("&");
            });
            // Remove the trailing "&"
            urlWithParams.setLength(urlWithParams.length() - 1);
        }
        return urlWithParams.append("&safe-mode").toString();
    }

    private static JsonNode getResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.body());
    }


    public static String readAJoke() {
        String jokeString = "";
        try {
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("type", "single");
            queryParams.put("amount", "1");

            String url = urlBuilder(JokeCategories.Programming, queryParams);
            System.out.println(" Url " + url);
            // Build the HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlBuilder(JokeCategories.Programming, queryParams)))
                    .GET()
                    .build();

            JsonNode jsonNode = getResponse(request);

            // Check if it's a single or two-part joke
            if (jsonNode.has("joke")) {
                // Single joke
                System.out.println("Here's a joke: " + jsonNode.get("joke").asText());
                jokeString = jsonNode.get("joke").asText();
            } else if (jsonNode.has("setup") && jsonNode.has("delivery")) {
                // Two-part joke
                System.out.println("Setup: " + jsonNode.get("setup").asText());
                System.out.println("Delivery: " + jsonNode.get("delivery").asText());
            } else {
                System.out.println("Couldn't fetch a joke!");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return jokeString;
    }

    public static void main(String[] args) {
        readAJoke();
    }

}
