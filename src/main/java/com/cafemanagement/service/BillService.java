package com.cafemanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafemanagement.POJO.Bill;

public interface BillService 
{
	//generate report
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap);
	
	//get bills which are generated
	public ResponseEntity<List<Bill>> getBills();
}
