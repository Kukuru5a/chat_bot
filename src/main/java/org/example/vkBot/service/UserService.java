package org.example.vkBot.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class UserService {
    private static final String TOKEN = "vk1.a.6mS-qVSLmKsdGt0wPt1BIfoK3u8XGXyNbZYxZ-i3LKC-vs3nA_Vo5wH0jZsLhAjsgP8AxB8aXOGEqTWqhq-dAwaEcNgIx_SKzqVxFqaErvBEY4WL2Abm_vFcyZeXEiwRFnct-OyPfvIsif5n27qoAMPomYdCDz3SRkApNCjPzg2onPvE2ejuP0Gek5ZkE-NHIurfWIpbQghwTgFsZe4voA";

    public String getUserName(Integer userId) throws IOException {
        URL url = new URL("https://api.vk.com/method/users.get?user_ids=" + userId + "&access_token=" + TOKEN + "&v=5.103");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response = new StringBuilder();
            try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            String responseString = response.toString();
            String firstName = responseString.split("\"first_name\":\"")[1].split("\"")[0];
            return firstName;
        } else {
            return "Unknown";
        }
    }
    public void sendMessage(Integer userId, String message) throws IOException {
        URL url = new URL("https://api.vk.com/method/messages.send?peer_id=" + userId + "&message=" + message + "&access_token=" + TOKEN + "&v=5.103");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
        }
    }
}
