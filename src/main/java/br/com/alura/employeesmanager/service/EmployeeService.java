package br.com.alura.employeesmanager.service;

import br.com.alura.employeesmanager.model.Branch;
import br.com.alura.employeesmanager.model.Employee;
import br.com.alura.employeesmanager.model.Position;
import br.com.alura.employeesmanager.repository.BranchRepository;
import br.com.alura.employeesmanager.repository.EmployeeRepository;
import br.com.alura.employeesmanager.repository.PositionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final BranchRepository branchRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.branchRepository = branchRepository;
    }

    public void showOptions(Scanner scanner) {
        int option = 1;

        while(option != 0) {

            System.out.println("\n--- Employees ---");
            System.out.println("--- 1 Create");
            System.out.println("--- 2 Update");
            System.out.println("--- 3 Read All");
            System.out.println("--- 4 Delete");
            System.out.println("--- 0 Back");
            System.out.println("\n--- Option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    save(scanner);
                    break;
                case 2:
                    update(scanner);
                    break;
                case 3:
                    readAll(scanner);
                    break;
                case 4:
                    delete(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nInvalid option...");
            }

        }
    }

    private void save(Scanner scanner) {
        System.out.println("--- Inserting new Employee ---");
        System.out.println("--- Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.println("--- Salary: ");
        String salaryInput = scanner.nextLine();
        BigDecimal salary = new BigDecimal(salaryInput);

        System.out.println("--- Hiring Date (dd/mm/yyyy): ");
        String hiringDateInput = scanner.nextLine();
        LocalDate hiringDate = LocalDate.parse(hiringDateInput, formatter);

        System.out.println("--- Position Id: ");
        int positionId = scanner.nextInt();
        Position position = null;
        Optional<Position> optional = positionRepository.findById(positionId);
        if(optional.isPresent())
            position = optional.get();
        else
            optional.orElseThrow(() -> new RuntimeException("Branch " + positionId + " not found"));

        System.out.println("--- Branches: ");
        List<Branch> branches = collectBranches(scanner);

        Employee employee = new Employee(name, salary, hiringDate, position, branches);
        employeeRepository.save(employee);

        System.out.println("--- Employee successfully created ---");
    }

    private void update(Scanner scanner) {
        System.out.println("--- Updating Employee ---");
        System.out.println("--- Id: ");
        int id = scanner.nextInt();

        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent())
            employee = optional.get();
        else
            optional.orElseThrow(() -> new RuntimeException("Employee " + id + "not found"));

        System.out.println("--- Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.println("--- Salary: ");
        String salaryInput = scanner.nextLine();
        BigDecimal salary = new BigDecimal(salaryInput);

        System.out.println("--- Hiring Date (dd/mm/yyyy): ");
        String hiringDateInput = scanner.nextLine();
        LocalDate hiringDate = LocalDate.parse(hiringDateInput, formatter);

        System.out.println("--- Position ID: ");
        int positionId = scanner.nextInt();
        Position position = null;
        Optional<Position> positionOptional = positionRepository.findById(positionId);
        if(positionOptional.isPresent())
            position = positionOptional.get();
        else
            positionOptional.orElseThrow(() -> new RuntimeException("Branch " + positionId + " not found"));

        employee.setName(name);
        employee.setSalary(salary);
        employee.setHiringDate(hiringDate);
        employee.setPosition(position);
        employeeRepository.save(employee);

        System.out.println("--- Employee successfully updated ---");
    }

    private void readAll(Scanner scanner) {
        System.out.println("--- Employees ---");
        System.out.println("--- Page: ");
        int page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "name"));
        Iterable<Employee> employees = employeeRepository.findAll(pageable);

        System.out.println("-----------------");
        System.out.println(employees);
        employees.forEach(employee -> System.out.println(employee));

        System.out.println("-----------------");
    }

    private void delete(Scanner scanner) {
        System.out.println("--- Deleting Employee ---");
        System.out.println("--- Id: ");
        int id = scanner.nextInt();

        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            employeeRepository.deleteById(id);
            System.out.println("--- Employee successfully deleted ---");
        } else {
            optional.orElseThrow(() -> new RuntimeException("Employee " + id + " not found"));
        }
    }

    private List<Branch> collectBranches(Scanner scanner) {
        List<Branch> branches = new ArrayList<>();
        int branchId = 1;

        while(branchId != 0) {
            System.out.println("--- Add Branch (0 to quit): ");
            branchId = scanner.nextInt();

            if(branchId != 0) {
                Optional<Branch> optional = branchRepository.findById(branchId);
                if(optional.isPresent())
                    branches.add(optional.get());
            }
        }

        System.out.println(branches);
        return branches;
    }

}
