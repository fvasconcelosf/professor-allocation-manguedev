package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professor Controller.")
@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {
	private ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}

	@Operation(summary = "Achar um professor.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	@GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Professor> findById(Long id) {
		Professor professor = professorService.findById(id);
		return professor == null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(professor, HttpStatus.OK);
	}

	@Operation(summary = "Achar todos os professors.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK") })
	public ResponseEntity<List<Professor>> findAll(String name) {
		List<Professor> professors = professorService.findAll(name);
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}

	@Operation(summary = "Professor salvo.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content) })
	public ResponseEntity<Professor> create(Professor professor) {
		try {
			professor = professorService.create(professor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Professor Atualizado")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<Professor> update(Long id, Professor professor) {
		professor.setId(id);
		try {
			professor = professorService.update(professor);
			return professor == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
					: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Operation(summary = "deletar um professor.")
	@DeleteMapping(path = "/{professor_id}")
	public ResponseEntity<Void> deleteById(Long id) {
		professorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "deletar todos os professores.")
	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		professorService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
