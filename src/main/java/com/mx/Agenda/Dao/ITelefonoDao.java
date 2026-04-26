package com.mx.Agenda.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Agenda.Dominio.Telefono;

public interface ITelefonoDao extends JpaRepository<Telefono, Integer>{

	boolean existsByTelefonoNum(String telefonoNum);
	List<Telefono> findByContacto_IdContacto(int contactoId);
	
	Telefono findByTelefonoNum (String TelefonoNum);
}
