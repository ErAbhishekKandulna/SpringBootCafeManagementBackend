package com.cafemanagement.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.cafemanagement.POJO.Product;
import com.cafemanagement.wrapper.ProductWrapper;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
	//get all product
	List<ProductWrapper>getAllProduct();
	
	//update product status
	@Modifying
	@Transactional
	Integer updateProductStatus(@Param("status")String status,@Param("id")Integer id);
	
	//get product by category
	List<ProductWrapper> getProductByCategory(@Param("id")Integer id);
	
	//get product by id
	ProductWrapper getProductById(@Param("id")Integer id);
}
