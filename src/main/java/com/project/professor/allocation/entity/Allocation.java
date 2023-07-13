package com.project.professor.allocation.entity;



import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Allocation")
public class Allocation {
<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
=======
	
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Enumerated(EnumType.STRING)
		@Column(name = "day", nullable = false) 
		private DayOfWeek day;
		
		@Temporal(TemporalType.TIME)
		@Column(name = "start", nullable = false) 
		private Date start;
		
		@Temporal(TemporalType.TIME)
		@Column(name = "end", nullable = false) 
		private Date end;
 
 @ManyToOne(opcional = false)
 private Professor professor;

 public Professor getProfessor() {
  return professor;
 }

 public void setProfessor (Professor professor) {
  this.professor = professor;
 }
>>>>>>> main

	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false)
	private DayOfWeek day;

	@Temporal(TemporalType.TIME)
	@Column(name = "start", nullable = false)
	private Date start;

	@Temporal(TemporalType.TIME)
	@Column(name = "end", nullable = false)
	private Date end;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

<<<<<<< HEAD
	public DayOfWeek getDay() {
		return day;
	}
=======
		public void setStart(Date start) {
			this.start = start;
		}
>>>>>>> main

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

<<<<<<< HEAD
	public Date getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Time end) {
		this.end = end;
	}
=======
		public void setEnd(Date end) {
			this.end = end;
		}
>>>>>>> main
}
