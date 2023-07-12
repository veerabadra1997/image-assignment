package com.imageinnovate.emptaxcalculation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imageinnovate.emptaxcalculation.model.EmployeeTax;
import com.imageinnovate.emptaxcalculation.model.EmployeeTaxVo;
import com.imageinnovate.emptaxcalculation.repo.EmployeeTaxRepo;
import com.imageinnovate.emptaxcalculation.util.Constants;

@Service
public class EmployeeTaxServiceImpl implements EmployeeTaxService {

	@Autowired
	EmployeeTaxRepo repo;

	@Autowired
	EmpTaxUtilService empUtilServ;

	@Override
	public int employeeAdd(EmployeeTax emp) {

		return repo.save(emp).getId();
	}

	@Override
	public List<EmployeeTaxVo> employeeDetails(String frFrom) {

		List<EmployeeTaxVo> employVoList = new ArrayList<EmployeeTaxVo>();

		String frTo = String.valueOf(Integer.parseInt(frFrom) + 1);
		Optional<List<EmployeeTax>> employee = repo.getEmployeeData(frFrom + Constants.FROM_DATE,
				frTo + Constants.TO_DATE);
		if (employee.isPresent()) {
			List<EmployeeTax> empList = employee.get();
			for (EmployeeTax emp : empList) {
				EmployeeTaxVo employVo = new EmployeeTaxVo();
				employVo.setFirstName(emp.getFirstName());
				employVo.setEmployeeCode(emp.getId());
				employVo.setLastName(emp.getLastName());
				employVo.setYearlSalary(emp.getSal() * 12);
				double taxAmount=empUtilServ.taxOnIncome(emp.getSal() * 12);
				employVo.setTax(taxAmount);
				employVo.setCess(empUtilServ.isCessApplied(emp.getSal() * 12));
				employVoList.add(employVo);
			}

		}

		return employVoList;
	}

}
