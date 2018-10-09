package com.capgemini.order.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exception.OrderNotFoundException;
import com.capgemini.order.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	Logger LOGGER = Logger.getLogger(OrderController.class.getName());
	
	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		ResponseEntity<Order> responseEntity = new ResponseEntity<Order>(orderService.addOrder(order),
				HttpStatus.OK);

		return responseEntity;
	}
	
	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable int orderId) throws OrderNotFoundException{

		Order order1 = orderService.findOrderById(orderId);
		if (order1 != null) {
			orderService.deleteOrder(order1);
			return new ResponseEntity<Order>(HttpStatus.OK);
		}
		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> findOrderById (@PathVariable int orderId) throws OrderNotFoundException{
		Order order1 = orderService.findOrderById(orderId);
		if(order1 != null) {
			return new ResponseEntity<Order>(orderService.findOrderById(orderId),HttpStatus.OK);
		}
		LOGGER.info("ORDER NOT FOUND"+"RETURNED HTTP STATUS 404");
		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
	}
	@GetMapping("/orderlist") 
	public ResponseEntity<List> listAllOrders(){
		List<Order> list = orderService.viewAllOrders();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}
		
		/*@GetMapping("/list") 
		public ResponseEntity<Order> getAllOrders(){
			List<Order> list = orderService.viewAllOrders();
			return new ResponseEntity<Order>(order,HttpStatus.OK);
		}*/
	}
		

	


