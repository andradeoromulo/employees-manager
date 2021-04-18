package br.com.alura.employeesmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    public Position() {}

    public Position(String description) {
        this.description = description;
    }
    public Position(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + id + "]" + " " + description;
    }
}
