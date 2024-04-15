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
    private int idmotilidad;
    private String ducciones;
    private String versiones;
    private String ppc;
    private String ct6m;
    private String cms;
    private String ojodominante;
    private String manodominante;
    private int idhistoriaclinica;
    String fecha;



}
