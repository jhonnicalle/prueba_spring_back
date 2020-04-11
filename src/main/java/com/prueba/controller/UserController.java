package com.prueba.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.exception.ResourceNotFoundException;
import com.prueba.model.User;
import com.prueba.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/list")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") String userId)
	      throws ResourceNotFoundException {
	    User user = userRepository.findByIdDocument(userId);
	    return ResponseEntity.ok().body(user);
	  }


	  @PostMapping("")
	  public User createUser(@Valid @RequestBody User user) {
	    return userRepository.save(user);
	  }

	  
	  @PutMapping("/{id}")
	  public ResponseEntity<User> updateUser(
	      @PathVariable(value = "id") String userId, @Valid @RequestBody User userDetails)
	      throws ResourceNotFoundException {

	    User user =
	        userRepository
	            .findByIdDocument(userId);

	    user.setEmail(userDetails.getEmail());
	    user.setLastName(userDetails.getLastName());
	    user.setName(userDetails.getName());
	    user.setUpdatedAt(new Date());
	    final User updatedUser = userRepository.save(user);
	    return ResponseEntity.ok(updatedUser);
	  }


	  @DeleteMapping("/{id}")		
	  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") String userId) throws Exception {
	    User user =
	        userRepository
	            .findByIdDocument(userId);

	    userRepository.delete(user);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	  }
}
