package com.uan.optica.service.Impl;

import com.uan.optica.entities.Usuario;
import com.uan.optica.repository.UsuarioRepository;
import com.uan.optica.service.MyUserDetails;
import com.uan.optica.service.PacienteService;
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
    private PacienteService pacienteService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**Toma el nombre del usuario de objeto de autenticacion, lo que trasmite el filtro
     * cargar ese usuario que esta autenticado, con sus detalles, nombre*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**Verificamos si lo que se acaba de autenticar existe*/
        Usuario user = usuarioRepository.getUserByUserName(username);
        String pas = user.getPassword();

        boolean n = passwordEncoder.matches("Bogota24*",pas);

        if (n){
            System.out.println("gemelosssssssssss");
        }
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // Obtener el ID del paciente utilizando el servicio PacienteService
        int idPaciente = pacienteService.obtenerPaciente();

        // Crear una instancia de MyUserDetails con el usuario y el ID del paciente
        MyUserDetails userDetails = new MyUserDetails(user, idPaciente);

        return userDetails;
    }
}

