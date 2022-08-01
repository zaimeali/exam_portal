package com.exam.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.portal.entity.Role;
import com.exam.portal.entity.User;
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

		// User user = new User();
		// user.setFirstName("Ethan");
		// user.setLastName("Ali");
		// user.setEmail("ethan@exam.com");
		// user.setUsername("ethan");
		// user.setPassword("123456789");
		// user.setProfile("default.png");

		// Role role = new Role();
		// role.setId(4L);
		// role.setName("ADMIN");

		// roleServiceImpl.createRole(role);

		// Role role = roleServiceImpl.findByID(4L);

		// userRoleServiceImpl.createUserRole(userServiceImpl.createUser(user), role);

		// System.out.println(userRoleServiceImpl.findUserRoleByID(3L).get());
	}
}