package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {

	private CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@Operation(summary = "Buscar um curso pelo seu id.")
	@GetMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> findById(@PathVariable(name = "allocation_id") Long id) {
		Course course = courseService.findById(id);
		return course == null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(course, HttpStatus.OK);
	}

	@Operation(summary = "Achar todos os cursos.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "ok") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Course>> findAll() {
		List<Course> courses = courseService.findaAll(null);
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Curso criado."),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> save(Course course) {
		course = courseService.save(course);
		return new ResponseEntity<>(course, HttpStatus.CREATED);
	}

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "BAD RQUEST", content = @Content),
			@ApiResponse(responseCode = "400", description = "Não encontrado", content = @Content) })
	@PutMapping(path = "/{course_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> update(Long id, Course course) {
		course.setId(id);
		try {
			course = courseService.update(course);
			return course == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
					: new ResponseEntity<>(course, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{couse_id}")
	public ResponseEntity<Void> deleteById(Long id) {
		courseService.DeleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		courseService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
