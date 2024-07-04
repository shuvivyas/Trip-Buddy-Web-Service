package com.example.helpers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TimeZoneHelper {
    private static final String TIMEZONE_API_KEY = "U199ENV7J6RT"; // Your new TimezoneDB API key
    private static final String NOMINATIM_API_URL = "https://nominatim.openstreetmap.org/search";

    public static String getTimeZoneDifference(String source, String destination) throws Exception {
        double[] sourceCoordinates = getCoordinates(source);
        double[] destinationCoordinates = getCoordinates(destination);
    
        String sourceUrl = buildTimeZoneUrl(sourceCoordinates[0], sourceCoordinates[1]);
        String destinationUrl = buildTimeZoneUrl(destinationCoordinates[0], destinationCoordinates[1]);
    
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Getting timezone offset as String directly
            String sourceOffset = getTimezoneOffset(httpClient, sourceUrl);
            String destinationOffset = getTimezoneOffset(httpClient, destinationUrl);
    
            // Calculate the time zone difference in seconds
            long differenceInSeconds = Long.parseLong(destinationOffset) - Long.parseLong(sourceOffset);
    
            // Convert difference to hours and minutes
            long hours = differenceInSeconds / 3600;
            long minutes = (differenceInSeconds % 3600) / 60;
    
            // Construct the string representation of the time zone difference
            String sign = (hours >= 0) ? "+" : "-";
            hours = Math.abs(hours);
            return String.format("%s%d:%02d", sign, hours, minutes);
        }
    }
    

    private static double[] getCoordinates(String city) throws Exception {
        String url = String.format("%s?q=%s&format=json&limit=1", NOMINATIM_API_URL, city);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    double lat = jsonObject.getDouble("lat");
                    double lon = jsonObject.getDouble("lon");
                    return new double[]{lat, lon};
                }
            }
        }
        throw new RuntimeException("Failed to get coordinates for city: " + city);
    }

    private static String buildTimeZoneUrl(double lat, double lon) {
        return "http://api.timezonedb.com/v2.1/get-time-zone"
                + "?key=" + TIMEZONE_API_KEY
                + "&format=json"
                + "&by=position"
                + "&lat=" + lat
                + "&lng=" + lon;
    }

    private static String getTimezoneOffset(CloseableHttpClient httpClient, String url) throws Exception {
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
    
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(result);
                String offset = json.getString("gmtOffset");
                return offset;
            }
        } finally {
            EntityUtils.consumeQuietly(response.getEntity());
        }
    
        return null;
    }
}
    
