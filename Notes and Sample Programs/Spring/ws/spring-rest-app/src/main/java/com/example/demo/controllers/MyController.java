package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

//whatever you return is a response body only no need for @ResponseBody like in web MVC
@RestController
public class MyController {
	@GetMapping(path="/", produces="text/xml")
	public String abc() {
		return "<html><body><h1>Welcome to REST API application</h1></body></html>";
	}
	@GetMapping(path="/greet")
	public String greet1() {
		return "Have a good day :) GET";
	}
	@PutMapping(path="/greet")
	public String greet2() {
		return "Have a good day :) PUT";
	}
	@PostMapping(path="/greet")
	public String greet3() {
		return "Have a good day :) POST";
	}
	@DeleteMapping(path="/greet")
	public String greet4() {
		return "Have a good day :) DELETE";
	}
	@PatchMapping(path="/greet")
	public String greet5() {
		return "Have a good day :) PATCH";
	}
}
//its not returning a jsonresponse. its plain html