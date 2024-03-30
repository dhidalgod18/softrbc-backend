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
public class Retinoscopia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRetinoscopia;
    private String od;
    private String oi;
    private int idhistoriaclinica;


}
