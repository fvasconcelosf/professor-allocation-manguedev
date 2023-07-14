package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	@Autowired
	AllocationRepository allocationRepository;

	@Test
	void findAll() { // Achar tudo que tem em allocation
		List<Allocation> allocationAll = allocationRepository.findAll();
		System.out.println(allocationAll);
	}

	@Test
	void findById() { // achar pelo ID
		Long id = 1l;
		Optional<Allocation> allocationById = allocationRepository.findById(id);
		Allocation alloby = allocationById.orElse(null);
		System.out.println(alloby);
	}

	//@Test
	//void update() { // atualizar
		//Allocation alloUp = new Allocation();
		//Allocation alloUpSay = allocationRepository.save(alloUp);
		//System.out.println(alloUpSay);

}

