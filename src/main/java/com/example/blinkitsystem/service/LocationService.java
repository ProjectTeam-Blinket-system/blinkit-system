package com.example.blinkitsystem.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import org.json.JSONObject;

public class LocationService {

    public static String getCurrentCity() {

        try {

            URL url = new URL("http://ip-api.com/json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            JSONObject obj = new JSONObject(response.toString());

            return obj.getString("city");

        } catch (Exception e) {

            e.printStackTrace();
            return "Sonipat"; // fallback
        }
    }
}