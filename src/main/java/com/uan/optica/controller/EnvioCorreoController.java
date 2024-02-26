package com.uan.optica.controller;

import com.uan.optica.entities.Correo;
import com.uan.optica.service.EnvioCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/correo")
public class EnvioCorreoController {


    public class MailController {

        @Autowired
        private EnvioCorreoService envioCorreoService;

        @PostMapping("/envio")
        public ResponseEntity<?> sendMail(@RequestBody Correo correo) {

            System.out.println("Llega mensaje: " + correo.toString());

            envioCorreoService.enviarCorreo(correo.getDestinatario(), correo.getAsunto(), correo.getCorreousuario(), correo.getContraseñagenerada());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Correo enviado");

            return ResponseEntity.ok(response);
        }
    }
}
