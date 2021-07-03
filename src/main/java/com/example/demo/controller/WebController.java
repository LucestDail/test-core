package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.PostsService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {
	
	private PostsService postsService;
	
	@GetMapping("/")
	public String main(Model model) {
		System.out.println("WebController main activated");
		model.addAttribute("posts",postsService.findAllDesc());
		return "main";
	}
	
	@GetMapping("/myip")
	public String myip(Model model) {
		System.out.println("WebController myip activated");
		return "myip";
	}
	
	@GetMapping("/getSummonerId")
	public String getSummonerId(Model model) {
		System.out.println("WebController getSummonerId activated");
		//model.addAttribute("posts",postsService.findAllDesc());
		return "getSummonerId";
	}
}
