package com.uan.optica.service.Impl;

import com.uan.optica.entities.Usuario;
import com.uan.optica.repository.UsuarioRepository;
import com.uan.optica.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UseDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**Toma el nombre del usuario de objeto de autenticacion, lo que trasmite el filtro
     * cargar ese usuario que esta autenticado, con sus detalles, nombre*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**Verificamos si lo que s eacaba de autenticar existe*/
        Usuario user = usuarioRepository.getUserByUserName(username);
        System.out.println(username +"QUE TRAEEEEEEE");
        if (user == null){
            System.out.println(user.getNombre() +"no entre");

            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        /**Retornamos ese usuario, obtener todos los datos*/
        System.out.println(user.getNombre() +"le pase los datos entre");
        System.out.println(user.getRol() +"le pase los datos entre");
        System.out.println(user.getPassword() +"Contraseña");




        return new MyUserDetails(user);
    }
}

