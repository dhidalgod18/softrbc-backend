package com.uan.optica.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Optometra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idoptometra;

    private String numerotarjeta;

    private  int idusuario;

    private boolean activo;

}