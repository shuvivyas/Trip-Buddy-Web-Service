package com.example.models;

import java.util.List;

public class TripBuddyResponse {
    private String sourceWeather;
    private String destinationWeather;
    private String timeZoneDifference;
    private List<String> activities;

    public TripBuddyResponse(String sourceWeather, String destinationWeather, String timeZoneDifference, List<String> activities) {
        this.sourceWeather = sourceWeather;
        this.destinationWeather = destinationWeather;
        this.timeZoneDifference = timeZoneDifference;
        this.activities = activities;
    }

    // Getters and setters
    public String getSourceWeather() {
        return sourceWeather;
    }

    public void setSourceWeather(String sourceWeather) {
        this.sourceWeather = sourceWeather;
    }

    public String getDestinationWeather() {
        return destinationWeather;
    }

    public void setDestinationWeather(String destinationWeather) {
        this.destinationWeather = destinationWeather;
    }

    public String getTimeZoneDifference() {
        return timeZoneDifference;
    }

    public void setTimeZoneDifference(String timeZoneDifference) {
        this.timeZoneDifference = timeZoneDifference;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
}
