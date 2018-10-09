package com.capgemini.order.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exception.OrderNotFoundException;
import com.capgemini.order.repository.OrderRepository;
import com.capgemini.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<Order> viewAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public Order findOrderById(int orderId)throws OrderNotFoundException {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(!optionalOrder.isPresent()) {
			throw new OrderNotFoundException("Order does not exists");
		}
		
		return optionalOrder.get();
	}

	@Override
	public void deleteOrder(Order order) throws OrderNotFoundException {
		 orderRepository.save(order);
	}

	
}
