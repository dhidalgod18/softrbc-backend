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
public class Motilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idMotilidad;
    String ducciones;
    String versiones;
    String ppc;
    String ct6m;
    String cms;
    String ojoDominante;
    String manoDominante;


}
