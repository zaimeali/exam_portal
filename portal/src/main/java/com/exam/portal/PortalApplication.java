package com.exam.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.portal.service.implementation.RoleServiceImpl;
import com.exam.portal.service.implementation.UserRoleServiceImpl;
import com.exam.portal.service.implementation.UserServiceImpl;

@SpringBootApplication
public class PortalApplication implements CommandLineRunner {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	RoleServiceImpl roleServiceImpl;

	@Autowired
	UserRoleServiceImpl userRoleServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World from Exam Portal");
	}
}