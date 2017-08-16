package com.cg.billing.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.billing.customresponse.CustomResponse;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

@ControllerAdvice
public class BillingServiceExceptionAspect {

	@ExceptionHandler(CustomerDetailsNotFoundException.class)
	public ResponseEntity<CustomResponse> handleCustomerDetailsNotFoundException(Exception e) {
		CustomResponse response = new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(BillDetailsNotFoundException.class)
	public ResponseEntity<CustomResponse> handleBillDetailsNotFoundException(Exception e) {
		CustomResponse response = new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(BillingServicesDownException.class)
	public ResponseEntity<CustomResponse> handleBillingServicesDownException(Exception e) {
		CustomResponse response = new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(InvalidBillMonthException.class)
	public ResponseEntity<CustomResponse> handleInvalidBillMonthException(Exception e) {
		CustomResponse response = new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(PlanDetailsNotFoundException.class)
	public ResponseEntity<CustomResponse> handlePlanDetailsNotFoundException(Exception e) {
		CustomResponse response = new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
			
	}
	
	@ExceptionHandler(PostpaidAccountNotFoundException.class)
	public ResponseEntity<CustomResponse> handlePostpaidAccountNotFoundException(Exception e) {
		CustomResponse response = new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
}
}