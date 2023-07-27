package com.project.professor.allocation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity
@Table(name = "Professor")
public class Professor {

	@Override
	public String toString() {
		return "Professor [id=" + id + ", cpf=" + cpf + ", name=" + name + ", depart=" + department + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, length = 11)
	private String cpf;

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Schema(allOf = Department.class, accessMode = AccessMode.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartament(Department department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
