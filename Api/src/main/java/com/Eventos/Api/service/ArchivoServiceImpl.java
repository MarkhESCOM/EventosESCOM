package com.Eventos.Api.service;

import com.Eventos.Api.model.Archivo;
import com.Eventos.Api.repository.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class ArchivoServiceImpl implements ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public Archivo subirArchivo(MultipartFile archivo) throws IOException {
        Archivo nuevoArchivo = new Archivo();
        nuevoArchivo.setNombre(archivo.getOriginalFilename());
        nuevoArchivo.setFechaSubida(new Date());
        nuevoArchivo.setContenido(archivo.getBytes());

        // Guardar el archivo en el sistema de archivos o la Base de Datos, según tus requisitos
        // Aquí puedes agregar lógica para decidir dónde almacenar el archivo

        // Ejemplo: Guardar en el sistema de archivos
        Path filePath = Paths.get(uploadPath, archivo.getOriginalFilename());
        Files.write(filePath, archivo.getBytes());

        return archivoRepository.save(nuevoArchivo);
    }

    @Override
    public List<Archivo> listarArchivos() {
        return archivoRepository.findAll();
    }

    @Override
    public Archivo obtenerArchivo(Long id) {
        return archivoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarArchivo(Long id) {
        archivoRepository.deleteById(id);
    }
}
