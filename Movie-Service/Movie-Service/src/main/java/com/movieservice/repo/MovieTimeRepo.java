package com.movieservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieservice.entity.MovieTime;

@Repository
public interface MovieTimeRepo extends JpaRepository<MovieTime, Long>{

}
