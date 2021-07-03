package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	
	
	@PostMapping("/myip")
	public ResponseEntity<String> postMyIp(HttpServletRequest request){
		return ResponseEntity.ok(request.getRemoteAddr());
	}
	@GetMapping("/myip")
	public ResponseEntity<String> getMyIp(HttpServletRequest request){
		return ResponseEntity.ok(request.getRemoteAddr());
	}
}
