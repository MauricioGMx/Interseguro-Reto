package com.interseguro.proyect.soft.repository;

import com.interseguro.proyect.soft.model.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpladoRepository extends MongoRepository<Empleado, String>{
}
