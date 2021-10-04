package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.nio.ByteBuffer;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
