package io.java.coronavirustracker2.service;

import io.java.coronavirustracker2.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.stream.Location;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service //makes it a spring service
public class coronavirusdta {

    private String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allstats = new ArrayList<>(); //created to serve as temporary filler while data loads

    public List<LocationStats> getAllstats() {
        return allstats;
    }

    public void setAllstats(List<LocationStats> allstats) {
        this.allstats = allstats;
    }

    @PostConstruct //wit will execute this message once the class is created
    @Scheduled(cron = "* * 1 * * *") //second minute hour etc. in order (once hour every day)
    void getCovidData() throws IOException, InterruptedException {
        List<LocationStats> currentstats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(COVID_DATA_URL))
                            .build();
        HttpResponse <String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body()); //returns the url info

        StringReader Bodyreader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(Bodyreader);
        for (CSVRecord record : records) {
            LocationStats stats = new LocationStats();

            stats.setState(record.get("Province/State"));
            stats.setCountry(record.get("Country/Region"));

            int latestcases = Integer.parseInt(record.get(record.size()-1));
            int prevDayCases = Integer.parseInt(record.get(record.size()-2));

            stats.setLatestNumCases(latestcases);
            stats.setDifference(latestcases  - prevDayCases);

            currentstats.add(stats);

        }
        this.allstats = currentstats;

    }



}
