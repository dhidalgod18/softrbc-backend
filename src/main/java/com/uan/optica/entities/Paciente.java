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
    private int idpaciente;
    private String ocupacion;
    private String fechanacimiento;

    private String genero;

    private int idusuario;
    private boolean aceptarterminos;

   private Integer idhistoriaclinica;

    private String nombreacompanante;


}
