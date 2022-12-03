package com.cafemanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafemanagement.wrapper.ProductWrapper;

public interface ProductService 
{
	//add new product
	public ResponseEntity<String> addNewProduct(Map<String, String>requestMap);
	
	//get all products
	public ResponseEntity<List<ProductWrapper>> getAllProduct();
	
	//update product
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap);
	
	//delete product
	public ResponseEntity<String> deleteProduct(Integer id);
	
	//update product status
	public ResponseEntity<String> updateProductStatus(Map<String, String>requestMap);
	
	//get product by category
	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);
	
	//get product by id
	public ResponseEntity<ProductWrapper> getProductById(Integer id);
}
