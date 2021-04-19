package br.com.alura.employeesmanager.repository;

import br.com.alura.employeesmanager.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
    // Derived Query
    List<Employee> findByName(String name);

    // JPQL Query
    @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.salary >= :salary AND e.hiringDate = :hiringDate")
    List<Employee> findByNameSalaryGreaterHiringDate(String name, BigDecimal salary, LocalDate hiringDate);

    // Native Query
    @Query(value = "SELECT * FROM employees e WHERE e.hiring_date >= :hiringDate", nativeQuery = true)
    List<Employee> findByHiringDateGreater(LocalDate hiringDate);
}
