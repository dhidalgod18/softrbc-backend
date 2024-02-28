package com.uan.optica.service.Impl;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Usuario;
import com.uan.optica.entities.UsuarioOptometraDTO;
import com.uan.optica.repository.OptometraRepository;
import com.uan.optica.repository.UsuarioRepository;
import com.uan.optica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OptometraRepository optometraRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean crearPersona(Usuario usuario) {
        try {
            String passwordBc = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passwordBc);
            usuarioRepository.save(usuario);
             return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UsuarioOptometraDTO> obtenerUsuariosOptometraDTO() {
        List<Usuario> usuarios = usuarioRepository.findByRol("ROLE_OPTOMETRA");
        List<UsuarioOptometraDTO> usuariosOptometraDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            Optometra optometra = optometraRepository.findByUsuarioId(usuario.getIdusuario());
            if (optometra != null) {
                usuariosOptometraDTO.add(new UsuarioOptometraDTO(usuario, optometra.getNumerotarjeta(), optometra.isActivo()));
            }
        }

        return usuariosOptometraDTO;
    }

    @Override
    public boolean modificarDatosOptometra(int idUsuario, String nuevadireccion, String nuevocorreo, Long nuevotelefono) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
            if (optionalUsuario.isPresent()) {
                Usuario optometra = optionalUsuario.get();
                optometra.setCorreo(nuevocorreo);
                optometra.setDireccion(nuevadireccion);
                optometra.setTelefono(nuevotelefono);
                usuarioRepository.save(optometra);
                return true;
            } else {
                return false; // El usuario no fue encontrado
            }
        } catch (NumberFormatException e) {
            // Manejo de la excepción si el formato del teléfono es inválido
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // Manejo de otras excepciones
            e.printStackTrace();
            return false;
        }
    }

    public boolean cambiarEstadoUsuario(int idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario != null) {
            Optometra optometra = optometraRepository.findByUsuarioId(idUsuario);
            if (optometra != null) {
                // Invertir el estado actual
                boolean nuevoEstado = !optometra.isActivo();
                optometra.setActivo(nuevoEstado);
                optometraRepository.save(optometra);
                return true;
            }
        }
        return false;
    }



}
