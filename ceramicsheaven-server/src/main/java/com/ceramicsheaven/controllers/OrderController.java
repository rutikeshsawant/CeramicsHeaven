package com.ceramicsheaven.controllers;

import com.ceramicsheaven.model.Address;
import com.ceramicsheaven.exceptions.OrderException;
import com.ceramicsheaven.exceptions.UserException;
import com.ceramicsheaven.model.Order;
import com.ceramicsheaven.model.User;
import com.ceramicsheaven.services.OrderService;
import com.ceramicsheaven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/orders")
@RestController
public class OrderController {


	private OrderService orderService;
	private UserService userService;

	@Autowired
	public OrderController(OrderService orderService, UserService userService) {
		this.orderService = orderService;
		this.userService = userService;
	}

	
	@GetMapping("/user")
	public ResponseEntity<List<Order>> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Order> orders = orderService.usersOrderHistory(user.getId());
		return new ResponseEntity<List<Order>>(orders,HttpStatus.CREATED);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Order> findOrderById(@PathVariable("Id")Long orderId,@RequestHeader("Authorization") String jwt)throws UserException, OrderException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Order order = orderService.findOrderById(orderId);
		
		return new ResponseEntity<Order>(order,HttpStatus.ACCEPTED);
	}
}
