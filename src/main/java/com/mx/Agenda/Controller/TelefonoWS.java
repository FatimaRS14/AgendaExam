package com.mx.Agenda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.Agenda.Dominio.Telefono;
import com.mx.Agenda.Service.ITelefonoService;

@RestController
@RequestMapping(path = "/api/telefono")
@CrossOrigin
public class TelefonoWS {

    @Autowired
    private ITelefonoService service;
    
  //LISTAR--------------------------->http://localhost:8010/api/telefono/listar
    @GetMapping("listar")
    public ResponseEntity<List<Telefono>> listar() {
        List<Telefono> tel = service.listar();

        if (tel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tel);
    }

  //GUARDAR---------------------------------->http://localhost:8010/api/telefono/guardar
    @PostMapping("guardar")
    public ResponseEntity<?> guardar(@RequestBody Telefono t) {

        if (service.telefonoDuplicado(t.getTelefonoNum())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un teléfono con el número: " + t.getTelefonoNum());
        }

        if (!t.getTipo().equalsIgnoreCase("Casa") &&
            !t.getTipo().equalsIgnoreCase("Celular") &&
            !t.getTipo().equalsIgnoreCase("Trabajo")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El tipo de teléfono solo puede ser: Casa, Celular o Trabajo");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.guardar(t));
    }
    
  //BUSCAR----------------------------------------------->http://localhost:8010/api/telefono/buscar/{idTelefono}
    @GetMapping("buscar/{telefonoNum}")
    public ResponseEntity<?> buscar(@PathVariable String telefonoNum) {

        Telefono encontrado = service.porNumero(telefonoNum);

        return (encontrado == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(encontrado);
    }

  //EDITAR------------------------------->http://localhost:8010/api/telefono/editar/{idTelefono}
    @PutMapping("editar/{idTelefono}")
    public ResponseEntity<?> editar(@PathVariable Integer idTelefono,
                                    @RequestBody Telefono t) {

        Telefono encontrado = service.buscar(idTelefono);

        if (encontrado == null) {
            return ResponseEntity.notFound().build();
        }

        encontrado.setTelefonoNum(t.getTelefonoNum());
        encontrado.setTipo(t.getTipo());

        return ResponseEntity.ok(service.guardar(encontrado));
    }

  //ELIMINAR----------------------------------------->http://localhost:8010/api/telefono/eliminar/{idTelefono}
    @DeleteMapping("eliminar/{idTelefono}")
    public ResponseEntity<?> eliminar(@PathVariable Integer idTelefono) {

        Telefono t = service.buscar(idTelefono);

        if (t == null) {
            return ResponseEntity.notFound().build();
        }

        service.eliminar(t);
        return ResponseEntity.noContent().build();
    }
    
    
  //PORCONTACTO----------------------------------------->http://localhost:8010/api/telefono/contacto/{contactoId}
    @GetMapping("contacto/{contactoId}")
    public ResponseEntity<?> buscarTelefonosPorContacto(@PathVariable int contactoId) {

        List<Telefono> lista = service.buscarTelefonosPorContacto(contactoId);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }
}