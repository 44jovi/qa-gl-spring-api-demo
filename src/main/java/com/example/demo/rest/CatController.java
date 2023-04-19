package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Cat;

@RestController
public class CatController {
	
	@GetMapping("/")
	public String greeting(){
		return "Howdy, world!";		
	}
	
	List <Cat> cats = new ArrayList<>();
	
	public Cat createCat(Cat c) {
		this.cats.add(c);
		
		return this.cats.get(cats.size() -1);	
	}
}
