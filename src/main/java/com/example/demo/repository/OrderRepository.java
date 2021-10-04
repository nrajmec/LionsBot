package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer>
{
    List<Order> findByCustomerid(int id);
}
