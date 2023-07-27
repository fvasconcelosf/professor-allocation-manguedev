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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity
@Table(name = "Allocation")
public class Allocation {

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", start=" + start + ", end=" + end + ", professor="
				+ professor + ", curso=" + course + "]";
	}

	@Id
	@JsonProperty(access = Access.READ_ONLY)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false)
	private DayOfWeek day;

	@Schema(example = "19:00:00", type = "string")
	@Temporal(TemporalType.TIME)
	@Column(name = "start", nullable = false)
	private Date start;

	@Schema(example = "20:00:00", type = "string")
	@Temporal(TemporalType.TIME)
	@Column(name = "end", nullable = false)
	private Date end;

	@Schema(allOf = Professor.class, accessMode = AccessMode.READ_ONLY)
	@ManyToOne(optional = false)
	private Professor professor;

	@Schema(allOf = Course.class, accessMode = AccessMode.READ_ONLY)
	@ManyToOne(optional = false)
	private Course course;

	public void setIdProfessor(Long professorId) {
		Professor professor = new Professor();
		professor.setId(professorId);
		this.setProfessor(professor);
	}

	public void setIdCourse(Long courseId) {
		Course course = new Course();
		course.setId(courseId);
		this.setCourse(course);
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

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
