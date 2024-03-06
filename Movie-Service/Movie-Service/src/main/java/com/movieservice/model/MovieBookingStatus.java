package com.movieservice.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MovieBookingStatus {
	private String movieId;
	private String movieName;
	private String customerName;
	private String CustomerEmail;
	private LocalDate date;
	private LocalTime time;
	private int price;
	private String status;
}
