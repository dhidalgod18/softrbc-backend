package com.uan.optica.service.Impl;

import com.uan.optica.entities.Optometra;
import com.uan.optica.entities.Paciente;
import com.uan.optica.entities.Usuario;
import com.uan.optica.repository.OptometraRepository;
import com.uan.optica.service.OptometraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptometraServiceImpl implements OptometraService {
    @Autowired
    private OptometraRepository optometraRepository;
    public Optometra crearOptometra(Optometra optometra) {
        return optometraRepository.save(optometra);
    }

    @Override
    public int obtenerOptometra() {
        List<Usuario> usuarios = optometraRepository.findByRol("ROLE_OPTOMETRA");
        int idOptometraLogin = 0;

        for (Usuario usuario : usuarios) {
            Optometra optometra = optometraRepository.findByUsuarioId(usuario.getIdusuario());
            if (optometra != null) {
                idOptometraLogin = optometra.getIdoptometra();
            }
        }

        return idOptometraLogin;

    }

    @Override
    public Optometra obtener(int id) {
        return optometraRepository.findByUsuarioId(id);
    }
}
