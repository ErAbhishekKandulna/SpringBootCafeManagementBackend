package com.cafemanagement.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.cafemanagement.POJO.User;
import com.cafemanagement.wrapper.UserWrapper;

public interface UserRepository extends JpaRepository<User, Integer> 
{
	//find by email id
	User findByEmailId(@Param("email") String email);
	
	//get all users
	List<UserWrapper> getAllUser();
	
	//get all admin
	List<String> getAllAdmin();
	
	//update user status
	@Transactional
	@Modifying
	Integer updateStatus(@Param("status")String status,@Param("id")Integer id);
	
	//find by email(we don't need to write query for this JPA will provide)
	User findByEmail(String email);
}
