package com.adrian.colegio.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipios")
public class MunicipioEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_municipio")
    private int idMunicipio;
    
    @Column(name = "id_provincia")
    private int idProvincia;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "cod_municipio")
    private String codMunicipio;
    
    @Column(name = "DC")
    private String dc;
    
    @OneToMany(mappedBy = "municipio")
    private List<AlumnoEntity> alumnos= new ArrayList<>();

	public MunicipioEntity(int idMunicipio, int idProvincia, String nombre, String codMunicipio, String dc) {
		super();
		this.idMunicipio = idMunicipio;
		this.idProvincia = idProvincia;
		this.nombre = nombre;
		this.codMunicipio = codMunicipio;
		this.dc = dc;
	}
	
	public MunicipioEntity() {
    }

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}
    
    
}
