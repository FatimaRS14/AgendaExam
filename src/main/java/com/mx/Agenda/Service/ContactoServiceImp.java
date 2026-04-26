package com.mx.Agenda.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Agenda.Dao.IContactoDao;
import com.mx.Agenda.Dominio.Contacto;

@Service
public class ContactoServiceImp implements IContactoService {
	@Autowired
	private IContactoDao dao;
	
	@Override
	public Contacto guardar(Contacto c) {
		return dao.save(c);
	}

	@Override
	public List<Contacto> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idContacto"));
	}

	@Override
	public Contacto buscar(Integer idContacto) {
		return dao.findById(idContacto).orElse(null);
	}

	@Override
	public void eliminar(Contacto c) {
		dao.delete(c);
		
	}
	
	public boolean emailRepetido(String email) {
		return dao.existsByEmail(email);
	}
	
	public boolean contactoRepetido(String name, String apellido) {
		return dao.existsByNameAndApellido(name, apellido);
	}
	
	public Contacto porNombre(String name, String apellido) {
		return dao.findByNameAndApellido(name, apellido);
	}
	
	
	

}
