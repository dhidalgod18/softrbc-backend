package com.uan.optica.service.Impl;

import com.uan.optica.service.EnvioCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EnvioCorreoImpl implements EnvioCorreoService {
    @Value("${email.sender}")
    private String emailSender;

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${app.login.url}")
    private String loginUrl;
    @Override
    public void enviarCorreo(String destinatario, String asunto, String correoUsuario, String contraseñaGenerada) {
        // Construye el mensaje de correo electrónico con la información requerida
        String mensaje = "Estimado(a) usuario,\n\n" +
                "Se ha registrado exitosamente en nuestra plataforma.\n\n" +
                "Correo electrónico: " + correoUsuario + "\n" +
                "Contraseña generada: " + contraseñaGenerada + "\n\n" +
                "Por favor, utilice esta información para iniciar sesión en nuestra aplicación. " +
                "Puede acceder al inicio de sesión aquí: " + loginUrl + "\n\n" +
                "Atentamente,\n" +
                "El Palacio de las Gafas";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailSender);
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(asunto);
        mailMessage.setText(mensaje);

        javaMailSender.send(mailMessage);
    }
}

