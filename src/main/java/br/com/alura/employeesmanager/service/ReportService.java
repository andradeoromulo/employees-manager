package br.com.alura.employeesmanager.service;

import br.com.alura.employeesmanager.model.Employee;
import br.com.alura.employeesmanager.model.EmployeeProjection;
import br.com.alura.employeesmanager.repository.EmployeeRepository;
import br.com.alura.employeesmanager.specification.EmployeeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private final EmployeeRepository employeeRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void showOptions(Scanner scanner) {
        int option = 1;

        while(option != 0) {

            System.out.println("\n--- Report ---");
            System.out.println("--- 1 Find employee by name");
            System.out.println("--- 2 Find employee by name, salary (gt) and hiring date");
            System.out.println("--- 3 Find employee by hiring date (gt)");
            System.out.println("--- 4 Find employees' salaries");
            System.out.println("--- 5 Find employees dynamically");
            System.out.println("--- 0 Back");
            System.out.println("\n--- Option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    findEmployeeByName(scanner);
                    break;
                case 2:
                    findByNameSalaryGreaterHiringDate(scanner);
                    break;
                case 3:
                    findByHiringDateGreater(scanner);
                    break;
                case 4:
                    findEmployeeSalary();
                    break;
                case 5:
                    findEmployeesDynamically(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nInvalid option...");
            }

        }
    }

    private void findEmployeeByName(Scanner scanner) {
        System.out.println("--- Find employee by name ---");
        System.out.println("--- Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        List<Employee> employees = employeeRepository.findByName(name);
        employees.forEach(System.out::println);

        System.out.println("-----------------");
    }

    private void findByNameSalaryGreaterHiringDate(Scanner scanner) {
        System.out.println("--- Find employee by name, salary (gt) and hiring date ---");
        System.out.println("--- Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.println("--- Salary (gt): ");
        String salaryInput = scanner.nextLine();
        BigDecimal salary = new BigDecimal(salaryInput);

        System.out.println("--- Hiring Date (dd/mm/yyyy): ");
        String hiringDateInput = scanner.nextLine();
        LocalDate hiringDate = LocalDate.parse(hiringDateInput, formatter);

        List<Employee> employees = employeeRepository.findByNameSalaryGreaterHiringDate(name, salary, hiringDate);
        employees.forEach(System.out::println);

        System.out.println("-----------------");
    }

    private void findByHiringDateGreater(Scanner scanner) {
        System.out.println("--- Find employee by hiring date (gt) ---");
        System.out.println("--- Hiring Date (dd/mm/yyyy): ");
        scanner.nextLine();
        String hiringDateInput = scanner.nextLine();
        LocalDate hiringDate = LocalDate.parse(hiringDateInput, formatter);

        List<Employee> employees = employeeRepository.findByHiringDateGreater(hiringDate);
        employees.forEach(System.out::println);

        System.out.println("-----------------");
    }

    private void findEmployeeSalary() {
        System.out.println("--- Employees' salaries ---");

        List<EmployeeProjection> employees = employeeRepository.findEmployeeSalary();
        employees.forEach(e -> {
            System.out.println("[" + e.getId() + "]" + " " + e.getName() + ", $" + e.getSalary() );
        });

        System.out.println("-----------------");
    }

    private void findEmployeesDynamically(Scanner scanner) {
        System.out.println("--- Find employees dynamically (all parameters are optional) ---");
        System.out.println("--- Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        if(name.equalsIgnoreCase("NULL"))
            name = null;

        System.out.println("--- Salary (gt): ");
        String salaryInput = scanner.nextLine();
        BigDecimal salary;
        if(salaryInput.equalsIgnoreCase("NULL"))
            salary = null;
        else
            salary = new BigDecimal(salaryInput);

        System.out.println("--- Hiring Date (gt): ");
        String hiringDateInput = scanner.nextLine();
        LocalDate hiringDate;
        if(hiringDateInput.equalsIgnoreCase("NULL"))
            hiringDate = null;
        else
            hiringDate = LocalDate.parse(hiringDateInput, formatter);

        List<Employee> employees = employeeRepository.findAll(Specification.where(
                EmployeeSpecification.name(name))
                .and(EmployeeSpecification.salary(salary))
                .and(EmployeeSpecification.hiringDate(hiringDate))
        );
        employees.forEach(System.out::println);

        System.out.println("-----------------");
    }

}
