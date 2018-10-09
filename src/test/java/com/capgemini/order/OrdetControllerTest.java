package com.capgemini.order;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.order.controller.OrderController;
import com.capgemini.order.entity.Order;
import com.capgemini.order.service.OrderService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrdetControllerTest {
	@Mock
	private OrderService orderService;
	
	
	@InjectMocks
	private OrderController orderController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}
	
	@Test
	public void testAddOrder() throws Exception {
		when(orderService.addOrder(Mockito.isA(Order.class))).thenReturn(new Order(456,"juice",LocalDate.of(2018, 10, 8)));
		mockMvc.perform(post("/order").
				contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\r\n" + 
						"	\"orderId\":456,\r\n" + 
						"	\"orderName\":\"juice\",\r\n" + 
						"	\"orderDate\":\"2018-10-08\"\r\n" + 
						"}")
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFindOrderById() throws Exception {
		Order order = new Order(456,"juice",LocalDate.of(2018, 10, 8));
		when(orderService.findOrderById(456)).thenReturn(order);
		mockMvc.perform(get("/order/456").
				accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.orderId").exists())
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testDelete() throws Exception {
		Order order = new Order(456,"juice",LocalDate.of(2018, 10, 8));

		when(orderService.findOrderById(456)).thenReturn(order);
		mockMvc.perform(delete("/order/456").
				accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andDo(print());
	}
	
	/*@Test
	public void testList() {
		when(orderService.viewAllOrders()).thenReturn(order);
		
	}

	*/
	

}
