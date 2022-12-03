package com.cafemanagement.restImpl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafemanagement.constents.CafeConstants;
import com.cafemanagement.rest.CategoryRest;
import com.cafemanagement.service.CategoryService;
import com.cafemanagement.utils.CafeUtils;

@RestController
public class CategoryRestImpl implements CategoryRest 
{
	@Autowired
	private CategoryService categoryService;

	//add new category
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) 
	{
		try 
		{
			return categoryService.addNewCategory(requestMap);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//get all category
	@Override
	public ResponseEntity<?> getAllCategory(String filterValue) 
	{
		try 
		{
			return categoryService.getAllCategory(filterValue);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//update category API implementation
	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) 
	{
		try 
		{
			return categoryService.updateCategory(requestMap);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
