package com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByIdDocument(String idDocument);
	
}