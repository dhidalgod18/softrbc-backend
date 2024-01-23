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
    int idVisionProxima;
    String ojoDRX;
    String ojoOIRX;
    String OD;
    String OI;
    String distaciaPupilar;
    String examenexterno;

}
