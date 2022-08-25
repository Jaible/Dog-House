package com.dog_house.controller;

import com.dog_house.entity.Contacto;
import com.dog_house.entity.Factura;
import com.dog_house.entity.Habitacion;
import com.dog_house.entity.Reservacion;
import com.dog_house.entity.Testimonio;
import com.dog_house.entity.Usuario;
import com.dog_house.repository.ContactoRepositorio;
import com.dog_house.repository.FacturaRepositorio;
import com.dog_house.repository.HabitacionRepositorio;
import com.dog_house.repository.ReservacionRepositorio;
import com.dog_house.repository.TestimonioRepositorio;
import com.dog_house.repository.UsuarioRepositorio;
import com.dog_house.service.AlmacenServiceImpl;
import com.dog_house.service.ReportServiceImpl;
import com.dog_house.service.Userprincipal;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class HomeControlador {

    @Autowired
    private HabitacionRepositorio habitacionRepositorio;

    @Autowired
    private ReservacionRepositorio reservacionRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ContactoRepositorio contactoRepositorio;
    
    @Autowired
    private AlmacenServiceImpl servicio;
    
    @Autowired
    private FacturaRepositorio facturaRepositorio;
    
    @Autowired
    private ReportServiceImpl reportService;
    
    @Autowired
    private TestimonioRepositorio testimonioRepositorio;
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/error")
    public String error(){
        return "error";
    }

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
    public ModelAndView paginaReservaciones(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<Reservacion> reservaciones = reservacionRepositorio.findAll(pageable);
        return new ModelAndView("/reservaciones").addObject("reservaciones", reservaciones);
    }
    @GetMapping("/testimonios")
    public ModelAndView paginaTestimonios(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page <Testimonio> testimonios = testimonioRepositorio.findAll(pageable);
        return new ModelAndView("testimonios")
                .addObject("testimonios", testimonios);
    }

    @GetMapping("/habitaciones/{id}/Pagos")
    public ModelAndView mostrarFormularioPago(@PathVariable Long id) {
        Habitacion habitacion = habitacionRepositorio.getOne(id);
        
        return new ModelAndView("Pagos")
                .addObject("habitacion", habitacion)
                .addObject("Factura", new Factura())
                .addObject("reservacion", new Reservacion());
    }
    
    @PostMapping("/habitaciones/{id}/Pagos")
    public String registrarFactura(@RequestParam("id") Long id,
            @Validated Factura factura, Reservacion reservacion) throws JRException, IOException{
        Habitacion habitacion = habitacionRepositorio.getOne(id);
        facturaRepositorio.save(factura);
        
        
        reservacion.setHabitacion(habitacion);
        reservacionRepositorio.save(reservacion);
        
        String fileLink = reportService.generateReport(id, "pdf");
        return "redirect:/"+fileLink;
    }

    @GetMapping("/contacto")
    public ModelAndView Contacto() {
        long cantidad = contactoRepositorio.count();

        if (cantidad > 0) {
            Contacto contacto = contactoRepositorio.getOne((long) 1);

            return new ModelAndView("/contacto")
                    .addObject("contacto", contacto);
        } else {
            Contacto contacto = new Contacto();
            contacto.setId((long) 1);
            contacto.setCorreo("default");
            contacto.setDireccion("default");
            contacto.setHorarios("default");
            contacto.setTelefonos(0);
            contacto.setWhatsApp(0);
            
            contactoRepositorio.save(contacto);
            return new ModelAndView("/contacto")
                    .addObject("contacto", contacto);
        }
    }

    @GetMapping("/reservacion")
    public ModelAndView mostrarReservaciones(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<Reservacion> reservaciones = reservacionRepositorio.findAll(pageable);
        return new ModelAndView("reservaciones")
                .addObject("reservacion", reservaciones);
    }
    
    @GetMapping("/testimonios/lista")
    public ModelAndView mostrarTestimonios(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<Testimonio> testimonios = testimonioRepositorio.findAll(pageable);
        return new ModelAndView("misTestimonios")
                .addObject("testimonios", testimonios);
    }
    
    @GetMapping("/testimonios/lista/{id}/editar")
    public ModelAndView editarTestimonio(@PathVariable long id) {
        Testimonio testimonio = testimonioRepositorio.getOne(id);
        return new ModelAndView("/editar-testimonio").addObject("testimonio", testimonio);
    }
    
    @PostMapping("/testimonios/lista/{id}/eliminar")
    public String eliminarTestimonio(@PathVariable long id) {
        Testimonio testimonio = testimonioRepositorio.getOne(id);
        testimonioRepositorio.delete(testimonio);
        testimonioRepositorio.deleteById(id);
        servicio.eliminarArchivo(testimonio.getRutaPortada());
        
        return "redirect:/testimonios";
    }
    
    @GetMapping("/UserProfile")
    public ModelAndView mostrarUsuario(Userprincipal principal){
        Usuario usuario = principal.getUsuario();
        return new ModelAndView("/UserProfile")
                .addObject("usuario", usuario);
    }
    
    @GetMapping("/UserProfile/{id}/editar")
    public ModelAndView editarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepositorio.getOne(id);
        return new ModelAndView("/modificarCuenta").addObject("usuario",usuario);
    }
    
    @GetMapping("/login")
    public String login(){
        return "/login";
    }
}
