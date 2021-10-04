package com.example.demo.services;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listAll(){
        return (List<Order>) orderRepository.findAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Order get(int id) {
        return orderRepository.findById(id).get();
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }


    public List<Order> findByCustomer(int id ){
        return (List<Order>) orderRepository.findByCustomerid(id);
    }
}
