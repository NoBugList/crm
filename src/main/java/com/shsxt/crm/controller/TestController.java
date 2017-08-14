package com.shsxt.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shsxt.crm.model.User;
import com.shsxt.crm.service.TestService;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("get/{id}")
//	@PostMapping
//	@PutMapping
//	@DeleteMapping
	public Map<String, Object> get(@PathVariable Integer id) {
		
		User user = testService.findById(id);
		
		Map<String, Object> result = new HashMap<>();
		result.put("resultCode", 1);
		result.put("resultMessage", "Success");
		result.put("result", user);
		
		return result;
	}
	
//	@GetMapping("get/{id}")
	@PostMapping("find")
//	@PutMapping
//	@DeleteMapping
	public Map<String, Object> getAll() {
		
		List<User> users = testService.find();
		
		Map<String, Object> result = new HashMap<>();
		result.put("resultCode", 1);
		result.put("resultMessage", "Success");
		result.put("result", users);
		
		return result;
	}
}
