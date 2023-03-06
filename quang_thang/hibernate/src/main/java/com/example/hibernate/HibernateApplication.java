package com.example.hibernate;

import com.example.hibernate.entity.Student;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HibernateApplication.class, args);
		StudentDao studentDao = new StudentDao();
		Student student = new Student("Bui", "Thang", "thangdeptraoi@gmail.com");
		Student student1 = new Student("Bui", "Thang2", "thangd2@gmail.com");
		studentDao.saveStudent(student);
		studentDao.saveStudent(student1);

		List< Student > students = studentDao.getStudents();
		students.forEach(s -> System.out.println(s.getFirstName()));
	}

}
