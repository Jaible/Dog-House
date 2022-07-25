package com.dog_house.service;

import com.dog_house.entity.Usuario;
import com.dog_house.repository.UsuarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepository;
    
    @Override
    public List<Usuario> getAllUsuario() {
        return (List<Usuario>)usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario findByUsuario(String Usuario) {
        return usuarioRepository.findByUsername(Usuario);
    }
    
}
