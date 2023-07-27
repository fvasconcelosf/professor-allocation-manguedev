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
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class ProfessorRespositoryTest {

	@Autowired
	ProfessorRepository professorRepository;

	@Test
	public void findAll() {
		List<Professor> professors = professorRepository.findAll();

		System.out.println(professors);
	}

	@Test
	public void findById() {
		Long id = 1L;
		Optional<Professor> professorId = professorRepository.findById(id);
		Professor professor = professorId.orElse(null);

		System.out.println(professor);
	}

	@Test
	void save() {
		Department department1 = new Department();
		department1.setId(1L);

		Professor professor = new Professor();
		professor.setId(1L);
		professor.setCpf("111.111.111-11");
		professor.setName("murilo");
		professor.setDepartament(department1);

	}

	public void update() {
		Professor professor = new Professor();
		professor.setName("tulio");
		professor.setId(null);

		Professor professor2 = professorRepository.save(professor);

		System.out.println(professor2);
	}

	@Test
	public void deleteById() {
		Long id = 1L;
		professorRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

}
