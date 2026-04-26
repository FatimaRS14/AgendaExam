package com.mx.Agenda.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Agenda.Dao.ITelefonoDao;
import com.mx.Agenda.Dominio.Telefono;

@Service
public class TelefonoServiceImp implements ITelefonoService {
	@Autowired
	private ITelefonoDao dao;

	@Override
	public Telefono guardar(Telefono t) {
		return dao.save(t);
	}

	@Override
	public List<Telefono> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idTelefono"));
	}

	@Override
	public Telefono buscar(Integer idTelefono) {
		return dao.findById(idTelefono).orElse(null);
	}

	@Override
	public void eliminar(Telefono t) {
		dao.delete(t);
		
	}
	
	public boolean telefonoDuplicado(String telefonoNum) {
		return dao.existsByTelefonoNum(telefonoNum);
	}

	public Telefono porNumero(String telefonoNum) {
		return dao.findByTelefonoNum(telefonoNum);
	}
	
	public List<Telefono> buscarTelefonosPorContacto(int contactoId) {
        return dao.findByContacto_IdContacto(contactoId);
    }
	
}
