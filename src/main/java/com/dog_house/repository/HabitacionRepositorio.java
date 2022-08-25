package com.dog_house.repository;

import com.dog_house.entity.Habitacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepositorio extends JpaRepository<Habitacion,Long>{
    List<Habitacion> findAllById(Long id);
}
