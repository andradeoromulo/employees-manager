package br.com.alura.employeesmanager.service;

import br.com.alura.employeesmanager.model.Branch;
import br.com.alura.employeesmanager.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class BranchService {

    private final BranchRepository repository;

    public BranchService(BranchRepository branchRepository) {
        this.repository = branchRepository;
    }

    public void showOptions(Scanner scanner) {
        int option = 1;

        while(option != 0) {

            System.out.println("\n--- Branches ---");
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
                    readAll();
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
        System.out.println("--- Inserting new Branch ---");
        System.out.println("--- Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("--- Address: ");
        String address = scanner.nextLine();

        Branch branch = new Branch(description, address);
        repository.save(branch);

        System.out.println("--- Position successfully created ---");
    }

    private void update(Scanner scanner) {
        System.out.println("--- Updating Branch ---");
        System.out.println("--- Id: ");
        int id = scanner.nextInt();
        System.out.println("--- Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("--- Address: ");
        String address = scanner.nextLine();

        Optional<Branch> optional = repository.findById(id);

        if (optional.isPresent()) {
            Branch branch = optional.get();
            branch.setDescription(description);
            branch.setAddress(address);
            repository.save(branch);
            System.out.println("--- Branch successfully updated ---");
        } else {
            optional.orElseThrow(() -> new RuntimeException("Branch " + id + " not found"));
        }
    }

    private void readAll() {
        System.out.println("--- Branches ---");

        Iterable<Branch> branches = repository.findAll();
        branches.forEach(branch -> System.out.println(branch));

        System.out.println("-----------------");
    }

    private void delete(Scanner scanner) {
        System.out.println("--- Deleting Branch ---");
        System.out.println("--- Id: ");
        int id = scanner.nextInt();

        Optional<Branch> branch = repository.findById(id);

        if (branch.isPresent()) {
            repository.deleteById(id);
            System.out.println("--- Branch successfully deleted ---");
        } else {
            branch.orElseThrow(() -> new RuntimeException("Branch " + id + " not found"));
        }
    }

}
