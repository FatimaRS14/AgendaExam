package com.mx.Agenda.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Agenda.Dominio.Contacto;

public interface IContactoDao extends JpaRepository<Contacto, Integer> {

    boolean existsByNameAndApellido(String name, String apellido);

    boolean existsByEmail(String email);

    Contacto findByNameAndApellido(String name, String apellido);
}
