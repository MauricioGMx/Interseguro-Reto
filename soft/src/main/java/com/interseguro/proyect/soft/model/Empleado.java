package com.interseguro.proyect.soft.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Document(collection = "empleado")
public class Empleado implements Serializable {

    @Id
    private String id;

    private double codigo;

    private String nombre;

    private String apellido_paterno;

    private String apellido_materno;

    private LocalDateTime fecha_nacimiento;

    private String correo;

    private String categoria;

    private double salario;

    private List<String> competencias;

}
