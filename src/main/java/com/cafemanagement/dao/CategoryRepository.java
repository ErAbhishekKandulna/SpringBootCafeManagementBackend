package com.cafemanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafemanagement.POJO.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> 
{
	//get all category
	List<Category> getAllCategory();
}
