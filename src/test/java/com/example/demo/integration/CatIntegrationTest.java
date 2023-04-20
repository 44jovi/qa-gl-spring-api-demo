package com.example.demo.integration;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.domain.Cat;

// This annotation will actually run your app
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// Script here is an array.
@Sql(scripts = { "classpath:cat-schema.sql", "classpath:cat-data.sql" })
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
		// writeValueAsString throws exception, so the method needs "throws Exception"
		String newCatAsJson = this.mapper.writeValueAsString(newCat);
		RequestBuilder req = MockMvcRequestBuilders.post("/create").content(newCatAsJson)
				.contentType(MediaType.APPLICATION_JSON);

		// Short form way, see imports
		ResultMatcher checkStatus = status().isCreated();
		Cat created = new Cat(2L, true, "lexi", true, 44);
		String createdAsJson = this.mapper.writeValueAsString(created);
		ResultMatcher checkBody = content().json(createdAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGetAll() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/getAll");

		ResultMatcher checkStatus = status().isOk();

		Cat cat = new Cat(1L, false, "simba", false, 99);

		List<Cat> list = new ArrayList<>();
		list.add(cat);

		String listAsJson = this.mapper.writeValueAsString(list);
		ResultMatcher checkBody = content().json(listAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetById() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/get/1");

		ResultMatcher checkStatus = status().isOk();

		Cat cat = new Cat(1L, false, "simba", false, 99);
		String catAsJson = this.mapper.writeValueAsString(cat);
		ResultMatcher checkBody = content().json(catAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

}
