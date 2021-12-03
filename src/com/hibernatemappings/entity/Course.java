package com.hibernatemappings.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	// define our fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	// a instructor will have many courses //many course, one instructor
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="instructor_id") // column in course table
	private Instructor instructor;
	
	@OneToMany(fetch = FetchType.LAZY,cascade=  CascadeType.ALL)
	@JoinColumn(name="course_id") // column in review table(Exceptional for UniDirectional)
	private List<Review> reviews;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="course_student",
	joinColumns = @JoinColumn(name="course_id"),
	inverseJoinColumns = @JoinColumn(name="student_id")
	)// course_student is the table name
	private List<Student> students;
	
	// define constructors
	public Course() {}

	public Course(String title) {
		this.title = title;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	// define toString
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title;
	}
	
	// add Convenience method
	public void add(Review theReview)
	{
		if(reviews==null)
		{
			reviews=new ArrayList<>();
		}
		reviews.add(theReview);
	}
	
	// add convenience method
	public void add(Student theStudent)
	{
		if(students==null)
		{
			students=new ArrayList<>();
		}
		students.add(theStudent);
	}
	
}
