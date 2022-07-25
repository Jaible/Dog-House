package com.dog_house.exceptions;

public class FileNotFoundExcepcion extends RuntimeException {

    public FileNotFoundExcepcion(String mensaje) {
        super(mensaje);
    }

    public FileNotFoundExcepcion(String mensaje, Throwable excepcion) {
        super(mensaje, excepcion);
    }
}
