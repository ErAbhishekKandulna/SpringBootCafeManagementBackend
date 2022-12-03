package com.cafemanagement.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/category")
public interface CategoryRest 
{
	//add new category
	@PostMapping("/add")
	public ResponseEntity<String> addNewCategory(@RequestBody(required = true)Map<String, String> requestMap);
	
	//get all category
	@GetMapping("/get")
	public ResponseEntity<?> getAllCategory(@RequestParam(required = false)String filterValue);
	
	//update category
	@PostMapping("/update")
	public ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String, String> requestMap);
}
