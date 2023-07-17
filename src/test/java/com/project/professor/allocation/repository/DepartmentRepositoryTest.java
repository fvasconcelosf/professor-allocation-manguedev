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

import com.project.professor.allocation.entity.Department;

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

	@Test
	public void findById() {
		Long id = 1L;
		Optional<Department> department = departmentRepository.findById(id);
//		Department department = departmentRepository.findById(id).orElse(null);

		System.out.println(department);

	}

	@Test
	public void create() {
		Department department = new Department();
		department.setId(null);
		department.setName("Departmento 1");

		System.out.println(department);
	}

	@Test
	public void update() {
		Department department = new Department();
		department.setId(null);
		department.setName("Departmento 2");
	}

	@Test
	public void deleteById() {
		Long id = 1L;
		departmentRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		departmentRepository.deleteAll();
	}
}
