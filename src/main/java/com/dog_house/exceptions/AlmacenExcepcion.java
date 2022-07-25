package com.dog_house.exceptions;

public class AlmacenExcepcion extends RuntimeException {

    public AlmacenExcepcion(String mensaje) {
        super(mensaje);
    }

    public AlmacenExcepcion(String mensaje, Throwable excepcion) {
        super(mensaje, excepcion);
    }
}
