package com.dog_house.service;

import com.dog_house.entity.Usuario;
import java.util.List;

public interface IUsuarioService {
    public List<Usuario> getAllUsuario();
    public Usuario getUsuarioById(long id);
    public void saveUsuario(Usuario usuario);
    public void delete (long id);
    public Usuario findByUsuario(String usuario);
}
