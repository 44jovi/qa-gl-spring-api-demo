package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// Tell Spring this is a table
@Entity
//POJO - Plain Old Java Object
public class Cat {
	
	@Id
	@GeneratedValue
	private Long id;
	boolean hasWhiskers;
	String name;	
	boolean evil;
	int length;
	
	public Cat () {
		
	}
	
	public boolean isHasWhiskers() {
		return hasWhiskers;
	}
	public void setHasWhiskers(boolean hasWhiskers) {
		this.hasWhiskers = hasWhiskers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEvil() {
		return evil;
	}
	public void setEvil(boolean evil) {
		this.evil = evil;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
}
