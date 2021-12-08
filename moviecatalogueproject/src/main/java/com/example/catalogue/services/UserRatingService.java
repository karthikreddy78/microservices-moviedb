package com.example.catalogue.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.catalogue.models.Rating;
import com.example.catalogue.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
@EnableCircuitBreaker
@EnableHystrixDashboard
public class UserRatingService {
	@Autowired
	private RestTemplate resttemplate;
	
	 @HystrixCommand(fallbackMethod = "getFallbackUserRating",
			 commandProperties = {
						@HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds",value="2000"),
						@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
						@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
						@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
				})
		public UserRating getUserRating(String userId) {
			return resttemplate.getForObject("http://rating-data-service/ratings/user/" + userId, UserRating.class);
		}
	    
	    public UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
	    	UserRating userRating=new UserRating();
	    	userRating.setRatingsList(Arrays.asList(new Rating("",0)));
	    	return userRating;
	    
	    
	    }

}
