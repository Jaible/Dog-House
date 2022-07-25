package com.dog_house.repository;

import com.dog_house.entity.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservacionRepositorio extends JpaRepository<Reservacion,Long>{
    
}
