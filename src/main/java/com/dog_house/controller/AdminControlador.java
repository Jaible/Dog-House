package com.dog_house.controller;

import com.dog_house.entity.Habitacion;
import com.dog_house.repository.HabitacionRepositorio;
import com.dog_house.service.AlmacenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private HabitacionRepositorio habitacionRepositorio;
    
    @Autowired
    private AlmacenServiceImpl servicio;
    
    @GetMapping("/habitaciones/nuevo")
    public ModelAndView formularioHabitacion(){
        return new ModelAndView("admin/nueva-habitacion").addObject("habitacion", new Habitacion());
    }
    
    @PostMapping("habitaciones/nuevo")
    public ModelAndView registrarHabitacion(@Validated Habitacion habitacion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (habitacion.getPortada().isEmpty()) {
                bindingResult.rejectValue("portada", "MultipartNotEmpty");
            }
            
            return new ModelAndView("admin/nueva-habitacion").addObject("habitacion", habitacion);
        }
        
        String rutaPortada = servicio.almacenarArchivo(habitacion.getPortada());
        habitacion.setRutaPortada(rutaPortada);
        
        habitacionRepositorio.save(habitacion);
        return new ModelAndView("redirect:/habitaciones");
    }
    
    @GetMapping("/habitaciones/{id}/editar")
    public ModelAndView editarHabitacion(@PathVariable long id) {
        Habitacion habitacion = habitacionRepositorio.getOne(id);
        return new ModelAndView("admin/editar-habitacion").addObject("habitacion", habitacion);
    }
    
    @PostMapping("/habitaciones/{id}/editar")
    public ModelAndView actualizarHabitacion(@PathVariable long id, @Validated Habitacion habitacion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/editar-habitacion")
                    .addObject("habitacion", habitacion);
        }
        
        Habitacion habitacionDB = habitacionRepositorio.getOne(id);
        habitacionDB.setNombre(habitacion.getNombre());
        habitacionDB.setDescripcion(habitacion.getDescripcion());
        habitacionDB.setPrecio(habitacion.getPrecio());
        habitacionDB.setServicios(habitacion.getServicios());
        
        if(!habitacion.getPortada().isEmpty()){
            servicio.eliminarArchivo(habitacionDB.getRutaPortada());
            String rutaPortada = servicio.almacenarArchivo(habitacion.getPortada());
            habitacionDB.setRutaPortada(rutaPortada);
        }
        
        habitacionRepositorio.save(habitacionDB);
        return new ModelAndView("redirect:/habitaciones");
    }
    
    @PostMapping("/habitaciones/{id}/eliminar")
    public String eliminarHabitacion(@PathVariable long id){
        Habitacion habitacion = habitacionRepositorio.getOne(id);
        habitacionRepositorio.delete(habitacion);
        servicio.eliminarArchivo(habitacion.getRutaPortada());
        
        return "redirect:/habitaciones";
    }
}
