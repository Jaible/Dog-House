package com.dog_house.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Habitacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String servicios;

    @NotNull
    private int precio;
    
    private String rutaPortada;

    @Transient
    private MultipartFile portada;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public MultipartFile getPortada() {
        return portada;
    }

    public void setPortada(MultipartFile portada) {
        this.portada = portada;
    }

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }

    public Habitacion(Integer id, String nombre, String descripcion, String servicios, int precio, MultipartFile portada,
            String rutaPortada) {
        this.id = id;   
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.servicios = servicios;
        this.precio = precio;
        this.portada = portada;
        this.rutaPortada = rutaPortada;
    }

    public Habitacion(String nombre, String descripcion, String servicios, int precio, MultipartFile portada,
            String rutaPortada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.servicios = servicios;
        this.precio = precio;
        this.portada = portada;
        this.rutaPortada = rutaPortada;
    }

    public Habitacion() {
        super();
    }
}
