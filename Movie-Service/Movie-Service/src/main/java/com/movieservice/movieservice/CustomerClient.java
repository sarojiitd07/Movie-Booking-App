package com.movieservice.movieservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.movieservice.entity.Customer;

@FeignClient(url = "http://localhost:9091", value = "Customer-Client")
public interface CustomerClient {

	@GetMapping(value = "/customer/{customerId}")
	Customer getCustomer(@PathVariable Long customerId);
}
