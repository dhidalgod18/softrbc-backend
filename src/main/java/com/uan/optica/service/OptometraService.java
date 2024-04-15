package com.uan.optica.service;

import com.uan.optica.entities.Optometra;
import org.springframework.stereotype.Service;

@Service

public interface OptometraService {
        Optometra crearOptometra(Optometra optometra);
        int obtenerOptometra();

}
