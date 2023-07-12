package com.imageinnovate.emptaxcalculation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imageinnovate.emptaxcalculation.model.EmployeeTax;

@Repository
public interface EmployeeTaxRepo extends JpaRepository<EmployeeTax, Integer> {

	@Query(value = "select * from employee where to_char(doj,'yyyy-mm')  between :fyFrom and :fyTo", nativeQuery = true)
	public Optional<List<EmployeeTax>> getEmployeeData(@Param("fyFrom") String fyFrom, @Param("fyTo") String fyTo);
}
