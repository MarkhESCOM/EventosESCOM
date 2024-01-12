package com.Eventos.Api.service;

import com.Eventos.Api.model.Archivo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArchivoService {

    Archivo subirArchivo(MultipartFile archivo) throws IOException;

    List<Archivo> listarArchivos();

    Archivo obtenerArchivo(Long id);

    void eliminarArchivo(Long id);
}
