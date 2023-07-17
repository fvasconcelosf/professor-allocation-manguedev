package com.project.professor.allocation.entity;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Allocation")
public class Allocation {

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", start=" + start + ", end=" + end + ", professor="
				+ professor + ", curso=" + course + "]";
	}

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

	@ManyToOne(optional = false)
	private Professor professor;
	
	@Column(name = "professor_id", nullable = false)
	private Long professorId;
	
	@ManyToOne(optional = false)
	private Course course; 
	
	@Column (name = "course_id", nullable=false)
	private Long courseId;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

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

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	

}
