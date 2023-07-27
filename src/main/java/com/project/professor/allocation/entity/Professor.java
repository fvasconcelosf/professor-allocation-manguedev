package com.project.professor.allocation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Professor")
public class Professor {

	@Override
	public String toString() {
		return "Professor [id=" + id + ", cpf=" + cpf + ", name=" + name + ", depart=" + departament + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, length = 11)
	private String cpf;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "department_id", nullable = false)
	private Department departament;

	public Department getDepartament() {
		return departament;
	}

	public void setDepartament(Department departament) {
		this.departament = departament;
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
