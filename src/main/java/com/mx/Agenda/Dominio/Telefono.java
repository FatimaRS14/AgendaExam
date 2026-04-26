package com.mx.Agenda.Dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TELEFONO")
public class Telefono {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TELEFONO_SEQ")
	@SequenceGenerator(
	        name = "TELEFONO_SEQ",
	        sequenceName = "TELEFONO_SEQ",
	        allocationSize = 1
	)
	@Column(name = "ID_TELEFONO")
	private Integer idTelefono;

    @Column(name = "TELEFONO_NUM", nullable = false, length = 25)
    private String telefonoNum;

    @Column(name = "TIPO", nullable = false, length = 20)
    private String tipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTACTO_ID", nullable = false)
    private Contacto contacto;

    @Column(name = "FECHA_CREACION", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    

	@Override
	public String toString() {
		return "Telefono [idTelefono=" + idTelefono + ", telefonoNum=" + telefonoNum + ", tipo=" + tipo + ", contacto="
				+ contacto + ", fechaCreacion=" + fechaCreacion + "]";
	}

	public Integer getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(Integer idTelefono) {
		this.idTelefono = idTelefono;
	}

	public String getTelefonoNum() {
		return telefonoNum;
	}

	public void setTelefonoNum(String telefonoNum) {
		this.telefonoNum = telefonoNum;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Telefono() {
		
	}

	public Telefono(Integer idTelefono, String telefonoNum, String tipo, Contacto contacto, LocalDateTime fechaCreacion) {
		this.idTelefono = idTelefono;
		this.telefonoNum = telefonoNum;
		this.tipo = tipo;
		this.contacto = contacto;
		this.fechaCreacion = fechaCreacion;
	}
	
	
    
	
    
    
}