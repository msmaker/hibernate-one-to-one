package com.msmaker.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.msmaker.hibernate.demo.entity.Instructor;
import com.msmaker.hibernate.demo.entity.InstructorDetail;
import com.msmaker.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create the objects
			/*
			 * Instructor tempInstructor = new Instructor("Chad", "Darby",
			 * "darbt@luv2code.com");
			 * 
			 * InstructorDetail tempInstructorDetail = new
			 * InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");
			 * 
			 */
			// create the objects
			Instructor tempInstructor = new Instructor("Madhu", "Patel", "Madhu@luv2code.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Guitar");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();

		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
