package com.movieservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieservice.entity.Movie;
import com.movieservice.entity.MovieDate;
import com.movieservice.entity.MovieTime;
import com.movieservice.model.MovieBookingStatus;
import com.movieservice.movieservice.MovieService;
import com.movieservice.repo.MovieRepo;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/{movieId}")
	public ResponseEntity<Movie> getMovie(@PathVariable String movieId){
		return new ResponseEntity<Movie>(movieService.getMovie(movieId),HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<Movie>> geAlltMovies(){
		return new ResponseEntity<List<Movie>>(movieService.getallMovies(),HttpStatus.FOUND);
	}
	
	@GetMapping("/date/{movieId}")
	public ResponseEntity<List<MovieDate>> getDates(@PathVariable String movieId){
		return new ResponseEntity<List<MovieDate>>(movieService.getDates(movieId),HttpStatus.FOUND);
	}
	
	@GetMapping("/time/{dateId}")
	public ResponseEntity<List<MovieTime>> getTimings(@PathVariable Long dateId){
		return new ResponseEntity<List<MovieTime>>(movieService.getTimings(dateId),HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
		return new ResponseEntity<Movie>(movieService.createMovie(movie),HttpStatus.CREATED);
	}
	
	@DeleteMapping("{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable String movieId){
		return new ResponseEntity<>(movieService.deleteMovie(movieId),HttpStatus.OK);
	}
	
	@GetMapping("/bookingStatus/{movieId}/{dateId}/{timeId}/{customerId}")
	public ResponseEntity<MovieBookingStatus> getMovieBokkingStatus(@PathVariable String movieId, @PathVariable Long dateId, @PathVariable Long timeId, @PathVariable Long customerId){
		MovieBookingStatus bookingStatus = movieService.getBookingStatus(movieId, dateId, timeId, customerId);
		return new ResponseEntity<>(bookingStatus, HttpStatus.FOUND);
	}
}
