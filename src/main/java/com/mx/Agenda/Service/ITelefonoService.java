package com.mx.Agenda.Service;

import java.util.List;

import com.mx.Agenda.Dominio.Telefono;

public interface ITelefonoService {
	
	Telefono guardar (Telefono t);
	List<Telefono> listar();
	Telefono buscar(Integer idTelefono);
	void eliminar(Telefono t);
	boolean telefonoDuplicado(String telefonoNum);
	Telefono porNumero(String telefonoNum);
	List<Telefono> buscarTelefonosPorContacto(int contactoId);
}
