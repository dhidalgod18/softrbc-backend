package com.uan.optica.filtros;

import java.security.SecureRandom;

public class CodigoRecuperacion {
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String CARACTERES_ESPECIALES = "!.*";

    private static final String ALL_CHARS = MAYUSCULAS + MINUSCULAS + NUMEROS + CARACTERES_ESPECIALES;
    private static final int LONGITUD_CODIGO = 20;

    public static void main(String[] args) {
        // Generar un código de recuperación aleatorio
        String codigoRecuperacion = generarCodigoRecuperacion();

        // Mostrar el código de recuperación generado
        System.out.println("Código de recuperación generado: " + codigoRecuperacion);
        System.out.println("tamaño: " + codigoRecuperacion.length());

    }

    public static String generarCodigoRecuperacion() {
        SecureRandom random = new SecureRandom();
        StringBuilder codigoRecuperacion = new StringBuilder();

        // Asegurar al menos un carácter de cada tipo
        codigoRecuperacion.append(MAYUSCULAS.charAt(random.nextInt(MAYUSCULAS.length())));
        codigoRecuperacion.append(MINUSCULAS.charAt(random.nextInt(MINUSCULAS.length())));
        codigoRecuperacion.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));
        codigoRecuperacion.append(CARACTERES_ESPECIALES.charAt(random.nextInt(CARACTERES_ESPECIALES.length())));

        // Generar el resto del código de recuperación
        int restante = LONGITUD_CODIGO - codigoRecuperacion.length();
        for (int i = 0; i < restante; i++) {
            codigoRecuperacion.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        return codigoRecuperacion.toString();
    }

}