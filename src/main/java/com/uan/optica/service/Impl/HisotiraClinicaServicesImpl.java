package com.uan.optica.service.Impl;

import com.uan.optica.entities.*;
import com.uan.optica.repository.HistoriaClinicaRepository;
import com.uan.optica.service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HisotiraClinicaServicesImpl  implements HistoriaClinicaService {
    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public Historiaclinica crearHistoria(Historiaclinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public HistoriaClinicaDTO listaHistoria(Long cedula) {

        Usuario usuario = historiaClinicaRepository.findByUsuarioIdp(cedula);
        Paciente pacienteBD = historiaClinicaRepository.findPacienteId(usuario.getIdusuario());


        List<Anamnesis> anamnesis = historiaClinicaRepository.findAnamnesis(pacienteBD.getIdhistoriaclinica());
        List<Antecedentes> antecedentes =  historiaClinicaRepository.findAntecedentes(pacienteBD.getIdhistoriaclinica());
        List<RxUso> rxUso = historiaClinicaRepository.findRxUso(pacienteBD.getIdhistoriaclinica());
        List<VisionLejana> visionLejana =  historiaClinicaRepository.findVisionLejana(pacienteBD.getIdhistoriaclinica());
        List<VisionProxima> visionProxima =  historiaClinicaRepository.findVisionProxima(pacienteBD.getIdhistoriaclinica());
        List<Motilidad> motilidad =  historiaClinicaRepository.findMotilidad(pacienteBD.getIdhistoriaclinica());
        List<Oftalmoscopia> oftalmoscopia =  historiaClinicaRepository.findOftalmoscopia(pacienteBD.getIdhistoriaclinica());
        List<Queratometria> queratometria =  historiaClinicaRepository.findQueratometria(pacienteBD.getIdhistoriaclinica());
        List<Retinoscopia> retinoscopia =  historiaClinicaRepository.findRetinoscopia(pacienteBD.getIdhistoriaclinica());
        List<RxFinal> rxFinal =  historiaClinicaRepository.findRxFinal(pacienteBD.getIdhistoriaclinica());


        HistoriaClinicaDTO historiaClinicaDTO = new HistoriaClinicaDTO(anamnesis, antecedentes, rxUso, visionLejana,
                visionProxima, motilidad, oftalmoscopia, queratometria, retinoscopia, rxFinal);

        return historiaClinicaDTO;
    }



}
