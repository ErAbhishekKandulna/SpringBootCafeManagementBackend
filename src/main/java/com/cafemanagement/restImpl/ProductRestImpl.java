package com.cafemanagement.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafemanagement.constents.CafeConstants;
import com.cafemanagement.rest.ProductRest;
import com.cafemanagement.service.ProductService;
import com.cafemanagement.utils.CafeUtils;
import com.cafemanagement.wrapper.ProductWrapper;

@RestController
public class ProductRestImpl implements ProductRest 
{
	@Autowired
	private ProductService productService;
	
	//add new product API implementation
	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) 
	{
		try 
		{
			return productService.addNewProduct(requestMap);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//get all products
	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() 
	{
		try 
		{
			return productService.getAllProduct();
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//update product
	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) 
	{
		try 
		{
			return productService.updateProduct(requestMap);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//delete product
	@Override
	public ResponseEntity<String> deleteProduct(Integer id) 
	{
		try 
		{
			return productService.deleteProduct(id);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//update product status
	@Override
	public ResponseEntity<String> updateProductStatus(Map<String, String> requestMap) 
	{
		try 
		{
			return productService.updateProductStatus(requestMap);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// get product by category
	@Override
	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) 
	{
		try 
		{
			return productService.getByCategory(id);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//get product by id
	@Override
	public ResponseEntity<ProductWrapper> getProductById(Integer id) 
	{
		try 
		{
			return productService.getProductById(id);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ProductWrapper(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
