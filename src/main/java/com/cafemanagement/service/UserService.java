package com.cafemanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafemanagement.wrapper.UserWrapper;

public interface UserService 
{
	//sign up
	ResponseEntity<String>signUp(Map<String,String> requestMap);
	
	//log in
	ResponseEntity<String>login(Map<String,String> requestMap);
	
	//get all users
	ResponseEntity<List<UserWrapper>> getAllUsers();
	
	//update user
	ResponseEntity<String>update(Map<String,String> requestMap);
	
	//check token
	ResponseEntity<String> checkToken();
	
	//change password
	ResponseEntity<String> changePassword(Map<String, String> requestMap);
	
	//forgot password
	ResponseEntity<String> forgotPassword(Map<String, String> requestMap);
}
