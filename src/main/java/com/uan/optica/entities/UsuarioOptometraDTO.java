package com.uan.optica.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioOptometraDTO {
    private Usuario usuario;
    private int idoptometra;
    private String numerotarjeta;
    private boolean activo;
}
