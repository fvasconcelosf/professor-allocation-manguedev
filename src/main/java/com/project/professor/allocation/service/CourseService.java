package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {
	private CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;

	}

	public Course findById(Long id) {
		Optional<Course> findCourseId = courseRepository.findById(id);
		Course course = findCourseId.orElse(null);

		return course;
	}

	public List<Course> findaAll(String name) {
		return name == null ? courseRepository.findAll() : courseRepository.findByNameContainingIgnoreCase(name);
	}

	public Course save(Course course) {
		course.setId(null);
		return saveInternal(course);
	}

	public Course update(Course course) {
		Long id = course.getId();
		return courseRepository.existsById(id) ? saveInternal(course) : null;
	}

	public void DeleteById(Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		courseRepository.deleteAllInBatch();

	}

	private Course saveInternal(Course course) {
		course = courseRepository.save(course);

		return course;
	}
}
