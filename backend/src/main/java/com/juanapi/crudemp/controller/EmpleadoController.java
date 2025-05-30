package com.juanapi.crudemp.controller;



import com.juanapi.crudemp.exception.ResourceNotFoundException;
import com.juanapi.crudemp.model.Empleado;
import com.juanapi.crudemp.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/empleado")
@CrossOrigin(origins = "*")
public class EmpleadoController {


    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    //POST CREAR EMPLEADO
    @PostMapping(value = "/crear")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);

        return ResponseEntity.ok(nuevoEmpleado);
    }

    //GET CONSULTA EMPLEADOS
    @GetMapping(value = "/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id);
        return ResponseEntity.ok(empleado);
    }

    @GetMapping(value = "/apellido")
    public ResponseEntity<Empleado> obtenerEmpleadoPorApellido(@RequestParam String apellido) throws ResourceNotFoundException {
        Empleado empleado = empleadoService.obtenerEmpleadoPorApellido(apellido);
        return ResponseEntity.ok(empleado);
    }

    @GetMapping(value = "/obtEmpleados")
    public ResponseEntity<List<Empleado>> obtenerEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerEmpleados();
        return ResponseEntity.ok(empleados);
    }

    //Eliminar empleado
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Empleado> elimEmpleado(@PathVariable Long id) throws ResourceNotFoundException {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    //Actualizar empleado
    @PutMapping(value = "/actualizar/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) throws ResourceNotFoundException {
        Empleado empleadoAct = empleadoService.actualizarEmpleado(id, empleadoDetalles);
        return ResponseEntity.ok(empleadoAct);
    }


    @GetMapping(value = "/error")
    public ResponseEntity<Empleado> pruebaError() {
        throw new ResourceNotFoundException("Empleado no encontrado");
    }

}