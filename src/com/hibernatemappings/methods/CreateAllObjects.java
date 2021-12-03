package com.hibernatemappings.methods;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernatemappings.entity.Course;
import com.hibernatemappings.entity.Instructor;
import com.hibernatemappings.entity.InstructorDetail;
import com.hibernatemappings.entity.Review;
import com.hibernatemappings.entity.Student;

public class CreateAllObjects {

	public static void main(String[] args) {
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		try {
			// create the objects
			
			session.beginTransaction();
			
			Instructor i1=new Instructor("Scooby","Doooo","scoobyD@gmail.com");
			InstructorDetail d1=new InstructorDetail("Channel_ScoobyDoo_Show","Mystery Solving");
			
			Instructor i2=new Instructor("Raman","Singhaniya","ramanS@gmail.com");
			InstructorDetail d2=new InstructorDetail("Channel_CookingDesi","Cooking food and eating");
			
			// associate the objects
			// calling setInstructorDetail of Instructor Class
			i1.setInstructorDetail(d1);
			i2.setInstructorDetail(d2);
		
			/*
			List<Instructor> list=session.createQuery("from Instructor",Instructor.class).getResultList();
			for(var a:list)
			{
				session.delete(a);
			}*/
			
			// Cascade type ALL => its going to save Instructor object as well as its associated
			// InstructorDetail Object 
			session.save(i1);
			session.save(i2);
			
			// Create Courses
			Course c1=new Course("Thief Catching Theoritical");
			Course c2=new Course("Murder Mystery Practical Approach");
			Course c3=new Course("Desi Veg Food");
			Course c4=new Course("Gujarati Khana Khazana");
			Course c5=new Course("South Indian Style Food");
			
			// Assigning courses to a Instructor
			i1.add(c1);
			i1.add(c2);
			i2.add(c3);
			i2.add(c4);
			i2.add(c5);
			
			// Saving the courses in db
			session.save(c1);
			session.save(c2);
			session.save(c3);
			session.save(c4);
			session.save(c5);
			
			Review c1r1=new Review("Very Scary Course.Loved it!!");
			Review c1r2=new Review("Hard Diffculty.But also good.");
			
			Review c2r1=new Review("Boring Blah Blah!!");
			Review c2r2=new Review("Not at all Diffcult");
			
			// Adding reviews to course
			// A course can have many reviews
			// 1 to Many (Unidirectional)
			c1.add(c1r1);
			c1.add(c1r2);
			c2.add(c2r1);
			c2.add(c2r2);
			
			
			
			Student s1=new Student("Charat","Sarir","ramanS@gmail.com");
			Student s2=new Student("John","Doe","johnDoe@gmail.com");
			Student s3=new Student("Amanda","Singh","johnS@gmail.com");
			Student s4=new Student("Sneha","Wilson","snehaW@gmail.com");
			
			// Assigning courses to students
			// Many to many
			// A student can have many courses
			// And a course can have many students
			c1.add(s1);
			c1.add(s2);
			c1.add(s4);
			c2.add(s4);
			c2.add(s3);
			c3.add(s1);
			c3.add(s2);
			c4.add(s4);
			c4.add(s2);
			c5.add(s1);
			c5.add(s3);
			c5.add(s2);
			
			// Saving the students in db
			session.save(s1);
			session.save(s2);
			session.save(s3);
			session.save(s4);
			
			session.getTransaction().commit();
			
		}catch(Exception e)
		{
			System.out.println("Error" +e);
		}finally {
			session.close();
			factory.close();
		}

	}

}
