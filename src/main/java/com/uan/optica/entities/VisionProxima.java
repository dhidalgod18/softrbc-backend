package com.uan.optica.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "visionproxima")

public class VisionProxima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idvisionproxima;
    private String ojodrx;
    private String ojooirx;
    private String od;
    private String oi;
    private int idhistoriaclinica;
    String fecha;



}
