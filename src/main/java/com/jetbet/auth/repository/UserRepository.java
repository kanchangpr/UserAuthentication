package com.jetbet.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jetbet.auth.bean.UserBean;

public interface UserRepository extends JpaRepository<UserBean, Long>{
	
	UserBean findByUserIdAndPassword(String username, String password);
	
	UserBean findByUserId(String username);
	
}
