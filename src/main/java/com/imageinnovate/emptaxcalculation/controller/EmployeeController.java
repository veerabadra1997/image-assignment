package com.imageinnovate.emptaxcalculation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imageinnovate.emptaxcalculation.model.EmployeeTax;
import com.imageinnovate.emptaxcalculation.model.EmployeeTaxVo;
import com.imageinnovate.emptaxcalculation.service.EmployeeTaxService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("api")
public class EmployeeController {

	@Autowired
	EmployeeTaxService empServ;

	@RequestMapping(method = RequestMethod.POST, path = "/emp")
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeTax emp) {
		int empId = empServ.employeeAdd(emp);
		return ResponseEntity.ok("Employee Created with ID :" + empId);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/emp")
	public ResponseEntity<List<EmployeeTaxVo>> saveEmployee(@RequestParam("financialYear") @Pattern(regexp="\\d{4}") String financialYear) {
		List<EmployeeTaxVo> employeeVoList = empServ.employeeDetails(financialYear);
		return ResponseEntity.ok(employeeVoList);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
	    Map<String, Object> responseBody = new HashMap<>();
	    responseBody.put("error", "Validation failed");
	    responseBody.put("details", ex.getBindingResult().getAllErrors());
	    return ResponseEntity.badRequest().body(responseBody);
	}


}
