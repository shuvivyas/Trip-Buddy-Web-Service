package com.example;

import com.example.helpers.TimeZoneHelper;
import com.example.helpers.WeatherHelper;
import com.example.models.TripBuddyResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

@Path("/tripbuddy")
public class TripBuddyService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TripBuddyResponse getTripInformation() {

        try {
            String sourceWeather = WeatherHelper.getCurrentWeather("source");
            String destinationWeather = WeatherHelper.getCurrentWeather("destination");
            String timeZoneDifference = TimeZoneHelper.getTimeZoneDifference();

            // Return the response with an empty list of activities
            return new TripBuddyResponse(sourceWeather, destinationWeather, timeZoneDifference, Collections.emptyList());
        } catch (Exception e) {
            // Handle exceptions, e.g., log them or return an error response
            e.printStackTrace();
            // For simplicity, returning null here, but handle appropriately in a real application
            return null;
        }
    }
}
