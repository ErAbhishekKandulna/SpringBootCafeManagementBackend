package com.cafemanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cafemanagement.POJO.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> 
{
	//get all bill by ADMIN
	List<Bill> getAllBills();
	
	//get bill by user name
	List<Bill> getBillByUserName(@Param("username")String username);
}
