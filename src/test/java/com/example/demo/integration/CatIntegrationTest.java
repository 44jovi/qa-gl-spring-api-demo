package com.example.demo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.domain.Cat;

// This annotation will actually run your app
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CatIntegrationTest {

	// Test classes don't typically use constructors therefore:
	@Autowired
	private MockMvc mvc;
	
	// Convert to JSON
	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		
		Cat newCat = new Cat(true, "lexi", true, 44);
		//This throws exception, so the method needs "throws Exception"
		String newCatAsJson = this.mapper.writeValueAsString(newCat);
		RequestBuilder req = MockMvcRequestBuilders.post("/create").content(newCatAsJson);
	}

}
