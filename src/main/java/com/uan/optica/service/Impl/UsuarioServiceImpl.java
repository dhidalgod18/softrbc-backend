package com.uan.optica.service.Impl;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Paciente;
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
                usuariosOptometraDTO.add(new UsuarioOptometraDTO(usuario, optometra.getIdoptometra(),optometra.getNumerotarjeta(), optometra.isActivo()));
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

    @Override
    public Usuario obtenerUsuarioPorCorreo(String cedula) {
        Usuario usuario = usuarioRepository.getUserByUserName(cedula);
        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario.getNombre()); // Imprime el nombre del usuario encontrado
        } else {
            System.out.println("Usuario no encontrado para el cedula: " + cedula);
        }
        return usuario;
    }

    @Override
    public boolean actualizarContraseña(int idUsuario, String nuevaContraseña) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
            if (optionalUsuario.isPresent()) {
                Usuario usuario = optionalUsuario.get();
                String nuevaContraseñaEncriptada = passwordEncoder.encode(nuevaContraseña);
                usuario.setPassword(nuevaContraseñaEncriptada);
                usuarioRepository.save(usuario);
                return true;
            } else {
                return false; // El usuario no fue encontrado
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuarioCedula(Long cedula) {
        return usuarioRepository.findByUsuarioIdp(cedula);
    }

    @Override
    public int obtenerAdmin() {
        List<Usuario> usuarios = usuarioRepository.findByRol("ROLE_ADMIN");
        int idadmin = 0;

            if (usuarios != null) {
                idadmin = usuarios.get(0).getIdusuario();

        }

        return idadmin;

    }
}
