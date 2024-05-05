package com.uan.optica.service.Impl;

import com.uan.optica.entities.Cita;
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

    @Value("${app.chatbot.url}")
    private String urlchatbot;
    @Override
    public void enviarCorreoRegistroOptometra(String destinatario, String asunto, String nombre, String contraseñaGenerada, String codigorecuperacion) {
        // Construye el mensaje de correo electrónico con la información requerida
        String mensaje = "Estimado(a) usuario,\n\n" +
                "Se ha registrado exitosamente en nuestra plataforma.\n\n" +
                "El usuario para acceder al sistema es el numero de cedula"+ "\n" +
                "El nombre del usuario registrado es: " + nombre + "\n"+
                "Contraseña generada: " + contraseñaGenerada + "\n"+
                "Clave de recuperacion: " + codigorecuperacion + "\n\n" +
                "Por favor, utilice esta información para iniciar sesión en nuestra aplicación. " +
                "Puede acceder al inicio de sesión aquí: " + loginUrl + "\n\n" +
                "Atentamente,\n" +
                "El Palacio de las Gafas";

        enviarCorreo(destinatario, "Registro Exitoso", mensaje);

    }

    public void enviarCorreoRegistroOPaciente(String destinatario,String nombre, String codigorecuperacion) {
        // Construye el mensaje de correo electrónico con la información requerida
        String mensaje = "Estimado(a) usuario,\n\n" +
                "Se ha registrado exitosamente en nuestra plataforma.\n\n" +
                "El usuario para acceder al sistema es el numero de cedula"+ "\n" +
                "El nombre del usuario registrado es: " + nombre + "\n"+
                "Clave de recuperacion: " + codigorecuperacion + "\n\n" +
                "Por favor, utilice esta información para iniciar sesión en nuestra aplicación. " +
                "Puede acceder al inicio de sesión aquí: " + urlchatbot + "\n\n" +
                "Atentamente,\n" +
                "El Palacio de las Gafas";

        enviarCorreo(destinatario, "Registro Exitoso", mensaje);

    }

    @Override
    public void enviarCorreoModificacionOptometra(String destinatario, String nombre, String codigorecuperacion) {
        String mensaje = "Estimado(a) usuario,\n\n" +
                "Se han modificado los datos exitosamente.\n\n" +
                "El usuario para acceder al sistema es el numero de cedula"+ "\n" +
                "El nombre del usuario modificado es: " + nombre + "\n"+
                "Clave de recuperacion: " + codigorecuperacion + "\n\n" +
                "Por favor, utilice esta información para iniciar sesión en nuestra aplicación. " +
                "Puede acceder al inicio de sesión aquí: " + loginUrl + "\n\n" +
                "Atentamente,\n" +
                "El Palacio de las Gafas";

        enviarCorreo(destinatario, "Modificacion Exitoso", mensaje);

    }


    @Override
    public void enviarCorreoVerificacionCita(String destinatario, String codigoVerificacion, Cita cita) {
        // Construir el mensaje de correo electrónico para la verificación de la cita
        String mensaje = "Estimado(a) paciente,\n\n" +
                "Su código de verificación de cita es: " + codigoVerificacion + "\n\n" +
                "Detalles de la cita:\n" +
                "Fecha: " + cita.getFecha() + "\n" +
                "Hora: " + cita.getHora() + "\n" +
                "Nombre del paciente: " + cita.getNombre() + "\n" +
                "Dirección de la cita:\n" +
                "Carrera 3A. No 16-51, Segundo piso, costado derecho, local 2-108\n" +
                "Centro Comercial Sanandrexitos\n" +
                "Ibagué, Tolima\n" +
                "\nUtilice este código para confirmar su cita en nuestra plataforma.\n\n" +
                "Para verificar o cancelar su cita, haga clic en el siguiente enlace:\n" +
                urlchatbot + "\n\n" +
                "Atentamente,\n" +
                "El Palacio de las Gafas";

        enviarCorreo(destinatario, "Código de Verificación de Cita", mensaje);
    }
    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailSender);
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(asunto);
        mailMessage.setText(mensaje);

        javaMailSender.send(mailMessage);
    }

}

