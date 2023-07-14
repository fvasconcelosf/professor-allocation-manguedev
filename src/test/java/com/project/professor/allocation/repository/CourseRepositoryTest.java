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

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	// método de teste nunca vai ter retorno e não tem parâmetro
	@Test
	void findAll() {
		List<Course> courses = courseRepository.findAll();
		System.out.println(courses);
	}

	@Test
	void findById() {
		Long id = 1l;
		Optional<Course> course = courseRepository.findById(id);
		System.out.println(course);
	}

	@Test
	void create() {
		Course course1 = new Course();
		course1.setName("CourseEAD");
		course1.setId(null);

		System.out.println(course1);

	}

	@Test
	public void save() {
		Course course = new Course();
		course.setId(null);
		course.setName("CourseEAD");

		course = courseRepository.save(course);
		System.out.println(course);

	}// o save pode ser criado para criação ou atualização

	@Test
	void update() {
		Course course = new Course();
		course.setName("CourseOFF");
		course.setId(1l);

	}

	@Test
	void deletById() {
		Long id = 1l;
		courseRepository.deleteById(id);

	}

	@Test
	void deletAll() {
		courseRepository.deleteAllInBatch();
	}
}