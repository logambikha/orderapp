package com.capgemini.order.service;

import java.util.List;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exception.OrderNotFoundException;

public interface OrderService {
	
	public Order addOrder(Order order);
	
	public void deleteOrder(Order order)throws OrderNotFoundException ;
	
	public List<Order> viewAllOrders();
	
	public Order findOrderById(int orderId) throws OrderNotFoundException;

}
