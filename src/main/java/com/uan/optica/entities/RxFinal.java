package com.uan.optica.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "rxfinal")

public class RxFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrxfinal;
    private String od;
    private String oi;
    private String avl;
    private String avp;
    private String addicion;
    private String bif;
    private String uso;
    private String diagnostico;
    private String conducta;
    private String examinador;
    private String color;
    private String control;
    private int idhistoriaclinica;
    private String  observaciones;
    String fecha;


}
