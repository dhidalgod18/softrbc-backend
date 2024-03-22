package com.uan.optica.service;

import com.uan.optica.entities.Cita;
import org.springframework.stereotype.Service;

@Service
public interface EnvioCorreoService {
    void  enviarCorreoRegistroOptometra(String destinatario, String asunto, String correoUsuario, String contraseñaGenerada, String codigorecuperacion);
    void enviarCorreoVerificacionCita(String destinatario, String codigoVerificacion, Cita cita);
    void enviarCorreo(String destinatario, String asunto, String mensaje);
}
