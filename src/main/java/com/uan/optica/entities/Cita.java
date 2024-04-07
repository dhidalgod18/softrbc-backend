package com.uan.optica.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcita;
    private String fecha;
    private String nombre;
    private Long telefono;
    private int idpaciente;
    private String codigo;
    private String hora;
    private boolean estado;

}
