package com.mx.Agenda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Agenda.Dominio.Contacto;
import com.mx.Agenda.Service.ContactoServiceImp;

@RestController
@RequestMapping(path ="/api/contacto")
@CrossOrigin
public class ContactoWS {
	@Autowired
	private ContactoServiceImp service;
	
	//LISTAR--------------------------->http://localhost:8010/api/contacto/listar
	@GetMapping("listar")
	public ResponseEntity<?> listar(){
		List<Contacto> cont = service.listar();
		if(cont.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(cont);
		}
		
	}
	
	//GUARDAR---------------------------------->http://localhost:8010/api/contacto/guardar
	@PostMapping("guardar")
	public ResponseEntity<?> guardar(@RequestBody Contacto c) {

	    if (service.emailRepetido(c.getEmail())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Ya existe un contacto con el email: " + c.getEmail());
	    }

	    if (service.contactoRepetido(c.getName(), c.getApellido())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Ya existe un contacto con el nombre: "
	                        + c.getName() + " " + c.getApellido());
	    }
	    Contacto nuevo = service.guardar(c);

	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body(nuevo);
	}
		
	//EDITAR------------------------------->http://localhost:8010/api/contacto/editar/{idContacto}
	@PutMapping("editar/{idContacto}")
	public ResponseEntity<?> editar(@PathVariable Integer idContacto,
	                                @RequestBody Contacto c) {

	    Contacto encontrado = service.buscar(idContacto);

	    if (encontrado == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        encontrado.setName(c.getName());
	        encontrado.setApellido(c.getApellido());
	        encontrado.setEmail(c.getEmail());

	        Contacto actualizado = service.guardar(encontrado);

	        return ResponseEntity.ok(actualizado);
	    }
	}
		
		//ELIMINAR----------------------------------------->http://localhost:8010/api/contacto/eliminar/{idContacto}
		@DeleteMapping("eliminar/{idContacto}")
		public ResponseEntity<?> eliminar(@PathVariable Integer idContacto){
		    Contacto c = service.buscar(idContacto);
	
		    if (c == null) {
		        return ResponseEntity.notFound().build();
		    }
	
		    service.eliminar(c);
		    return ResponseEntity.noContent().build();
		}
		
		//BUSCAR----------------------------------------------->http://localhost:8010/api/contacto/buscar/{idContacto}
		@GetMapping("buscar/{idContacto}")
		public ResponseEntity<?> buscarPorId(@PathVariable Integer idContacto) {

	        Contacto encontrado = service.buscar(idContacto);

	        return (encontrado == null)
	                ? ResponseEntity.notFound().build()
	                : ResponseEntity.ok(encontrado);
	    }
		
		//BUSCAR POR NOMBRE--------------------------------->http://localhost:8010/api/contacto/buscar/{name}/{apellido}
		@GetMapping("buscar/{name}/{apellido}")
		public ResponseEntity<?> buscar(@PathVariable String name,
		                                @PathVariable String apellido) {

		    Contacto encontrado = service.porNombre(name, apellido);

		    return (encontrado == null)
		            ? ResponseEntity.notFound().build()
		            : ResponseEntity.ok(encontrado);
		}
	
	

}
