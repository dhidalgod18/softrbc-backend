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
public class VisionProxima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVisionProxima;
    private String ojoDRX;
    private String ojoOIRX;
    private String OD;
    private String OI;
    private int idhistoriaclinica;



}
