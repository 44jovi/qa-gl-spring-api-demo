package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Cat;

@RestController
public class CatController {
	
	@GetMapping("/")
	public String greeting(){
		return "Howdy, world!";		
	}
	
	List <Cat> cats = new ArrayList<>();
	
	@PostMapping("/create")
	public Cat createCat(@RequestBody Cat c) {
		this.cats.add(c);
		
		return this.cats.get(cats.size() -1);	 
	}
	
	@GetMapping("/getAll")
	public List<Cat> getAll() {
		return this.cats;
	}
	
	@GetMapping("/get/{id}")
	// if variable names don't match, you'd have to add it after @PathVariable
	public Cat get(@PathVariable int id) {
		
		return this.cats.get(id);
		 
	}
}
