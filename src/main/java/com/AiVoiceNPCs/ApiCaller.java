package com.AiVoiceNPCs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiCaller {
    public String callApi(String apiUrl, String apiKey) throws IOException {
        // Create a URL object from the API endpoint URL
        URL url = new URL(apiUrl);

        // Create a HTTP connection object from the URL object
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the HTTP request method to GET
        connection.setRequestMethod("GET");

        // Set the headers
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("xi-api-key", apiKey);

        // Check the HTTP response code to ensure the request was successful
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + responseCode);
        }

        // Read the response content as a string
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Return the response content as a string
        return response.toString();
    }
}