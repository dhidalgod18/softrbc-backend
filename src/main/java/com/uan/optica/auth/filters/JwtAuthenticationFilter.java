package com.uan.optica.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uan.optica.entities.Usuario;
import com.uan.optica.service.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.uan.optica.auth.tokenJwtConfig.*;

/**UsernamePasswordAuthenticationFilter maneja una ruta
 * que se llama login que es por defecto y solamente se ejecuta
 * ese filtro se llama cuando el metodo sea post y la url login
 * controlador que intercepta los request que sea post */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private  AuthenticationManager authenticationManager;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**intentar realizar la autenticacion
     * el login
     * request obtiene los datos del request que esta en el cuerpo
     * del request
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /**Tenemos que poblar el objeto usuario de forma
         * manual*/
        Usuario usuario = null;
        /**Vienen en el cuerpo del request*/
        String correo = null;
        String password = null;

        try {
            /** el user y password Lo capturamos con
             * request.getInputStream
             * toma datos y los pobla en la clase usuario
             * crea la instancia por debajo con lo datos creados */
            usuario = new ObjectMapper().readValue(request.getInputStream(),Usuario.class);
            correo = usuario.getCorreo();
            password = usuario.getPassword();

            logger.info("Username desde request InputStream (raw)"+correo);
            logger.info("Password desde request InputStream (raw)"+password);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /**Nos autenticamos mediante el user y password
         * */
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(correo,password);

        return authenticationManager.authenticate(authToken);
    }
/**Si salio todo bien*/
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String correo = ((MyUserDetails) authResult.getPrincipal()).getUsername();
        String rol = ((MyUserDetails) authResult.getPrincipal()).getRol();
        System.out.println(rol+"El rol sin el role");
        String nombre = ((MyUserDetails) authResult.getPrincipal()).getNombre();
        String apellido = ((MyUserDetails) authResult.getPrincipal()).getApellido();
        //Esta línea obtiene las autoridades (roles) del usuario autenticado
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        // Agrega otros claims según el rol del usuario
        if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            claims.put("isAdmin", true);
        } else if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_OPTOMETRA"))) {
            claims.put("isOptometra", true);
        }
        //String originalInput = SECRET_KEY + ":" + correo;
        //String token = Base64.getEncoder().encodeToString(originalInput.getBytes());
        String token = Jwts.builder()

                .setClaims(claims)
                .setSubject(rol)
                //firma
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                //fecha actual mas una hora en milesegundos
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();
        System.out.println("..........");
        response.addHeader(HEADER_AUTORIZATION, PREFIX_TOKEN +token);
        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("correo", correo);
        body.put("nombre", nombre);
        body.put("apellido", apellido);
        body.put("username", correo);
        /**Lo passamos a un json  */
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }
/**Si algo sale mal*/
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Error en la autenticacion username o password incorrecto");
        body.put("error", failed.getMessage());
        response.getWriter().write(new  ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
