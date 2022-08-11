package com.exam.portal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.portal.entity.Role;
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

	public Role getCommonRole() {
		return roleServiceImpl.findByName("USER");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World from Exam Portal");

		List<String> roleNames = new ArrayList<>();
		roleNames.add("USER");
		roleNames.add("STUDENT");
		roleNames.add("TEACHER");
		roleNames.add("ADMIN");

		long i = 1L;

		for(String rn : roleNames) {
			// roleServiceImpl.createRole(new Role(i++, rn));
			System.out.print(i++);
			System.out.println(" : " + rn);
		}
	}
}