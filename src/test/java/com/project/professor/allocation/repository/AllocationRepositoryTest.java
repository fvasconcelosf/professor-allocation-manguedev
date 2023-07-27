package com.project.professor.allocation.repository;


import java.sql.Time;
import java.time.DayOfWeek;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	@Autowired
	AllocationRepository allocationRepository;

	@Test // pesquisa detalhada ao banco, "find" criado no repositório da entidade.
	public void findByCourseId() {
		Allocation alloCourse = new Allocation();
		alloCourse.setId(1L);
		System.out.println(alloCourse);
	}

	@Test // pesquisa detalhada ao banco, "find" criado no repositório da entidade.
	public void findByProfessorId() {
		Allocation alloProf = new Allocation();
		alloProf.setId(1L);
		System.out.println(alloProf);
	}

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

	@Test
	public void create() {

		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDay(DayOfWeek.SUNDAY);
		allocation.setStart(Time.valueOf("20:00:00"));
		allocation.setEnd(Time.valueOf("21:00:00"));
		allocation.setIdProfessor(1L);
		allocation.setIdCourse(1L);

		allocation = allocationRepository.save(allocation);

		System.out.println(allocation);
	}

	@Test
	public void update() {

		Allocation allocation = new Allocation();
		allocation.setId(1L);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setStart(Time.valueOf("13:00:00"));
		allocation.setEnd(Time.valueOf("19:00:00"));
		allocation.setIdProfessor(1L);
		allocation.setIdCourse(1L);

	}

	@Test
	public void deleteById() {
		Long id = 1l;

		allocationRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}
}
