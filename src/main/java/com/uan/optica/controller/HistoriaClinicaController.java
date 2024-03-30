package com.uan.optica.controller;

import com.uan.optica.entities.*;
import com.uan.optica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/HistoriaClinica")
public class HistoriaClinicaController {

    @Autowired
    HistoriaClinicaService historiaClinicaService;
    @Autowired
    AnamnesisService anamnesisService;
    @Autowired
    AntecedentesService antecedentesService;
    @Autowired
    RxUsoService rxUsoService;
    @Autowired
    VisionLejanaService visionLejanaService;
    @Autowired
    VisionProximaService visionProximaService;
    @Autowired
    MotilidadService motilidadService;
    @Autowired
    QueratometriaService queratometriaService;
    @Autowired
    OftalmoscopiaService oftalmoscopiaService;
    @Autowired
    RxFinalService rxFinalService;
    @Autowired
    RetinoscopiaService retinoscopiaService;
    @Autowired
    PacienteService pacienteService;

    @PostMapping("/nueva")
    public ResponseEntity<?> guardarHistoriaClinica(@RequestBody Map<String, Object> requestBody) {
        try {
            HistoriaClinica historiaClinica1 = new HistoriaClinica();
            HistoriaClinica historiaClinica = historiaClinicaService.crearHistoria(historiaClinica1);

            // Extraer los datos del usuario del cuerpo de la solicitud
            Map<String, Object> datosMap = (Map<String, Object>) requestBody.get("Anamnesis");
            // Crear un objeto Anamnesis a partir de los datos recibidos
            Anamnesis anamnesis = new Anamnesis();
            anamnesis.setAnamnesis((String) datosMap.get("anamnesis"));
            anamnesis.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());
            // Crear el usuario
            anamnesisService.agregarAnamnesis(anamnesis);
            // Extraer el objeto Antecedentes del cuerpo de la solicitud
            Map<String, Object> antecedentesMap = (Map<String, Object>) requestBody.get("Antecedentes");
            Antecedentes antecedentes = new Antecedentes();
            antecedentes.setFamiliares((String) antecedentesMap.get("antecedentesFamiliares"));
            antecedentes.setOculares((String) antecedentesMap.get("antecedentesOculares"));
            antecedentes.setGenerales((String) antecedentesMap.get("antecedentesGenerales"));
            antecedentes.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());
            // Crear antecedentes
            antecedentesService.agregarAntecedentes(antecedentes);
            // Extraer el objeto RxEnUso del cuerpo de la solicitud
            Map<String, Object> RxusoMap = (Map<String, Object>) requestBody.get("RxUso");

            RxUso rxUso = new RxUso();
            rxUso.setOd((String) RxusoMap.get("od"));
            rxUso.setOi((String) RxusoMap.get("oi"));
            rxUso.setAdd((String) RxusoMap.get("add"));
            rxUso.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());

            // Crear rxUso
            rxUsoService.agregarRxEnUso(rxUso);
            // Extraer el objeto RxEnUso del cuerpo de la solicitud
            Map<String, Object> visionLejanaMap = (Map<String, Object>) requestBody.get("VisionLejana");

            VisionLejana visionLejana = new VisionLejana();
            visionLejana.setOjoDRX((String) visionLejanaMap.get("odConRx"));
            visionLejana.setOjoIRX((String) visionLejanaMap.get("oiConRx"));
            visionLejana.setOD((String) visionLejanaMap.get("od"));
            visionLejana.setOI((String) visionLejanaMap.get("oi"));
            visionLejana.setDistanciapupilar((String) visionLejanaMap.get("distanciaPupilar"));
            visionLejana.setExamenExterno((String) visionLejanaMap.get("externo"));
            visionLejana.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());
            // Crear visionlejana
            visionLejanaService.agregarVisionLejana(visionLejana);

            // Extraer el objeto visionProxima del cuerpo de la solicitud
            Map<String, Object> visionProximaMap = (Map<String, Object>) requestBody.get("visionProxima");
            VisionProxima visionProxima = new VisionProxima();
            visionProxima.setOjoDRX((String) visionProximaMap.get("odConRx"));
            visionProxima.setOjoOIRX((String) visionProximaMap.get("oiConRx"));
            visionProxima.setOD((String) visionProximaMap.get("od"));
            visionProxima.setOI((String) visionProximaMap.get("oi"));
            visionProxima.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());
            // Crear visionproxima
            visionProximaService.agregarVisionProxima(visionProxima);

            // Extraer el objeto Motilidad del cuerpo de la solicitud
            Map<String, Object> motilidadMap = (Map<String, Object>) requestBody.get("Motilidad");
            Motilidad motilidad = new Motilidad();
            motilidad.setDucciones((String) motilidadMap.get("ducciones"));
            motilidad.setVersiones((String) motilidadMap.get("versiones"));
            motilidad.setPpc((String) motilidadMap.get("ppc"));
            motilidad.setCt6m((String) motilidadMap.get("ct6m"));
            motilidad.setCms((String) motilidadMap.get("33cm"));
            motilidad.setOjoDominante((String) motilidadMap.get("ojodominante"));
            motilidad.setManoDominante((String) motilidadMap.get("manodominante"));
            motilidad.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());

            // Crear motilidad
            motilidadService.agregarMotilidad(motilidad);
            // Extraer el objeto oftalmoscopia del cuerpo de la solicitud
            Map<String, Object> oftalmoscopiaMap = (Map<String, Object>) requestBody.get("Oftalmoscopia");
            Oftalmoscopia oftalmoscopia = new Oftalmoscopia();
            oftalmoscopia.setOd((String) oftalmoscopiaMap.get("od"));
            oftalmoscopia.setOi((String) oftalmoscopiaMap.get("oi"));
            oftalmoscopia.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());

            // Crear oftalmoscopia
            oftalmoscopiaService.agregarOftalmoscopia(oftalmoscopia);
            // Extraer el objeto queratometria del cuerpo de la solicitud
            Map<String, Object> queratometriaMap = (Map<String, Object>) requestBody.get("Queratometria");
            Queratometria queratometria = new Queratometria();
            queratometria.setOd((String) queratometriaMap.get("od"));
            queratometria.setOi((String) queratometriaMap.get("oi"));
            queratometria.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());

            // Crear queratometria
            queratometriaService.agregarQueratometria(queratometria);
            // Extraer el objeto retinoscopia del cuerpo de la solicitud
            Map<String, Object> retinoscopiaMap = (Map<String, Object>) requestBody.get("Retinoscopia");
            Retinoscopia retinoscopia = new Retinoscopia();
            retinoscopia.setOd((String) retinoscopiaMap.get("od"));
            retinoscopia.setOi((String) retinoscopiaMap.get("oi"));
            retinoscopia.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());

            // Crear retinoscopia
            retinoscopiaService.agregarRetinoscopia(retinoscopia);
            // Extraer el objeto rxfinal del cuerpo de la solicitud
            Map<String, Object> rxfinalMap = (Map<String, Object>) requestBody.get("RxFinal");
            RxFinal rxFinal = new RxFinal();

            rxFinal.setOd((String) rxfinalMap.get("od"));
            rxFinal.setOi((String) rxfinalMap.get("oi"));
            rxFinal.setAvl((String) rxfinalMap.get("avl"));
            rxFinal.setAvp((String) rxfinalMap.get("avp"));
            rxFinal.setColor((String) rxfinalMap.get("color"));
            rxFinal.setAdd((String) rxfinalMap.get("add"));
            rxFinal.setBif((String) rxfinalMap.get("bif"));
            rxFinal.setUso((String) rxfinalMap.get("uso"));
            rxFinal.setDiagnostico((String) rxfinalMap.get("diagnostico"));
            rxFinal.setConducta((String) rxfinalMap.get("conducta"));
            rxFinal.setExaminador((String) rxfinalMap.get("examinador"));
            rxFinal.setControl((String) rxfinalMap.get("control"));
            rxFinal.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());


            // Crear rxfinal
            rxFinalService.agregarRxFinal(rxFinal);
            Map<String, Object> pacienteMap = (Map<String, Object>) requestBody.get("paciente");
            Paciente paciente1 = new Paciente();
            paciente1.setIdhistoriaclinica(historiaClinica.getIdHistoriaCLinica());



            return ResponseEntity.ok().build(); // Registro exitoso


        } catch (Exception e) {
            String errorMessage = "Error al intentar registrar: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @GetMapping("/generarFormula/{id}")
    public ResponseEntity<?> formulaclinica(@PathVariable("id") int id) {
        Paciente resultado = pacienteService.obtenerPacienteporId(id);
        int idHistoriaClinica = resultado.getIdhistoriaclinica();
        Optional<RxFinal> rxFinal = rxFinalService.rxFinal(idHistoriaClinica);

        if (rxFinal != null) {
            return ResponseEntity.ok(rxFinal);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontro la formula");
        }
    }


}