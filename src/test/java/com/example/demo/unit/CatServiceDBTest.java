package com.example.demo.unit;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.repo.CatRepo;
import com.example.demo.service.CatService;
import com.example.demo.domain.Cat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatServiceDBTest {

	// Wiring is injecting a bean into a bean
	// Autowired, looks for CatService and injects it
	@Autowired
	private CatService service;

	@MockBean
	private CatRepo repo;

	@Test
	void testCreateCat() {
		Cat cat = new Cat(false, "blah", false, 88);
		Mockito.when(this.repo.save(Mockito.any())).thenReturn(cat);
		Assertions.assertEquals(cat, this.service.createCat(cat));
	}

	@Test
	void testUpdate() {
		long id = 1L;
		Cat existing = new Cat(id, false, "blah", false, 88);
		Cat updated = new Cat(id, false, "halb", false, 88);
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updated)).thenReturn(updated);

		Assertions.assertEquals(updated, this.service.update((int) id, updated.isHasWhiskers(), updated.getName(),
				updated.isEvil(), updated.getLength()));

	}

}
