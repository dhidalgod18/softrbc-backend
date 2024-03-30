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
public class RxFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRxFinal;
    private String od;
    private String oi;
    private String avl;
    private String avp;
    private String add;
    private String bif;
    private String uso;
    private String diagnostico;
    private String conducta;
    private String examinador;
    private String color;
    private String control;
    private int idhistoriaclinica;


}
