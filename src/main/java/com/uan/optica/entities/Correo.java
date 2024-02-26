package com.uan.optica.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Correo {
    private String destinatario;
    private String asunto;
    private String mensaje;
    private String correousuario; // Correo del usuario
    private String contraseñagenerada; // Contraseña generada por el sistema


}
