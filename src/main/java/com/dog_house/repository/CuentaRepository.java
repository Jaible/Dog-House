package com.dog_house.repository;

import com.dog_house.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepositorio extends CrudRepository<Cuenta,Long>{
    
}
