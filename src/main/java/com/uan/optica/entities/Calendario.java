package com.uan.optica.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcalendario;
    private String diasatencion;
    private int duracioncita;
    private int idoptometra;
}
