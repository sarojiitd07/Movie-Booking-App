package com.movieservice.movieservieimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieservice.entity.Customer;
import com.movieservice.entity.Movie;
import com.movieservice.entity.MovieDate;
import com.movieservice.entity.MovieTime;
import com.movieservice.model.MovieBookingStatus;
import com.movieservice.movieservice.CustomerClient;
import com.movieservice.movieservice.MovieService;
import com.movieservice.repo.MovieDateRepo;
import com.movieservice.repo.MovieRepo;
import com.movieservice.repo.MovieTimeRepo;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private MovieDateRepo movieDateRepo;
	@Autowired
	private MovieTimeRepo movieTimeRepo;
	@Autowired
	private CustomerClient customerClient;

	 @Override
	    public Movie createMovie(Movie movie) {
		 	Movie movieToBeSaved = Movie.builder().movieId(movie.getMovieId()).name(movie.getName()).build();
		 	Movie savedMovie = movieRepo.save(movieToBeSaved);
		 	List<MovieDate> dates = movie.getDates();
		 	int i=0;
		 	for(MovieDate date : dates) {
		 		MovieDate movieDateToBeSaved = MovieDate.builder().date(date.getDate()).movie(savedMovie).build();
		 		MovieDate savedDate = movieDateRepo.save(movieDateToBeSaved);
		 		List<MovieTime> movieTimes = dates.get(i).getMovieTimes();
		 		System.out.println(movieTimes);
		 		i++;
		 		for(MovieTime time: movieTimes) {
		 			time.setMovie(savedMovie);
		 			time.setMovieDate(savedDate);
		 			movieTimeRepo.save(time);
		 		}
		 	}
	        return savedMovie;
	    }

	@Override
	public Movie getMovie(String movieId) {
		return movieRepo.findById(movieId).orElseThrow(()-> new RuntimeException("Movie doesn't exist"));
	}

	
	@Override
	public List<MovieDate> getDates(String movieId) {
		Movie movie = movieRepo.findById(movieId).orElseThrow(()-> new RuntimeException("Movie doesn't exist"));
		return movie.getDates();
	}
	
	

	@Override
	public String deleteMovie(String movieId) {
		movieRepo.deleteById(movieId);
		return "Deleted successfully";
	}

	@Override
	public List<Movie> getallMovies() {
		return movieRepo.findAll();
	}

	@Override
	public List<MovieTime> getTimings(Long dateId) {
		Optional<MovieDate> movieDate = movieDateRepo.findById(dateId);
		if(!movieDate.isEmpty()) {
			return movieDate.get().getMovieTimes();
		}else {
			throw new RuntimeException("Movie Date doesn't exist");
		}
		
	}

	@Override
	public MovieBookingStatus getBookingStatus(String movieId, Long dateId, Long timeId, Long customerId) {
		Movie movie = movieRepo.findById(movieId).orElseThrow(()->new RuntimeException("Movie doesn't exist"));
		
		MovieDate movieDate = movieDateRepo.findById(dateId).orElseThrow(()->new RuntimeException("Date doesn't exist"));
		
		MovieTime movieTime = movieTimeRepo.findById(timeId).orElseThrow(()->new RuntimeException("Time doesn't exist"));
		
		Customer customer = customerClient.getCustomer(customerId);
		
		String status = "";
		if(movieTime.getNoOfSeatsAvailable()>0) {
			status = "Booked"; 
		}else {
			status = "Failed";
		}
		
		MovieBookingStatus movieBookingStatus = MovieBookingStatus.builder().movieId(movieId).movieName(movie.getName()).date(movieDate.getDate()).time(movieTime.getTime())
		.price(movieTime.getPrice()).status(status).customerName(customer.getCustomerName()).CustomerEmail(customer.getEmail()).build();
		
		if(status.equals("Booked")) {
			movieTime.setNoOfSeatsAvailable(movieTime.getNoOfSeatsAvailable()-1);
			movieTimeRepo.save(movieTime);
		}
		return movieBookingStatus;
	}

}
