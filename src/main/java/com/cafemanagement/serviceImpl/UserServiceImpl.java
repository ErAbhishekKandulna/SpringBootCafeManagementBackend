package com.cafemanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cafemanagement.POJO.User;
import com.cafemanagement.constents.CafeConstants;
import com.cafemanagement.dao.UserRepository;
import com.cafemanagement.jwt.CustomerUserDetailsService;
import com.cafemanagement.jwt.JwtFilter;
import com.cafemanagement.jwt.JwtUtil;
import com.cafemanagement.service.UserService;
import com.cafemanagement.utils.CafeUtils;
import com.cafemanagement.utils.EmailUtils;
import com.cafemanagement.wrapper.UserWrapper;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService 
{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomerUserDetailsService customerUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	EmailUtils emailUtils;

	//sign up
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) 
	{
		log.info("Inside Signup {}",requestMap);
		try 
		{
			if(validateSignUpMap(requestMap))
			{
				User user=userRepository.findByEmailId(requestMap.get("email"));
				if(Objects.isNull(user))
				{
					userRepository.save(getUserFromMap(requestMap));
					return CafeUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
				}
				else
				{
					return CafeUtils.getResponseEntity("Email already exist.",HttpStatus.BAD_REQUEST);
				}
			}
			else
			{
				return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA,HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//validating the request data
	private boolean validateSignUpMap(Map<String, String> requestMap)
	{
		if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
		&& requestMap.containsKey("email") && requestMap.containsKey("password"))
		{
			return true;
		}
		return false;
	}
	
	//return user object method
	private User getUserFromMap(Map<String, String> requestMap)
	{
		User user=new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		
		return user;
	}

	//log in
	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) 
	{
		log.info("Inside Login");
		try 
		{
			Authentication auth=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
					);
			
			if(auth.isAuthenticated())
			{
				if(customerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true"))
				{
					return new ResponseEntity<String>("{\"token\":\""+
							jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
									customerUserDetailsService.getUserDetail().getRole()) + "\"}",
							HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval."+"\"}",
							HttpStatus.BAD_REQUEST);
				}
			}
			
		} 
		catch (Exception ex)
		{
			log.error("{}",ex);
		}
		return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}",
				HttpStatus.BAD_REQUEST);
	}

	//get all users
	@Override
	public ResponseEntity<List<UserWrapper>> getAllUsers() 
	{
		try 
		{
			if(jwtFilter.isAdmin())
			{
				return new ResponseEntity<>(userRepository.getAllUser(),HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
			}
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
			if(jwtFilter.isAdmin())
			{
				Optional<User> optional = userRepository.findById(Integer.parseInt(requestMap.get("id")));
				
				if(!optional.isEmpty())
				{
					userRepository.updateStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
					sendMailToAdmin(requestMap.get("status"),optional.get().getEmail(),userRepository.getAllAdmin());
					return CafeUtils.getResponseEntity("User Status Updated Successfully.", HttpStatus.OK);
				}
				else
				{
					return CafeUtils.getResponseEntity("User id doesn't exist.", HttpStatus.OK);
				}
			}
			else
			{
				return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//send mail method
	private void sendMailToAdmin(String status, String user, List<String> allAdmin) 
	{
		allAdmin.remove(jwtFilter.getCurrentUser());
		if(status!=null && status.equalsIgnoreCase("true"))
		{
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Approved","USER:-"+ user + "\n is approved by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
		}
		else
		{
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Disabled","USER:-"+ user + "\n is disabled by \nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
		}
	}

	//check token login
	@Override
	public ResponseEntity<String> checkToken() 
	{
		return CafeUtils.getResponseEntity("true", HttpStatus.OK);
	}

	//change password
	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) 
	{
		try 
		{
			User userObj = userRepository.findByEmail(jwtFilter.getCurrentUser());
			if(!userObj.equals(null))
			{
				if(userObj.getPassword().equals(requestMap.get("oldPassword")))
				{
					userObj.setPassword(requestMap.get("newPassword"));
					userRepository.save(userObj);
					return CafeUtils.getResponseEntity("Password Updated Successfully", HttpStatus.OK);
				}
				return CafeUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
			}
			return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
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
			User user = userRepository.findByEmail(requestMap.get("email"));
			if(!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail()))
			{
				emailUtils.forgotMail(user.getEmail(), "Credentials by Cafe Management System", user.getPassword());
			}
			return CafeUtils.getResponseEntity("Check your mail for credentials.", HttpStatus.OK);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();		
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
