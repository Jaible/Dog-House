package com.dog_house.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Reservacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @JoinColumn(name="habitacion_id")
    private Habitacion habitacion;
    
    @NotBlank
    private int noches;
    
    @NotBlank
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate fecha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public int getNoches() {
        return noches;
    }

    public void setNoches(int noches) {
        this.noches = noches;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Reservacion(long id, Habitacion habitacion, int noches, LocalDate fecha) {
        this.id = id;
        this.habitacion = habitacion;
        this.noches = noches;
        this.fecha = fecha;
    }

    public Reservacion(Habitacion habitacion, int noches, LocalDate fecha) {
        this.habitacion = habitacion;
        this.noches = noches;
        this.fecha = fecha;
    }

    public Reservacion() {
        super();
    }
}
