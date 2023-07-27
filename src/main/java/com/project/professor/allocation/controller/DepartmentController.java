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

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Departments Contoller")
@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@Operation(summary = "Buscar um departamento pelo id")
	@GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {
		Department department = departmentService.findById(id);
		return department == null ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(department, HttpStatus.OK);
	}

	@Operation(summary = "Achar todos os departamentos")
	@ApiResponses(@ApiResponse(responseCode = "200", description = "ok"))
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> findAll() {
		List<Department> departments = departmentService.findAll(null);
		return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Departamento Criado"),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> save(@RequestBody Department department) {
		try {
			department = departmentService.create(department);
			return new ResponseEntity<>(department, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiResponses({@ApiResponse(responseCode = "200", description = "Departmento Atualizado"),
				   @ApiResponse(responseCode ="400", description = "BAD REQUEST", content = @Content),
				   @ApiResponse(responseCode = "400", description = "Departmento não encontrado", content = @Content )})
	@PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id, @RequestBody Department department) {
		department.setId(id);
		try {
			department = departmentService.Update(department);
			return department == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
					: new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{department_id}")
	public ResponseEntity<Void> deleteByid(Long id) {
		departmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		departmentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
