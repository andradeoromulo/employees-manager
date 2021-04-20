package br.com.alura.employeesmanager.model;

import java.math.BigDecimal;

public interface EmployeeProjection {
    Integer getId();
    String getName();
    BigDecimal getSalary();
}
