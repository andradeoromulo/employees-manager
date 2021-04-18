package br.com.alura.employeesmanager.repository;

import br.com.alura.employeesmanager.model.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer> {
}
