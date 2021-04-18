package br.com.alura.employeesmanager.service;

import br.com.alura.employeesmanager.model.Position;
import br.com.alura.employeesmanager.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class PositionService {

    PositionRepository repository;

    public PositionService(PositionRepository positionRepository) {
        this.repository = positionRepository;
    }

    public void showOptions(Scanner scanner) {
        int option = 1;

        while(option != 0) {

            System.out.println("\n--- Positions ---");
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
        System.out.println("--- Inserting new Position ---");
        System.out.println("--- Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        Position position = new Position(description);
        repository.save(position);

        System.out.println("--- Position successfully created ---");
    }

    private void update(Scanner scanner) {
        System.out.println("--- Updating Position ---");
        System.out.println("--- Id: ");
        int id = scanner.nextInt();
        System.out.println("--- Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        Optional<Position> optional = repository.findById(id);

        if (optional.isPresent()) {
            Position position = optional.get();
            position.setDescription(description);
            repository.save(position);
            System.out.println("--- Position successfully updated ---");
        } else {
            optional.orElseThrow(() -> new RuntimeException("Position " + id + " not found"));
        }
    }

    private void readAll() {
        System.out.println("--- Positions ---");

        Iterable<Position> positions = repository.findAll();
        positions.forEach(position -> System.out.println(position));

        System.out.println("-----------------");
    }

    public void delete(Scanner scanner) {
        System.out.println("--- Deleting Position ---");
        System.out.println("--- Id: ");
        int id = scanner.nextInt();

        Optional<Position> optional = repository.findById(id);

        if (optional.isPresent()) {
            repository.deleteById(id);
            System.out.println("--- Position successfully deleted ---");
        } else {
            optional.orElseThrow(() -> new RuntimeException("Position " + id + " not found"));
        }
    }

}
