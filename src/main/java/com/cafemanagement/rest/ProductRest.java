package com.cafemanagement.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafemanagement.wrapper.ProductWrapper;

@RequestMapping("/product")
public interface ProductRest 
{
	//add new product
	@PostMapping("/add")
	public ResponseEntity<String> addNewProduct(@RequestBody Map<String, String> requestMap);
	
	//get all products
	@GetMapping("/get")
	public ResponseEntity<List<ProductWrapper>>getAllProduct();
	
	//update product
	@PostMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestBody Map<String, String> requestMap);
	
	//delete product
	@PostMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id);
	
	//update status of the product
	@PostMapping("/updateStatus")
	public ResponseEntity<String> updateProductStatus(@RequestBody Map<String, String> requestMap);
	
	//get product by category
	@GetMapping("/getByCategory/{id}")
	public ResponseEntity<List<ProductWrapper>> getByCategory(@PathVariable Integer id);
	
	//get product by id
	@GetMapping("/getById/{id}")
	public ResponseEntity<ProductWrapper> getProductById(@PathVariable Integer id);
}
