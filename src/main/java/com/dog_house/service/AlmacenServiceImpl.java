package com.dog_house.service;

import com.dog_house.exceptions.AlmacenExcepcion;
import com.dog_house.exceptions.FileNotFoundExcepcion;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlmacenServiceImpl implements AlmacenService {

    @Value("${storage.location}")
    private String storageLocation;

    @PostConstruct
    @Override
    public void iniciarAlmacenServicio() {
        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException ex) {
            throw new AlmacenExcepcion("Error al inicializar la ubicacion en el almacen de archivos");
        }
    }

    @Override
    public String almacenarArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();

        if (archivo.isEmpty()) {
            throw new AlmacenExcepcion("No se puede almacenar un archivo vacio");
        }

        try {
            InputStream inputStream = archivo.getInputStream();

            //Replace_Existing sirve para que en caso de que haya un archivo con el mismo nombre, lo reemplaze
            Files.copy(inputStream, Paths.get(storageLocation).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            throw new AlmacenExcepcion("Error al almacenar el archivo " + nombreArchivo, ex);
        }
        return nombreArchivo;
    }

    @Override
    public Path cargarArchivo(String nombreArchivo) {
        return Paths.get(storageLocation).resolve(nombreArchivo);
    }

    @Override
    public Resource cargarComoRecurso(String nombreArchivo) {
        try {
            Path archivo = cargarArchivo(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());

            if (recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                throw new FileNotFoundExcepcion("No se pudo encontrar el archivo " + nombreArchivo);
            }

        } catch (MalformedURLException ex) {
            throw new FileNotFoundExcepcion("No se pudo encontrar el archivo " + nombreArchivo, ex);
        }
    }

    @Override
    public void eliminarArchivo(String nombreArchivo) {
        Path archivo = cargarArchivo(nombreArchivo);

        try {
            FileSystemUtils.deleteRecursively(archivo);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
