package io.java.coronavirustracker2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusTracker2Application {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusTracker2Application.class, args);
	}

}
