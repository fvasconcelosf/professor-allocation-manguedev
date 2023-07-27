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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Allocations controller")
@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {
	private AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

	@Operation(summary = "busca uma alocação pelo seu id")
	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
		Allocation allocation = allocationService.findById(id);
		return allocation == null ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(allocation, HttpStatus.OK);
	}

	@Operation(summary = "achar todas as alocações")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "ok") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<List<Allocation>>(allocations, HttpStatus.OK);
	}

	@GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name = "course_id") Long id) {
		List<Allocation> allocations = allocationService.findByCourse(id);
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> create(Allocation allocation) {
		try {
			allocation = allocationService.create(allocation);
			return new ResponseEntity<>(allocation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "alocação atualizada com sucesso! "),		
		@ApiResponse(responseCode = "400", description = "horario de inicio deve estar menor que o de fim!", content = @Content),
		@ApiResponse(responseCode = "400", description = "alocação não encontrada", content = @Content) })

	@PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> update(Long id, @RequestBody Allocation allocation) {
		allocation.setId(id);
		try {
			allocation = allocationService.update(allocation);
			return allocation == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
					: new ResponseEntity<>(allocation, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(path = "/{allocation_id}")
	public ResponseEntity<Void> deleteById(Long id) {

		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
