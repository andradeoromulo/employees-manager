package br.com.alura.employeesmanager;

import br.com.alura.employeesmanager.model.Position;
import br.com.alura.employeesmanager.repository.EmployeeRepository;
import br.com.alura.employeesmanager.repository.PositionRepository;
import br.com.alura.employeesmanager.service.BranchService;
import br.com.alura.employeesmanager.service.EmployeeService;
import br.com.alura.employeesmanager.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EmployeesManagerApplication implements CommandLineRunner {

    final private PositionService positionService;
    final private BranchService branchService;
    final private EmployeeService employeeService;

    public EmployeesManagerApplication(PositionService positionService, BranchService branchService, EmployeeService employeeService) {
        this.positionService = positionService;
        this.branchService = branchService;
        this.employeeService = employeeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeesManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        int option = 1;
        Scanner scanner = new Scanner(System.in);

        while(option != 0) {

            System.out.println("\n--- Employees Manager ---");
            System.out.println("--- 1 Positions");
            System.out.println("--- 2 Branches");
            System.out.println("--- 3 Employees");
            System.out.println("--- 0 Exit");
            System.out.println("\n--- Option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    positionService.showOptions(scanner);
                    break;
                case 2:
                    branchService.showOptions(scanner);
                    break;
                case 3:
                    employeeService.showOptions(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nInvalid option...");
            }

        }

        System.out.println("\n--- Finishing Employees Manager ---");

    }
}
