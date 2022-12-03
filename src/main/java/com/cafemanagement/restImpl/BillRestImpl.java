package com.cafemanagement.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafemanagement.POJO.Bill;
import com.cafemanagement.constents.CafeConstants;
import com.cafemanagement.rest.BillRest;
import com.cafemanagement.service.BillService;
import com.cafemanagement.utils.CafeUtils;

@RestController
public class BillRestImpl implements BillRest
{
	@Autowired
	private BillService billService;

	//generate report
	@Override
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap) 
	{
		try 
		{
			return this.billService.generateReport(requestMap);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//get bills which are generated
	@Override
	public ResponseEntity<List<Bill>> getBills() 
	{
		try 
		{
			return billService.getBills();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
