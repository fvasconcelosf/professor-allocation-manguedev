package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private ProfessorRepository professorRepository;

	private DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;

		this.departmentService = departmentService;
	}

	public Professor findById(Long id) {
		Optional<Professor> findProfessorId = professorRepository.findById(id);
		Professor professor = findProfessorId.orElse(null);
		return professor;
	}

	public List<Professor> findAll(String name) {
		return name == null ? professorRepository.findAll() : professorRepository.findByNameContainingIgnoreCase(null);
	}

	public Professor create(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		return professorRepository.existsById(id) ? saveInternal(professor) : null;
	}

	public void deleteById(Long id) {
		if (professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	private Professor saveInternal(Professor professor) {
		Professor professorSave = professorRepository.save(professor);
		Long departmentId = professorSave.getDepartment().getId();
		Department department = departmentService.findById(departmentId);
		professorSave.setDepartament(department);

		return professorSave;

	}

}
