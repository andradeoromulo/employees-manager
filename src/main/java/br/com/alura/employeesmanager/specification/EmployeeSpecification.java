package br.com.alura.employeesmanager.specification;

import br.com.alura.employeesmanager.model.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeSpecification {

    public static Specification<Employee> name(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }

    public static Specification<Employee> salary(BigDecimal salary) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("salary"), salary);
    }

    public static Specification<Employee> hiringDate(LocalDate hiringDate) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("hiringDate"), hiringDate);
    }
}
