/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dog_house.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author crist
 */
@Entity 

public class Contacto implements Serializable {

    @Id
    @Column(name = "id_contacto")
    private long id;

    @NotBlank
    private String Direccion;

    @NotBlank
    private String Correo;

    @NotNull
    private int Telefonos;

    @NotNull
    private int WhatsApp;
    
    @NotBlank
    private String Horarios;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getTelefonos() {
        return Telefonos;
    }

    public void setTelefonos(int Telefonos) {
        this.Telefonos = Telefonos;
    }

    public int getWhatsApp() {
        return WhatsApp;
    }

    public void setWhatsApp(int WhatsApp) {
        this.WhatsApp = WhatsApp;
    }

    public String getHorarios() {
        return Horarios;
    }

    public void setHorarios(String Horarios) {
        this.Horarios = Horarios;
    }

    public Contacto(long id, String Direccion, String Correo, int Telefonos, int WhatsApp, String Horarios) {
        this.id = id;
        this.Direccion = Direccion;
        this.Correo = Correo;
        this.Telefonos = Telefonos;
        this.WhatsApp = WhatsApp;
        this.Horarios = Horarios;
    }

    public Contacto(String Direccion, String Correo, int Telefonos, int WhatsApp, String Horarios) {
        this.Direccion = Direccion;
        this.Correo = Correo;
        this.Telefonos = Telefonos;
        this.WhatsApp = WhatsApp;
        this.Horarios = Horarios;
    }
    
   public Contacto() {
       super();
    }
}