package com.interseguro.proyect.soft.service;

import com.interseguro.proyect.soft.DTO.EmpleadoDTO;
import com.interseguro.proyect.soft.exception.v2.requestExceptions.NotFoundRException;
import com.interseguro.proyect.soft.exception.v3.requestExceptions.BusinessException;
import com.interseguro.proyect.soft.model.Empleado;
import com.interseguro.proyect.soft.repository.EmpladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpClientErrorException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{

    @Autowired
    private EmpladoRepository empladoRepository;

    @Override
    public Empleado saveEmpleado(EmpleadoDTO empleado) {
        Empleado empleadoSave = new Empleado();

        Random r = new Random();
        Double min = 0.0;
        Double max = 0.0;

        switch (empleado.getCategoria()){
            case "A":
                min =  8000.0;
                max = 15000.0;
                break;

            case "B":
                min = 5000.0;
                max = 7999.0;
                break;

            case "C":
                min = 3000.0;
                max = 4999.0;
                break;

            case "D":
                min = 2000.0;
                max = 2999.0;
                break;
        }


        empleadoSave.setCodigo(empleado.getCodigo());
        empleadoSave.setNombre(empleado.getNombre());
        empleadoSave.setApellido_paterno(empleado.getApellidoPaterno());
        empleadoSave.setApellido_materno(empleado.getApellidoMaterno());
        empleadoSave.setCorreo(empleado.getCorreo());
        empleadoSave.setCategoria(empleado.getCategoria());
        empleadoSave.setSalario(min + (max - min) * r.nextDouble());
        empleadoSave.setCompetencias(empleado.getConocimientos());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDate.parse(empleado.getFechaNacimiento(), formatter).atStartOfDay();
        empleadoSave.setFecha_nacimiento(dateTime);
        return empladoRepository.save(empleadoSave);
    }

    @Override
    public List<String> getEmpleados() {
        List<Empleado> empleados = empladoRepository.findAll();
        List<String> response = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(Empleado empleado: empleados){
            response.add(((int)empleado.getCodigo()) + " - " + empleado.getNombre() + " " + empleado.getApellido_paterno() + " " + empleado.getApellido_materno() + " - " + empleado.getFecha_nacimiento().format(formatter) + " - " + "(" + empleado.getCategoria() + ")");
        }

        return  response;
    }


    public boolean validarCategorias(String categoria) {
        List<String> categorias = List.of("A", "B", "C", "D");
        return categorias.contains(categoria);
    }

    @Override
    public boolean validarFechaNacimiento(String fecha) {
        boolean esFechaValida = true;

        if(fecha.chars().filter(rec -> rec == '-').count() != 2){
            esFechaValida = false;
            return esFechaValida;
        }

        int anio = Integer.parseInt(fecha.split("-")[0]);
        int mes = Integer.parseInt(fecha.split("-")[1]);
        int dia = Integer.parseInt(fecha.split("-")[2]);

        try {
            LocalDate.of(anio,mes,dia);
        }catch (DateTimeException e){
            esFechaValida = false;
        }

        return esFechaValida;
    }

}
