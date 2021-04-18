package br.com.alura.employeesmanager.repository;

import br.com.alura.employeesmanager.model.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Integer> {
}
