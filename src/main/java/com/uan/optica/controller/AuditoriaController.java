package com.uan.optica.controller;

import com.uan.optica.entities.*;
import com.uan.optica.service.AuditoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auditoria")

public class AuditoriaController {

    @Autowired
    private AuditoriaServices auditoriaServices;

    @GetMapping("/informacion")
    public ResponseEntity<List<Auditoria>>  auditoria() {
           List<Auditoria> listaauditoria = auditoriaServices.auditorias();
           if (listaauditoria != null){
            return ResponseEntity.ok(listaauditoria);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
