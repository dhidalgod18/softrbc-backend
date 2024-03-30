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
public class VisionLejana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVisionLejana;
    private String ojoDRX;
    private String ojoIRX;
    private String OD;
    private String OI;
    private String distanciapupilar;
    private String examenExterno;
    private int idhistoriaclinica;


}
