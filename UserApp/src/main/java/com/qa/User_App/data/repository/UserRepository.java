package com.qa.User_App.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.User_App.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
