package com.cafemanagement.jwt;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafemanagement.dao.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService
{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	com.cafemanagement.POJO.User userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		log.info("Inside loadUserByUsername {}",username);
		userDetail=userRepository.findByEmailId(username);
		if(!Objects.isNull(userDetail))
		{
			return new User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
		}
		else
		{
			throw new UsernameNotFoundException("User not found.");
		}
	}
	
	//get user details
	public com.cafemanagement.POJO.User getUserDetail()
	{
		return userDetail;
	}
	
}
