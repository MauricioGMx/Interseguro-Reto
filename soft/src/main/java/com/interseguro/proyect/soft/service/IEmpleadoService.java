package com.interseguro.proyect.soft.service;

import com.interseguro.proyect.soft.DTO.EmpleadoDTO;
import com.interseguro.proyect.soft.model.Empleado;

import java.util.List;

public interface IEmpleadoService {
    Empleado saveEmpleado(EmpleadoDTO empleado);
    List<String> getEmpleados();
    boolean validarCategorias(String categoria);
    boolean validarFechaNacimiento(String fecha);
}
