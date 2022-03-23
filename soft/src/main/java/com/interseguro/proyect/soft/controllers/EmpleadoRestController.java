package com.interseguro.proyect.soft.controllers;

import com.interseguro.proyect.soft.DTO.EmpleadoDTO;
import com.interseguro.proyect.soft.model.Empleado;
import com.interseguro.proyect.soft.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/talentfestapi")
public class EmpleadoRestController {

    @Autowired
    private IEmpleadoService empleadoService;

    @PostMapping("/empleados")
    public ResponseEntity<?> saveEmpleado(@Valid @RequestBody EmpleadoDTO empleado, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        List<String> errors = new ArrayList<>();

        //Validar Correo:
        if(result.hasErrors()){
           String errorEmail = result.getFieldError("correo").getDefaultMessage();
           errors.add(errorEmail);
        }

        //Validar Categoria:
        if(!empleadoService.validarCategorias(empleado.getCategoria())) {
            errors.add("La categoria ingresasa no es valida. ");
        }

        //Validar Comepetencias:
        if(empleado.getConocimientos().size() != 0){
            if(empleado.getConocimientos().size() > 10){
                errors.add("El tamaño maximo de competencias es 10. ");
            }
        }

        //Validar Fecha de nacimiento:
        if(!empleadoService.validarFechaNacimiento(empleado.getFechaNacimiento())){
            errors.add("La fecha de nacimiento no es valida. ");
        }

        //Verificar si hay errores:
        if(errors.size() > 0){
            response.put("errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Empleado empleadoSaved;
        try{
            empleadoSaved = empleadoService.saveEmpleado(empleado);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el guardado del empleado. ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        Random claseRandom = new Random();
        response.put("codigoRespuesta", claseRandom.nextInt(10000));
        response.put("mensajeRespuesta", "El empleado " + ((int)empleadoSaved.getCodigo()) + " ha sido registrado exitosamente.");

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    @GetMapping("/empleados")
    public ResponseEntity<?> getEmpleados(){
        Map<String, Object> response = new HashMap<>();
        List<String> empleados;

        try{
            empleados = empleadoService.getEmpleados();
        }catch (DataAccessException e){
            response.put("mensaje", "Error al consultar la lista de empleados. ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("empleados", empleados);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
