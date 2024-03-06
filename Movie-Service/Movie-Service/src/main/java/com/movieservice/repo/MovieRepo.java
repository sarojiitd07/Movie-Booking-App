package com.movieservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieservice.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, String>{

}
