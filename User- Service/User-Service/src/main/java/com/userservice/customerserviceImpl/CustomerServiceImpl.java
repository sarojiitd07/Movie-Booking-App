package com.userservice.customerserviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.Entity.Customer;
import com.userservice.customerservice.CustomerService;
import com.userservice.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer getCustomer(Long customerId) {
		return customerRepo.findById(customerId).orElseThrow(()->new RuntimeException("Customer doesn't exist"));
	}

}
