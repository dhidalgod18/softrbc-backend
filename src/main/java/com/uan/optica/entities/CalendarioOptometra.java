package com.uan.optica.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalendarioOptometra {
    private String diaCalendario;
    private boolean estado;
}
