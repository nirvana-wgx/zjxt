package com.ctevs.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zjxt-api/test")
public class TestController {
  
	@GetMapping("/helloworld")
	public String helloworld()  {
		return "helloworlds";
	}
	
	@GetMapping("/helloworld2")
	public String helloworld2() {
		return "helloworlds2";
	}
}
