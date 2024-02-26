package com.uan.optica.filtros;
import java.security.SecureRandom;

public class PasswordAleatorio {
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String CARACTERES_ESPECIALES = "!.+*";

    private static final String ALL_CHARS = MAYUSCULAS + MINUSCULAS + NUMEROS + CARACTERES_ESPECIALES;

    public static void main(String[] args) {
        // Generar una contraseña aleatoria
        String password = generarPassword();

        // Mostrar la contraseña generada
        System.out.println("Contraseña generada: " + password);
    }

    public static String generarPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Asegurar al menos un carácter de cada tipo
        password.append(MAYUSCULAS.charAt(random.nextInt(MAYUSCULAS.length())));
        password.append(MINUSCULAS.charAt(random.nextInt(MINUSCULAS.length())));
        password.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));
        password.append(CARACTERES_ESPECIALES.charAt(random.nextInt(CARACTERES_ESPECIALES.length())));

        // Generar el resto de la contraseña
        int longitud = random.nextInt(5) + 8; // Longitud aleatoria entre 8 y 12 caracteres
        for (int i = 4; i < longitud; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // Mezclar los caracteres para mayor seguridad
        for (int i = 0; i < longitud; i++) {
            int randomIndexToSwap = random.nextInt(longitud);
            char temp = password.charAt(randomIndexToSwap);
            password.setCharAt(randomIndexToSwap, password.charAt(i));
            password.setCharAt(i, temp);
        }

        return password.toString();
    }
}
