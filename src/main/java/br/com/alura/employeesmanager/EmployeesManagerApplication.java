package br.com.alura.employeesmanager;

import br.com.alura.employeesmanager.model.Position;
import br.com.alura.employeesmanager.repository.PositionRepository;
import br.com.alura.employeesmanager.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EmployeesManagerApplication implements CommandLineRunner {

    PositionService positionService;

    public EmployeesManagerApplication(PositionService positionService) {
        this.positionService = positionService;
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
            System.out.println("--- 0 Exit");
            System.out.println("\n--- Option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    positionService.showOptions(scanner);
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
