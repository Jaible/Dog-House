package com.dog_house.service;

import com.dog_house.entity.Mascota;
import java.util.List;


public interface IMascotaService {
    public List<Mascota> getAllMascota();
    public Mascota getMascotaById(long id);
    public void saveMascota(Mascota mascota);
    public void delete (long id);
}
