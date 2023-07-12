package com.imageinnovate.emptaxcalculation.service;

public class EmpTaxUtilServiceImpl implements EmpTaxUtilService {

	@Override
	public double isCessApplied(double income) {
		double cess = 0.0;

		if (income > 2500000) {
			cess = income*0.02;
		}
		return cess;
	}

	@Override
	public double taxOnIncome(double income) {
		double tax = 0.0;
		if (income <= 250000) {
            tax = 0.0;
        } else if (income > 250000 && income <= 500000) {
            tax = (income - 250000) * 0.05;
        } else if (income > 500000 && income <= 100000) {
            tax = 12500 + (income - 500000) * 0.1;
        } else if (income > 1000000) {
        	 tax = (income - 1000000) * 0.2 + 62500;
        }
		return tax;
	}

}
