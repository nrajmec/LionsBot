# LionsBot

1. Open POSTMAN online or dektop application
2. Follow below commands to see the desired output
3. Before proceeding go to "Authorization" tab and use below login-password	
	a. admin-admin
	b. user-admin

GET => /customers 
	1. admin - Success
	2. user - not visible
	
	http://localhost:9091/customers
	
GET => /orders
	1. admin - Success
	2. user - not visible
	
	http://localhost:9091/orders
	
GET => /orders/customer/{customer_id}
	1. admin - Success
	2. user - Success
	
	http://localhost:9091/orders/customer/2
	
PUT => /orders/update/{order_id}
POSTMAN instructions:
	1. admin - Success
	2. user - Success
	
	http://localhost:9091/orders/update/3
	{
	    "customer_id": 4,
	    "order_date": "2020-10-04",
	    "total_price": 500.0,
	    "num_items": 5
	}
	
POST => /customers
	1. admin - Success
	2. user - not visible
	
	http://localhost:9091/customers
	{
		"customer_name": "user3",
		"email": "user3@gmail.com",
		"password": "user3",
		"contact_no": "1234567"
	}	
	
POST => /orders
	1. admin - Success
	2. user - Success
	
	http://localhost:9091/orders
	{
	    "customer_id": 5,
	    "order_date": "2021-10-02",
	    "total_price": 5000,
	    "num_items": 10
	}
	
DELETE => /customers
	1. admin - Success
	2. user - Not visible
	
	http://localhost:9091/customers/7
	
DELETE => /orders
	1. admin - Success
	2. user - Not visible

	http://localhost:9091/orders/delete/2


