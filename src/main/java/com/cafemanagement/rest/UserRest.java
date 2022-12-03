package com.cafemanagement.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafemanagement.wrapper.UserWrapper;

@RequestMapping("/user")
public interface UserRest 
{
	//sign up
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody(required = true)Map<String, String> requestMap);
	
	//log in
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);
	
	//get all users
	@GetMapping("/get")
	public ResponseEntity<List<UserWrapper>> getAllUsers();
	
	//update user
	@PostMapping("/update")
	public ResponseEntity<String>update(@RequestBody(required = true) Map<String, String>requestMap);
	
	//check token
	@GetMapping("/checkToken")
	public ResponseEntity<String> checkToken();
	
	//change password
	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap);
	
	
	//forgot password
	@PostMapping("/forgotPassword")
	ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestMap);
	
}
