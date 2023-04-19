package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Cat;

@RestController
public class CatController {

	@GetMapping("/")
	public String greeting() {
		return "Howdy, world!";
	}

	List<Cat> cats = new ArrayList<>();

	@PostMapping("/create")
	public ResponseEntity<Cat> createCat(@RequestBody Cat c) {		
		this.cats.add(c);
		
		Cat created = this.cats.get(cats.size() - 1);
		
		return new ResponseEntity<>(created, HttpStatus.CREATED);
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

	@DeleteMapping("/remove/{id}")
	public Cat delete(@PathVariable int id) {
		// Could also pass in the object, but would return boolean
		return this.cats.remove(id);
	}

	@PatchMapping("/update/{id}")
	// Parameter types must be wrapper classes
	public Cat update(@PathVariable int id, @RequestParam(name = "hasWhiskers", required = false) Boolean hasWhiskers,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "evil", required = false) Boolean evil,
			@RequestParam(name = "length", required = false) Integer length) {
		
		Cat c = cats.get(id);
		
		if (hasWhiskers != null) c.setHasWhiskers(hasWhiskers);
		if (name != null) c.setName(name);
		if (evil != null) c.setEvil(evil);
		if (length != null) c.setLength(length);
		
		return c;
	}
}
