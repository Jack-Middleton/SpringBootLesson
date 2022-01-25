package com.qa.User_App.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.User_App.data.entity.User;

@RestController
@RequestMapping(path = "/user")
public class Controller {
	private static long counter = 0;

	private List<User> users = new ArrayList<>(
			List.of(new User(counter++, "Fred", "Daly", 33), new User(counter++, "Sarah", "Daly", 33)));

	@GetMapping // localhost:8080/user
	public List<User> getUsers() {
		return users;
	}

	// {id} is a path variable
	// we send requests to: localhost:8080/user/{id}
	@RequestMapping(path = "/{id}", method = { RequestMethod.GET })
	// @GetMapping(path = "/{id}")
	public User getUserById(@PathVariable("id") int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		throw new EntityNotFoundException("Entity with id " + id + " was not found.");
	}

	// RequestMapping(method = { RequestMethod.POST })
	@PostMapping // accepts requests to: localhost:8080/user using POST
	public User createUser(@Valid @RequestBody User user) {
		user.setId(counter++);
		users.add(user);
		return user;
	}

	@PutMapping("/{id}")
	@ResponseBody
	public User updateUser(@PathVariable("id") long id, @Valid @RequestBody User user) {
		// TODO: Update user in list if they exist
		for (int i = 0; i < users.size(); i++) {
			if (id == users.get(i).getId()) {
				users.set(i, user);
			}
		}
		return user;

	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable("id") long id) {
		// TODO: Delete user in list if they exist
		for (var user : new ArrayList<User>(users)) {
			if (id == user.getId()) {
				users.remove(user);
			}
		}
		throw new EntityNotFoundException("Entity with id " + id + " was not found.");
	}
}
