package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	 SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

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
    public void save_create() throws ParseException {
        // Arrange
        Allocation allocation = new Allocation();
        allocation.setId(null);
        allocation.setDay(DayOfWeek.SUNDAY);
        allocation.setStart(sdf.parse("12:10-0000"));
        allocation.setEnd(sdf.parse("21:00-0000"));
        allocation.setProfessorId(1L);
        allocation.setCourseId(1L);

        allocation = allocationRepository.save(allocation);

        System.out.println(allocation);
    }
    @Test
    public void save_update() throws ParseException {
        // Arrange
        Allocation allocation = new Allocation();
        allocation.setId(1L);
        allocation.setDay(DayOfWeek.MONDAY);
        allocation.setStart(sdf.parse("13:00-0300"));
        allocation.setEnd(sdf.parse("19:00-0300"));
        allocation.setProfessorId(1L);
        allocation.setCourseId(1L);
    
    }
	
    @Test
    public void deleteById() {
        Long id = 1L;
        
        System.out.println(id);
    }

    @Test
    public void deleteAll() {
        allocationRepository.deleteAllInBatch();
    }
}

	

