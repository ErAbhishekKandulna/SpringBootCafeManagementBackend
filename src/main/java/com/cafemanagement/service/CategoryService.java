package com.cafemanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafemanagement.POJO.Category;

public interface CategoryService 
{
	//add new category
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap);
	
	
	//get all category
	public ResponseEntity<List<Category>> getAllCategory(String filterValue);
	
	//update category
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap);
}
