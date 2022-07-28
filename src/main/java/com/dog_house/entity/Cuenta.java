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
 * @author jenn
 */
@Entity 
public class Cuenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Cuenta")
    private long id;
    @NotBlank
    private String Raza;
    @NotBlank
    private int Edad;
    @NotNull
    private String Gustos;
    @NotNull
    private String Disgustos;
    @NotNull
    private String EmailDueno;
    @NotBlank
    private int Telefonos;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getRaza() {
        return Raza;
    }
    public void setRaza(String Raza) {
        this.Raza = Raza;
    }

    public int getEdad() {
        return Edad;
    }
    public void setCorreo(int Correo) {
        this.Edad = Edad;
    }

    public String getGustos() {
        return Gustos;
    }
    public void setGustos(String Gustos) {
        this.Gustos = Gustos;
    }

    public String getDisgustos() {
        return Disgustos;
    }
    public void setDisgustos(String Disgustos) {
        this.Disgustos = Disgustos;
    }

    public String getEmailDueno() {
        return EmailDueno;
    }
    public void setEmailDueno(String EmailDueno) {
        this.EmailDueno = EmailDueno;
    }
    public int getTelefonos() {
        return Telefonos;
    }
    public void setTelefonos(int Telefonos) {
        this.Telefonos = Telefonos;
    }

    public Cuenta (long id, String Raza, int Edad, String Gustos, String Disgustos, String EmailDueno, int Telefonos) {
        this.id = id;
        this.Raza = Raza;
        this.Edad = Edad;
        this.Gustos = Gustos;
        this.Disgustos = Disgustos;
        this.EmailDueno = EmailDueno;
        this.Telefonos = Telefonos;
    }

    public Cuenta (String Raza, int Edad, String Gustos, String Disgustos, String EmailDueno, int Telefonos) {
        this.Raza = Raza;
        this.Edad = Edad;
        this.Gustos = Gustos;
        this.Disgustos = Disgustos;
        this.EmailDueno = EmailDueno;
        this.Telefonos = Telefonos;
    }


   public Cuenta() {
       super();
    }
}
