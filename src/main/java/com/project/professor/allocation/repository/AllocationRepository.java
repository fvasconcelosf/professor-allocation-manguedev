package com.project.professor.allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
	List<Allocation> findByCourse (Long course);
	List<Allocation> findByProfessor (Long professor);

}
