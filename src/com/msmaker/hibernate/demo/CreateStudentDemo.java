package com.msmaker.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.msmaker.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// display the students
			displayStudents(theStudents);

			// query students: lastName = 'Silva'
			theStudents = session.createQuery("from Student s where s.lastName='Silva'").getResultList();

			// display the students
			System.out.println("\n\nStudents who have last name of Silva");
			displayStudents(theStudents);

			// query students: lastName= 'Silva' OR firstName = 'Pato'
			theStudents = session.createQuery("from Student s where s.lastName = 'Silva' OR firstName ='Pato'")
					.getResultList();

			System.out.println("\n\nStudents who have last name of Silva or first name Pato");
			displayStudents(theStudents);

			// query students where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();

			System.out.println("\n\nStudents who have email ends with gmail.com");
			displayStudents(theStudents);

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
