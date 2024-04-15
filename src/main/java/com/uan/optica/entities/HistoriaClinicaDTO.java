package com.uan.optica.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoriaClinicaDTO {
    List<Anamnesis> anamnesis;
    List<Antecedentes> antecedentes;
    List<RxUso> rxUso;
    List<VisionLejana> visionLejana;
    List<VisionProxima> visionProxima;
    List<Motilidad> motilidad;
    List<Oftalmoscopia> oftalmoscopia;
    List<Queratometria> queratometria;
    List<Retinoscopia> retinoscopia;
    List<RxFinal> rxFinal;

}
