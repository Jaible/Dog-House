package com.dog_house.repository;

import com.dog_house.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario,Long>{
    Usuario findByUsername(String usuario);
}
