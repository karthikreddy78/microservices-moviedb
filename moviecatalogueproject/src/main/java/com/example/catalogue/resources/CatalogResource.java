package com.example.catalogue.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.catalogue.models.CatalogItem;
import com.example.catalogue.models.Movie;
import com.example.catalogue.models.Rating;
import com.example.catalogue.models.UserRating;
import com.example.catalogue.services.MovieInfoService;
import com.example.catalogue.services.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableHystrixDashboard
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	private RestTemplate resttemplate;
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	@Autowired
	private UserRatingService userRatingService;
    @RequestMapping("/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackCatalogue")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
    	   
    	UserRating userrating = userRatingService.getUserRating(userId);
    	for(Rating r:userrating.getRatingsList())
    		System.out.println(r.toString());
    	return userrating.getRatingsList().stream()
    			.map(rating -> {
    				System.out.println(rating.getMovieId());
    				return movieInfoService.getCatalogueItem(rating);
    			})
    			.collect(Collectors.toList());

    }

    
}
  
   
    
//    public List<CatalogItem> getFallbackCatalogue(@PathVariable("userId") String userId) {
//    	return Arrays.asList(new CatalogItem("No movie", "", 0));
//    }
    



//Movie movie =webClientBuilder.build()
//.get()
//.uri("http://localhost:8081/movies/" + rating.getMovieId())
//.retrieve()
//.bodyToMono(Movie.class)
//.block();


//Resttemplate Synchronous call