package com.uan.optica.filtros;

import java.security.SecureRandom;

public class CodigoCitas {
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final int LONGITUD_CODIGO = 10;

    public static void main(String[] args) {
    String codigo = codigoCita();
    System.out.println(codigo + "codigo para el agendamiento de la cita");

    }

    public static String codigoCita() {
        SecureRandom random = new SecureRandom();
        StringBuilder codigoRecuperacion = new StringBuilder();
        codigoRecuperacion.insert(0, "o");
        codigoRecuperacion.insert(1, "p");

        codigoRecuperacion.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));

        // Generar el resto del código de recuperación
        int restante = LONGITUD_CODIGO - codigoRecuperacion.length();
        for (int i = 0; i < restante; i++) {
            codigoRecuperacion.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));
        }

        return codigoRecuperacion.toString();
    }


}
