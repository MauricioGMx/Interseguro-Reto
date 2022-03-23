package com.interseguro.proyect.soft.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class EmpleadoDTO {
    private int codigo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento;

    @Email(message = "El correo no es valido. ")
    private String correo;
    private String categoria;
    private List<String> conocimientos;

    public EmpleadoDTO() {
        this.conocimientos = new ArrayList<>();
    }
}
