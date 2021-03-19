package com.example.demo.order;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class OrderService {
	private List<Order> myorder= Arrays.asList(


			new Order("3334ff","ddd",45,10.2,"5555"),
			new Order("3334ff","ddd",45,10.2,"5555"),
			new Order("3334ff","ddd",45,10.2,"5555")
	
			
			);
			
			
	public List<Order> getAllOrder(){
		return myorder;
	}
			
	public Order getorderbyid(String id) {
		return myorder.stream().filter(t->t.getId().equals(id)).findFirst().get();
	}
	
	
	public void addOrder(Order neworder) {
		myorder.add(neworder);
	}
	
	
	
	

}
