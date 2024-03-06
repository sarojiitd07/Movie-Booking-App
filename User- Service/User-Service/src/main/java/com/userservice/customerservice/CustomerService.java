package com.userservice.customerservice;

import com.userservice.Entity.Customer;

public interface CustomerService {
	
	Customer createCustomer(Customer customer);
	Customer getCustomer(Long customerId);

}
