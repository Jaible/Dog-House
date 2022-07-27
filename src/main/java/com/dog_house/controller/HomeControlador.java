package com.dog_house.controller;

import com.dog_house.entity.Contacto;
import com.dog_house.entity.Habitacion;
import com.dog_house.entity.Reservacion;
import com.dog_house.repository.ContactoRepositorio;
import com.dog_house.repository.HabitacionRepositorio;
import com.dog_house.repository.ReservacionRepositorio;
import com.dog_house.repository.UsuarioRepositorio;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeControlador {
    
    @Autowired
    private HabitacionRepositorio habitacionRepositorio;
    
    @Autowired
    private ReservacionRepositorio reservacionRepositorio;
    
    @Autowired
    
    private ContactoRepositorio contactoRepositorio;
    
    @GetMapping("/habitaciones")
    public ModelAndView paginaHabitaciones(@PageableDefault(sort = "nombre", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Habitacion> habitaciones = habitacionRepositorio.findAll(pageable);
        return new ModelAndView("habitaciones")
                .addObject("habitacion", habitaciones);
    }
    
    @GetMapping("/habitaciones/{id}")
    public ModelAndView detalleHabitacion(@PathVariable long id, Map<String, Object> model) {
        Habitacion habitacion = habitacionRepositorio.getOne(id);
        return new ModelAndView("habitacion")
                .addObject("habitacion", habitacion);
    }
    
    @GetMapping("/reservaciones")
    public ModelAndView paginaReservaciones(@PageableDefault(sort = "id", size = 5) Pageable pageable){
        Page<Reservacion> reservaciones = reservacionRepositorio.findAll(pageable);
        return new ModelAndView("/reservaciones").addObject("reservaciones", reservaciones);
    }
    
    @GetMapping("/Pagos")
    public String index(){
        return "/Pagos";
    }
    
    @GetMapping("/contacto")
    public ModelAndView Contacto() {
        Contacto contacto = contactoRepositorio.getOne((long) 1);
        return new ModelAndView("/contacto")
                .addObject("contacto", contacto);
    }
}
