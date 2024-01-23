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
public class Antecedentes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idAntecedentes;
    String familiares;
    String oculares;
    String generales;
}
