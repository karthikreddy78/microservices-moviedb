package com.example.ratings.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratings.models.Movie;
import com.example.ratings.models.Rating;
import com.example.ratings.models.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingResource {
	
	@RequestMapping("/{movieId}")
    public Rating getMovieInfo(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);

    }
	
	@RequestMapping("/user/{userId}")
	 public UserRating getUserDetails(@PathVariable("userId") String userId) {
		
		List<Rating> ratingsList = Arrays.asList(
                new Rating("550", 3),
                new Rating("3", 4)
        );
       UserRating userrating=new UserRating();
       userrating.setRatingsList(ratingsList);
       return userrating;

    }

}
