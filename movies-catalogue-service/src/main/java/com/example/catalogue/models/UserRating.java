package com.example.catalogue.models;

import java.util.List;

public class UserRating {

	private List<Rating> ratingsList;

	public UserRating()
	{
		
	}
	public UserRating(List<Rating> ratingsList2) {
		this.ratingsList=ratingsList2;
		// TODO Auto-generated constructor stub
	}

	public List<Rating> getRatingsList() {
		return ratingsList;
	}

	public void setRatingsList(List<Rating> ratingsList) {
		this.ratingsList = ratingsList;
	}
	@Override
	public String toString() {
		return "UserRating [ratingsList=" + ratingsList + "]";
	}
	
	
	
}
