package com.uan.optica.controller;

import com.uan.optica.entities.Auditoria;
import com.uan.optica.service.AuditoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/auditoria")

public class AuditoriaController {

    @Autowired
    private AuditoriaServices auditoriaServices;


}
