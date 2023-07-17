package com.project.professor.allocation.repository;


import com.project.professor.allocation.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	public void findAll() {
		List<Department> departments = departmentRepository.findAll();
		
		System.out.println(departments);
	}
	
	public void findById() {
		Long id = 1L;
		Optional<Department> department = departmentRepository.findById(id);
//		Department department = departmentRepository.findById(id).orElse(null);
		
		System.out.println(department);

	}

	public void create() {
		Department department = new Department();
		department.setId(null);
		department.setName("Departmento 1");	
		
		System.out.println(department);
	}
	
	public void update() {
		Department department = new Department();
		department.setId(null);
		department.setName("Departmento 2");
	}
	
	public void deleteById() {
		Long id = 1L;
		departmentRepository.deleteById(id);
	}
	
	public void deleteAll() {
		departmentRepository.deleteAll();
	}
}
