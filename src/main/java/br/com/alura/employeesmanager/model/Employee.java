package br.com.alura.employeesmanager.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal salary;
    private LocalDate hiringDate;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employees_branches",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "branch_id ") }
    )
    private List<Branch> branches;

    public Employee() {}

    public Employee(String name, BigDecimal salary, LocalDate hiringDate, Position position, List<Branch> branches) {
        this.name = name;
        this.salary = salary;
        this.hiringDate = hiringDate;
        this.position = position;
        this.branches = branches;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {

        return "[" + id + "]" + " " + name + ", " + position.getDescription() + ", $" + salary + ", " + hiringDate;

    }
}
