package com.uan.optica.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaciente;
    private String ocupacion;
    private LocalDate fechaNacimiento;

    private GeneroEnum generoEnum;
    private String nombreAcompañante;

    public enum GeneroEnum {
        Masculino,
        Femenino,

    }

}
