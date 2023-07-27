package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class AllocationService {

	private AllocationRepository allocationRepository;
	private ProfessorService professorService;
	private CourseRepository courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseRepository courseService) {
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
		if (courseService.existsById(id)) {
			courseService.deleteById(id);
		}
	}

	public void deleteAll() {
		courseService.deleteAllInBatch();
	}

	public Allocation saveInternal(Allocation allocation) {

//
//		Long professorId = allocation.getProfessor().getId();
//		allocation.setProfessor(professorService.getProfessorById(professorId));
//
//		Long courseId = allocation.getCourse().getId();
//		allocation.setCourse(courseService.getCourseById(courseId));

		return allocation;
	}

}
