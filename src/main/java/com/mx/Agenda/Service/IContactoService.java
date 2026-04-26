package com.mx.Agenda.Service;

import java.util.List;

import com.mx.Agenda.Dominio.Contacto;

public interface IContactoService {


	Contacto guardar (Contacto c);
	List<Contacto> listar();
	Contacto buscar(Integer idContacto);
	void eliminar(Contacto c);
}
