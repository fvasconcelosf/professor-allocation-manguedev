package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class AllocationService {

	private AllocationRepository allocationRepository;
	private ProfessorService professorService;
	private CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public Allocation findById(Long id) {
		Optional<Allocation> findAllocationId = allocationRepository.findById(id);
		Allocation allocation = findAllocationId.orElse(null);

		return allocation;
	}
	
	public List<Allocation> findByPofessor(Long professorId) {
		Professor professor = new Professor();
		professor.setId(professorId);
		return allocationRepository.findByProfessor(professor);
	}

	public List<Allocation> findByCourse(Long courseId) {
		Course course = new Course();
		course.setId(courseId);
		return allocationRepository.findByCourse(course);
	}

	public List<Allocation> findAll() {
		List<Allocation> allocation = allocationRepository.findAll();

		return allocation;
	}

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		return saveInternal(allocation);
	}

	public Allocation update(Allocation allocation) {

		Long allocationIdLong = allocation.getId();
		return allocationRepository.existsById(allocationIdLong) ? saveInternal(allocation) : null;

	}

	public void deleteById(Long id) {
		if (allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		allocationRepository.deleteAll();
	}

	public Allocation saveInternal(Allocation allocation) {
		
		Allocation allo = allocationRepository.save(allocation);
		
		Long professorId = allo.getProfessor().getId();
		Professor professor = professorService.findById(professorId);
		allo.setProfessor(professor);
		
		Long courseId = allo.getCourse().getId();
		Course course = courseService.findById(courseId);
		allo.setCourse(course);
		
		
		return allo;
	}

}
