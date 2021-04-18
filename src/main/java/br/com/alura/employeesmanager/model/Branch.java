package br.com.alura.employeesmanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String address;

    @ManyToMany(mappedBy = "branches", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Branch() {}

    public Branch(String description, String address) {
        this.description = description;
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "[" + id + "]" + " " + description + ", " + address;
    }
}
