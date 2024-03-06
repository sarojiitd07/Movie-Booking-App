package com.movieservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieservice.entity.MovieDate;

@Repository
public interface MovieDateRepo extends JpaRepository<MovieDate, Long>{
}
