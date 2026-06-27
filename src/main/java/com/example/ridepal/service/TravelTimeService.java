package com.example.ridepal.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Service
public class TravelTimeService {
    private static final String ROUTES_API_URL =
            "https://routes.googleapis.com/directions/v2:computeRoutes";

    @Value("${google.maps.api.key}")
    private String apiKey;

    public Time getTravelTime(String origin, String destination) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            ObjectNode body = objectMapper.createObjectNode();
            body.set("origin",
                    objectMapper.createObjectNode().put("address", origin));
            body.set("destination",
                    objectMapper.createObjectNode().put("address", destination));
            body.put("travelMode", "DRIVE");

            String requestBody = objectMapper.writeValueAsString(body);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-Goog-Api-Key", apiKey);
            // Only ask for the fields we actually use.
            headers.set("X-Goog-FieldMask", "routes.duration");

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.exchange(
                    ROUTES_API_URL, HttpMethod.POST, entity, String.class).getBody();

            return parseTravelTimeFromJson(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Time parseTravelTimeFromJson(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);

            if (root != null && root.has("routes") && root.get("routes").isArray()) {
                JsonNode routes = root.get("routes");

                if (routes.size() > 0) {
                    JsonNode route = routes.get(0);

                    if (route.has("duration")) {
                        long seconds = parseDurationSeconds(route.get("duration").asText());

                        long hours = TimeUnit.SECONDS.toHours(seconds);
                        long minutes = TimeUnit.SECONDS.toMinutes(seconds) % 60;
                        long remainingSeconds = seconds % 60;

                        return new Time((int) hours, (int) minutes, (int) remainingSeconds);
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private long parseDurationSeconds(String duration) {
        if (duration == null) {
            return 0;
        }
        String value = duration.trim();
        if (value.endsWith("s")) {
            value = value.substring(0, value.length() - 1);
        }
        return (long) Double.parseDouble(value);
    }
}
