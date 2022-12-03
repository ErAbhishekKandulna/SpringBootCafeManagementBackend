package com.cafemanagement.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafemanagement.constents.CafeConstants;
import com.cafemanagement.rest.UserRest;
import com.cafemanagement.service.UserService;
import com.cafemanagement.utils.CafeUtils;
import com.cafemanagement.wrapper.UserWrapper;

@RestController
public class UserRestImpl implements UserRest 
{
	@Autowired
	UserService userService;
	
	//sign up
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) 
	{
		try 
		{
			return userService.signUp(requestMap);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	//log in
	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) 
	{
		try 
		{
			return userService.login(requestMap);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	//get all users
	@Override
	public ResponseEntity<List<UserWrapper>> getAllUsers() 
	{
		try 
		{
			return userService.getAllUsers();
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//update user
	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) 
	{
		try 
		{
			return userService.update(requestMap);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	//check token
	@Override
	public ResponseEntity<String> checkToken() 
	{
		try 
		{	
			return userService.checkToken();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//change password
	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) 
	{
		try 
		{
			return userService.changePassword(requestMap);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//forgot password
	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) 
	{
		try 
		{
			return userService.forgotPassword(requestMap);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
