package com.mx.Agenda.Dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACTO")
public class Contacto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACTO_SEQ")
	@SequenceGenerator(
	        name = "CONTACTO_SEQ",
	        sequenceName = "CONTACTO_SEQ",
	        allocationSize = 1
	)
	@Column(name = "ID_CONTACTO")
	private Integer idContacto;
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
   // private Integer idContacto;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "APELLIDO", nullable = false, length = 200)
    private String apellido;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "FECHA_CREACION", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "contacto", cascade = CascadeType.ALL)
    private List<Telefono> lista = new ArrayList<>();

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	@Override
	public String toString() {
		return "Contacto [idContacto=" + idContacto + ", name=" + name + ", apellido=" + apellido + ", email=" + email
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}

	public Contacto(Integer idContacto, String name, String apellido, String email, LocalDateTime fechaCreacion) {
		this.idContacto = idContacto;
		this.name = name;
		this.apellido = apellido;
		this.email = email;
		this.fechaCreacion = fechaCreacion;
		
	}

	public Contacto() {
		
	}
    
    
    
}