package com.example.demo.order;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class OrderController {
	private String json;
	HttpEntity<String> entity = null;
	
	@Autowired
	private OrderService orderservice;
	
	
	@RequestMapping("/")
	public String helloworld(){

		
		return "HelloWorld";
		
	}
	
	@RequestMapping("/orders")
	public List <Order> getAllOrders() {

		
		return orderservice.getAllOrder();
		
	}
	
	@RequestMapping("/orders/{id}")
	public Order getOrderbyid(@PathVariable String id) {
		return orderservice.getorderbyid(id);
	}

	
	@RequestMapping(method=RequestMethod.POST, value="/orders")
	public void addTopic(@RequestBody Order order) {
		
		System.out.print("I received the request" + order.getId());
	
		
		ObjectMapper mapper = new ObjectMapper();
		try {
		  json = mapper.writeValueAsString(order);
		  System.out.println(json);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
	
		String url = "https://exchange-con.herokuapp.com/api/createorder";

		// create an instance of RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		// send POST request
		
	
		  
		  entity = new HttpEntity<String>(json,headers);
		  
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
		    System.out.println("Order Successful " + response.getBody());
		} else {
		    System.out.println("Request Failed");
		}
		
		
		
	//orderservice.addOrder(order);
	}
}
