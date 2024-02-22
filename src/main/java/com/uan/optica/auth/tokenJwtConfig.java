package com.uan.optica.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class tokenJwtConfig {
    //public final static String SECRET_KEY = "algun_token_con_alguna_frase_palabra_secreta";
    //EL PREFIJO DEL TOKEN QUE VIENE ANTES DEL TOKEN
    public final static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public final static String PREFIX_TOKEN = "Bearer ";
    public final static String HEADER_AUTORIZATION = "Authorization";

}
