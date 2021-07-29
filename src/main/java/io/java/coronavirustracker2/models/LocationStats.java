package io.java.coronavirustracker2.models;

public class LocationStats {

    private String state;
    private String country;
    private int latestNumCases;
    private int difference;


    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestNumCases() {
        return latestNumCases;
    }

    public void setLatestNumCases(int latestNumCases) {
        this.latestNumCases = latestNumCases;
    }

    @Override
    public String toString(){
        return "Location Stats { " + "state = " + state + " Country = " + country + " Latest Cases = " + latestNumCases + " }";
    }
}
