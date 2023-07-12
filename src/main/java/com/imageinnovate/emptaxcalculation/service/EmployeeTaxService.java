package com.imageinnovate.emptaxcalculation.service;

import java.util.List;

import com.imageinnovate.emptaxcalculation.model.EmployeeTax;
import com.imageinnovate.emptaxcalculation.model.EmployeeTaxVo;

public interface EmployeeTaxService {
	
	 public int employeeAdd(EmployeeTax emp);
	 
	 public List<EmployeeTaxVo> employeeDetails(String financialYear);

}
