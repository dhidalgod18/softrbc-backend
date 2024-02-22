package com.uan.optica.filtros;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class passworEnconder {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "Colombia24*";
        String pass = passwordEncoder.encode(password);
        System.out.println(pass);
    }


}
