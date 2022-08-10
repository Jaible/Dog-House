/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dog_house.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author crist
 */

@Entity 
public class Testimonio implements Serializable{
@Id
@Column(name = "id_testimonio")
    private long id;   

@NotBlank
private String nombre;

@NotBlank
private String descripcion;

private String rutaPortada;

@Transient
private MultipartFile portada;

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }

    public MultipartFile getPortada() {
        return portada;
    }

    public void setPortada(MultipartFile portada) {
        this.portada = portada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }
    
    public Testimonio(long id, String nombre, String descripcion, String rutaPortada, MultipartFile portada) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rutaPortada = rutaPortada;
        this.portada = portada;
    }

    public Testimonio(String nombre, String descripcion, String rutaPortada, MultipartFile portada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rutaPortada = rutaPortada;
        this.portada = portada;
    }

    public Testimonio() {
    }


}
