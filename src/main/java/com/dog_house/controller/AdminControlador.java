package com.dog_house.controller;

import com.dog_house.entity.Contacto;
import com.dog_house.entity.Cuenta;
import com.dog_house.entity.Habitacion;
import com.dog_house.entity.Reservacion;
import com.dog_house.entity.Testimonio;
import com.dog_house.repository.ContactoRepositorio;
import com.dog_house.repository.HabitacionRepositorio;
//import com.dog_house.repository.CuentaRepositorio;
import com.dog_house.repository.ReservacionRepositorio;
import com.dog_house.repository.TestimonioRepositorio;
import com.dog_house.service.AlmacenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @Autowired
    private ContactoRepositorio contactoRepositorio;

    @Autowired
    private ReservacionRepositorio reservacionRepositorio;

    @Autowired
    private TestimonioRepositorio testimonioRepositorio;

//     @Autowired
//    private CuentaRepositorio cuentaRepositorio;
    @GetMapping("/habitaciones")
    public ModelAndView listadoHabitaciones(@PageableDefault(sort = "nombre", size = 5) Pageable pageable) {
        Page<Habitacion> habitaciones = habitacionRepositorio.findAll(pageable);
        return new ModelAndView("admin/habitaciones").addObject("habitaciones", habitaciones);
    }

    @GetMapping("/habitaciones/nuevo")
    public ModelAndView formularioHabitacion() {
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

    @GetMapping("/testimonios/nuevo")
    public ModelAndView crearTestimonio() {
        return new ModelAndView("admin/nuevo-testimonio").addObject("testimonio", new Testimonio());
    }

    @PostMapping("/testimonios/nuevo")
    public ModelAndView registrarTestimonio(@Validated Testimonio testimonio, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (testimonio.getPortada().isEmpty()) {
                bindingResult.rejectValue("portada", "MultipartNotEmpty");
            }
            return new ModelAndView("admin/nuevo-testimonio").addObject("testimonio", testimonio);
        }
        String RutaPortada = servicio.almacenarArchivo(testimonio.getPortada());
        testimonio.setRutaPortada(RutaPortada);
        testimonioRepositorio.save(testimonio);
        return new ModelAndView("redirect:/testimonios");
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

        if (!habitacion.getPortada().isEmpty()) {
            servicio.eliminarArchivo(habitacionDB.getRutaPortada());
            String rutaPortada = servicio.almacenarArchivo(habitacion.getPortada());
            habitacionDB.setRutaPortada(rutaPortada);
        }

        habitacionRepositorio.save(habitacionDB);
        return new ModelAndView("redirect:/habitaciones");
    }

    @PostMapping("/habitaciones/{id}/eliminar")
    public String eliminarHabitacion(@PathVariable long id) {
        Habitacion habitacion = habitacionRepositorio.getOne(id);
        habitacionRepositorio.delete(habitacion);
        servicio.eliminarArchivo(habitacion.getRutaPortada());

        return "redirect:/habitaciones";
    }

    @GetMapping("/contacto/nuevo")
    public ModelAndView formularioContacto() {
        return new ModelAndView("admin/nuevo-contacto").addObject("contacto", new Contacto());
    }

    @PostMapping("/contacto/nuevo")
    public ModelAndView registrarContacto(@Validated Contacto contacto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/nuevo-contacto").addObject("contacto", contacto);
        }

        Contacto contactoDB = contactoRepositorio.getOne((long) 1);
        contactoDB.setCorreo(contacto.getCorreo());
        contactoDB.setDireccion(contacto.getDireccion());
        contactoDB.setHorarios(contacto.getHorarios());
        contactoDB.setTelefonos(contacto.getTelefonos());
        contactoDB.setWhatsApp(contacto.getWhatsApp());

        contactoRepositorio.save(contactoDB);
        return new ModelAndView("redirect:/contacto");
    }

    @GetMapping("/contacto/{id}/editar")
    public ModelAndView editarContacto() {
        Contacto contacto = contactoRepositorio.getOne((long) 1);
        return new ModelAndView("/admin/editar-contacto").addObject("contacto", contacto);
    }

    @PostMapping("/contacto/{id}/editar")
    public ModelAndView actualizarContacto(@Validated Contacto contacto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/nuevo-contacto").addObject("contacto", contacto);
        }

        Contacto contactoDB = contactoRepositorio.getOne((long) 1);
        contactoDB.setCorreo(contacto.getCorreo());
        contactoDB.setDireccion(contacto.getDireccion());
        contactoDB.setHorarios(contacto.getHorarios());
        contactoDB.setTelefonos(contacto.getTelefonos());
        contactoDB.setWhatsApp(contacto.getWhatsApp());

        contactoRepositorio.save(contactoDB);
        return new ModelAndView("redirect:/contacto");
    }

    @GetMapping("/contacto/{id}/eliminar")
    public ModelAndView eliminarContacto() {
        Contacto contactoDB = contactoRepositorio.getOne((long) 1);
        contactoDB.setCorreo("default");
        contactoDB.setDireccion("default");
        contactoDB.setHorarios("default");
        contactoDB.setTelefonos(0);
        contactoDB.setWhatsApp(0);

        contactoRepositorio.save(contactoDB);
        return new ModelAndView("redirect:/contacto");
    }

    @GetMapping("/reservacion/{id}/editar")
    public ModelAndView editarReservación(@PathVariable long id) {
        Reservacion reservacion = reservacionRepositorio.getOne(id);
        return new ModelAndView("admin/editar-reservacion").addObject("reservacion", reservacion);
    }

    @PostMapping("/reservacion/{id}/editar")
    public ModelAndView actualizarReservacion(@PathVariable long id,
            @Validated Reservacion reservacion, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/editar-reservacion")
                    .addObject("reservacion", reservacion);
        }

        Reservacion reservacionDB = reservacionRepositorio.getOne(id);
        reservacionDB.setHabitacion(reservacionDB.getHabitacion());
        reservacionDB.setFecha(reservacion.getFecha());
        reservacionDB.setNoches(reservacion.getNoches());

        reservacionRepositorio.save(reservacionDB);
        return new ModelAndView("redirect:/reservacion");
    }

    @GetMapping("/reservacion/{id}/eliminar")
    public String eliminarReservacion(@PathVariable long id) {
        reservacionRepositorio.deleteById(id);
        return "redirect:/reservacion";
    }
}

//@GetMapping("/cuenta/nueva")
//public ModelAndView formularioCuenta() {
//    return new ModelAndView("admin/nueva-cuenta").addObject("cuenta", new Cuenta());
//}
//}
//@PostMapping("/cuenta/nueva")
//public ModelAndView registrarContacto(@Validated Cuenta cuenta, BindingResult bindingResult) {
//    if (bindingResult.hasErrors()) {
//        return new ModelAndView("admin/nueva-cuenta").addObject("cuenta", cuenta);
//    }
//Cuenta cuentaDB = cuentaRepositorio.getOne((long) 1);
//        cuentaDB.setNombreMascota(cuenta.getNombreMascota());
//        cuentaDB.setRaza(cuenta.getRaza());
//        cuentaDB.setEdad(cuenta.getRaza());
//        cuentaDB.setGustos(cuenta.getGustos());
//        cuentaDB.setDisgustos(cuenta.getDisgustos());
//        cuentaDB.setEmailDueno(cuenta.getEmailDueno());
//        contactoDB.setTelefonos(contacto.getTelefonos());
//        
//        cuentaRepositorio.save(cuentaDB);
//        return new ModelAndView("redirect:/cuenta");
//    }
//
//    @GetMapping("/cuenta/{id}/editar")
//    public ModelAndView editarCuenta() {
//        Cuenta cuenta = cuentaRepositorio.getOne((long) 1);
//        return new ModelAndView("/admin/nueva-cuenta").addObject("cuenta", cuenta);
//    }
//
//    @PostMapping("/cuenta/{id}/editar")
//    public ModelAndView actualizarCuenta(@Validated Cuenta cuenta, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("admin/nueva-cuenta").addObject("cuenta", cuenta);
//        }
//
//        Cuenta cuentaDB = cuentaRepositorio.getOne((long) 1);
//        cuentaDB.setNombreMascota(cuenta.getNombreMascota());
//        cuentaDB.setRaza(cuenta.getRaza());
//        cuentaDB.setEdad(cuenta.getRaza());
//        cuentaDB.setGustos(cuenta.getGustos());
//        cuentaDB.setDisgustos(cuenta.getDisgustos());
//        cuentaDB.setEmailDueno(cuenta.getEmailDueno());
//        contactoDB.setTelefonos(contacto.getTelefonos());
//
//        cuentaRepositorio.save(cuentaDB);
//        return new ModelAndView("redirect:/cuenta");
//    }
//
//    @PostMapping("/cuenta/{id}/eliminar")
//    public ModelAndView eliminarCuenta() {
//        Cuenta cuentaDB = cuentaRepositorio.getOne((long) 1);
//        cuentaDB.setNombreMascota("default");
//        cuentaDB.setRaza("default");
//        cuentaDB.setEdad("default");
//        cuentaDB.setGustos("default");
//        cuentaDB.setDisgustos("default");
//        cuentaDB.setEmailDueno("default");
//        cuentaDB.setTelefonos("0");
//
//        cuentaRepositorio.save(cuentaDB);
//        return new ModelAndView("redirect:/cuenta");
//    }
//}
