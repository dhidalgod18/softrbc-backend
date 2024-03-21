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

public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idauditoria;
    LocalDate fecha;
    String accion;
    int idusuario;
    String informacion;

}
