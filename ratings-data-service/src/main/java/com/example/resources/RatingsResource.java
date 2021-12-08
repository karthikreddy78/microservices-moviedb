package com.example.resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratings.models.Rating;


@RestController
@RequestMapping("/rating")
public class RatingsResource {
    
    @RequestMapping("/{movieId}")
    public Rating getMovieInfo(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);

    }
}