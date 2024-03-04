package com.uan.optica.service;

public interface EnvioCorreoService {
    void enviarCorreo(String destinatario, String asunto, String correoUsuario, String contraseñaGenerada, String codigorecuperacion);
}
