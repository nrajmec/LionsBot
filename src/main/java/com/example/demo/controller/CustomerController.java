package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController
{
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public @ResponseBody Iterable<Customer> getAllCustomers ()
	{
		return customerService.listAll();
	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public String createNewCustomer (@RequestBody Customer customer)
	{
		customerService.save(customer);

		return "New customer added successfully";
	}

	@RequestMapping(value = "/customers/changepassword/{order_id}", method = RequestMethod.PUT)
	public String updateOrder(@PathVariable(value="order_id") int id, @RequestBody Customer customer) {
		Optional<Customer> customerdata = customerRepository.findById(id);

		if (customerdata.isPresent()) {
			Customer _customer = customerdata.get();
			_customer.setCustomer_name(customer.getCustomer_name());
			_customer.setEmail(customer.getEmail());
			_customer.setPassword(customer.getPassword());
			_customer.setContact_no(customer.getContact_no());
			customerService.save(_customer);
			return "Successfully updated customer " + id;
		} else{
			throw new RuntimeException("Customer not present for the id :" + id);
		}
	}

	@RequestMapping(value = "/customers/{customer_id}", method = RequestMethod.DELETE)
	public String deletecustomer (@PathVariable(value="customer_id") int id)
	{
		customerService.delete(id);
		return "Customer with id " + id + " is deleted successfully";
	}
}
