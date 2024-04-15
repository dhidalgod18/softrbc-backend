package com.uan.optica.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoriaClinitaResponseDTO {
    private List<HistoriaClinicaDTO> historiaClinicaDTOS;
    private Paciente pacienteDTO;
}
