package com.cafemanagement.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafemanagement.POJO.Bill;

@RequestMapping("/bill")
public interface BillRest 
{
	// generate report
	@PostMapping("/generateReport")
	public ResponseEntity<String> generateReport(@RequestBody Map<String, Object> requestMap);
	
	//generate bill which generated
	@GetMapping("/getBills")
	public ResponseEntity<List<Bill>> getBills();
	
}
