package com.movieservice.movieservice;

import java.util.List;

import com.movieservice.entity.Movie;
import com.movieservice.entity.MovieDate;
import com.movieservice.entity.MovieTime;
import com.movieservice.model.MovieBookingStatus;

public interface MovieService {
	Movie createMovie(Movie movie);
	Movie getMovie(String movieId);
	List<Movie> getallMovies();
	List<MovieDate> getDates(String movieId);
	List<MovieTime> getTimings(Long dateId);
	String deleteMovie(String movieId);
	
	MovieBookingStatus getBookingStatus(String movieId, Long dateId, Long timeId, Long customerId);
//	List<MovieTime> getTimings(String movieId);
}
