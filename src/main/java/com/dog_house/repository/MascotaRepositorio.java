package com.dog_house.repository;

import com.dog_house.entity.Mascota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepositorio extends CrudRepository<Mascota,Long>{
    
}
