package com.uan.optica.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioPacienteDTO {
    private Usuario usuario;
    private Paciente paciente;
}
