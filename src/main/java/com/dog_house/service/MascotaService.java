package com.dog_house.service;

import com.dog_house.entity.Mascota;
import com.dog_house.entity.Usuario;
import com.dog_house.repository.MascotaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class MascotaService implements IMascotaService{
    
    @Autowired
    private MascotaRepositorio mascotaRepository;
    
    @Override
    public List<Mascota> getAllMascota() {
        return (List<Mascota>)mascotaRepository.findAll();
    }

    @Override
    public Mascota getMascotaById(long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMascota(Mascota mascota) {
        mascotaRepository.save(mascota);
    }

    @Override
    public void delete(long id) {
        mascotaRepository.deleteById(id);
    }
    
}
