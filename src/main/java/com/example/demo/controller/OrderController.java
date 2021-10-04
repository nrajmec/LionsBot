package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public @ResponseBody Iterable<Order> getAllOrders ()
    {
        return orderService.listAll();
    }

    @RequestMapping(value = "/orders/customer/{customerid}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Order> getOrderByCustomerId (@PathVariable(value="customerid") int id)
    {
        return orderService.findByCustomer(id);
    }

    @RequestMapping(value = "/orders/{order_id}", method = RequestMethod.GET)
    public @ResponseBody Order getOrderByOrderId (@PathVariable(value="order_id") int id)
    {
        return orderService.get(id);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String createNewOrder (@RequestBody Order order)
    {
        orderService.save(order);
        return "New order added successfully";
    }

    @RequestMapping(value = "/orders/update/{order_id}", method = RequestMethod.PUT)
    public String updateOrder(@PathVariable(value="order_id") int id, @RequestBody Order order) {
        Optional<Order> orderdata = orderRepository.findById(id);
//                .orElseThrow(() -> new RuntimeException("Order not present for the id :" + id));

        if (orderdata.isPresent()) {
            Order _order = orderdata.get();
            _order.setCustomer_id(order.getCustomer_id());
            _order.setOrder_date(order.getOrder_date());
            _order.setTotal_price(order.getTotal_price());
            _order.setNum_items(order.getNum_items());
            orderService.save(_order);
            return "Successfully updated order " + id;
        } else{
            throw new RuntimeException("Order not present for the id :" + id);
        }
    }

    @DeleteMapping(value = "/orders/delete/{id}")
    public String deleteOrderById (@PathVariable(value="id") int id)
    {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return "Order deleted with " + id;
        } else
        {
            throw new RuntimeException("Order not present for the id " + id);
        }
    }
}
