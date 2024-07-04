package com.example.helpers;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TimeZoneHelper {
    private static final String TIMEZONE_API_URL = "http://worldtimeapi.org/api/ip/";

    public static String getTimeZoneDifference() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(TIMEZONE_API_URL);
            HttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            String timeZone = getTimeZoneOffset(result);
            return timeZone;
        }
    }

    private static String getTimeZoneOffset(String response) {
        // Parse the response to get the time zone offset
        // Here you would extract the necessary information from the response
        // For simplicity, let's assume the response contains the offset directly
        return "GMT+05:30"; // Replace this with the actual offset extracted from the response
    }
}
