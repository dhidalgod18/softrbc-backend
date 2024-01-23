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
    int idRxFinal;
    String od;
    String oi;
    String avl;
    String avp;
    String add;
    String bif;
    String uso;
    String diagnostico;
    String conducta;
    String examinador;
    String color;
    String control;

}
