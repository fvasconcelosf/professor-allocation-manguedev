package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class DepartmentService {

	private DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;

	}

	public Department findById(Long id) {

		Optional<Department> findDepartmentId = departmentRepository.findById(id);
		Department department = findDepartmentId.orElse(null);

		return department;
	}

	public List<Department> findAll(String name) {
		return name == null ? departmentRepository.findAll() : departmentRepository.findByNameIgnoreCase(name);
	}

	public Department create(Department department) {
		department.setId(null);

		return departmentRepository.save(department);
	}

	public Department Update(Department department) {
		Long id = department.getId();
		return (id != null && departmentRepository.existsById(id)) ? departmentRepository.save(department) : null;

	}

	public void deleteById(Long id) {
		if (departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}

}
