package com.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.Entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
