package com.example.helpers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WeatherHelper {
    private static final String WEATHER_API_KEY = "e3c538e4ce3f61c86e2f85aab4a8cc25";

    public static String getCurrentWeather(String city) throws Exception {
        String url = "http://api.openweathermap.org/data/2.5/weather"
                + "?q=" + city
                + "&appid=" + WEATHER_API_KEY
                + "&units=metric";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    // Parse the JSON response and extract the current weather
                    // ...
                }
            } finally {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }

        return ""; // Replace with the actual weather information
    }
}
