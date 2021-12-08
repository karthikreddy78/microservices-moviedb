package com.example.catalogue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.catalogue.models.CatalogItem;
import com.example.catalogue.models.Movie;
import com.example.catalogue.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
@EnableHystrixDashboard
@EnableCircuitBreaker
public class MovieInfoService {
	
	@Autowired
	private RestTemplate resttemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogueItem",
			commandProperties = {
					@HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds",value="2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
			})
	public CatalogItem getCatalogueItem(Rating rating) {
		System.out.println("In movie service :"+rating);
		Movie movie = resttemplate.getForObject("http://movies-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

    public CatalogItem getFallbackCatalogueItem(Rating rating) {
				return new CatalogItem("Not found", "", rating.getRating());
	}

}
