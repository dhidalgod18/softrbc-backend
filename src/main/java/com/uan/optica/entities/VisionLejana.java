package com.uan.optica.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "visionlejana")

public class VisionLejana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idvisionlejana;
    private String ojoDRX;
    private String ojoIRX;
    private String OD;
    private String OI;
    private String distanciapupilar;
    private String examenexterno;
    private int idhistoriaclinica;
    String fecha;


}
