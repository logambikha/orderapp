package com.capgemini.order;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.order.entity.Order;
import com.capgemini.order.repository.OrderRepository;
import com.capgemini.order.service.impl.OrderServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderServiceTest {
	
	@Mock
	private OrderRepository orderRepository;
	MockMvc mockMvc;
	@InjectMocks
	
	private OrderServiceImpl orderServiceImpl;
	
	
	Order order = new Order(456,"juice",LocalDate.of(2018, 10, 8));
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderServiceImpl).build();
	}
	
	@Test
	public void addOrderTest() {
		when(orderRepository.save(Mockito.isA(Order.class))).thenReturn(order);
		System.out.println(order);
		Order result = orderServiceImpl.addOrder(order);
		System.out.println(result);
		assertEquals(order, result);
	}
	
	@Test
	public void testFindcustomerById() throws Exception {
		System.out.println(order.getOrderId());
		
		Optional<Order> optionalOrder = Optional.of(order);
		when(orderRepository.findById(Mockito.isA(Integer.class))).thenReturn(optionalOrder);
		
		System.out.println(order.getOrderId() + "for" + order);
		assertEquals(optionalOrder.get(), orderServiceImpl.findOrderById(1234));
	}

	@Test
	public void TestForDeleteorder() throws Exception {
		System.out.println(order);
		orderServiceImpl.deleteOrder(order);
		verify(orderRepository).delete(order);
		System.out.println(order);
	}
	

}
