package com.uan.optica.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioOptometraDTO {
    private Usuario usuario;
    private String numerotarjeta;
    private boolean activo;
}
