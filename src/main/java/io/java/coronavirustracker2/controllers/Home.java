package io.java.coronavirustracker2.controllers;

import io.java.coronavirustracker2.models.LocationStats;
import io.java.coronavirustracker2.service.coronavirusdta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Home {

    @Autowired //can autowire services
    coronavirusdta coronavirusdta;

    @GetMapping("/") //connects with the home html file
    public String home(Model model){
        List<LocationStats> allStats = coronavirusdta.getAllstats();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestNumCases()).sum();
        int sumDiff = allStats.stream().mapToInt(stat -> stat.getDifference()).sum();

        model.addAttribute("locationstats", coronavirusdta.getAllstats());
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("sumDiff", sumDiff);

        return "home";
    }
}
