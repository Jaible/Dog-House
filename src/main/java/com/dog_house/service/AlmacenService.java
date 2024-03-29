package com.dog_house.service;

import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AlmacenService {

    public void iniciarAlmacenServicio();

    public String almacenarArchivo(MultipartFile archivo);

    public Path cargarArchivo(String nombreArchivo);

    public Resource cargarComoRecurso(String nombreArchivo);

    public void eliminarArchivo(String nombreArchivo);
}
