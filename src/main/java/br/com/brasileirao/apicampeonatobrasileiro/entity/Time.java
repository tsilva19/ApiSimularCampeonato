package br.com.brasileirao.apicampeonatobrasileiro.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String nome;
    @Column(length = 4)
    private String sigla;
    @Column(length = 2)
    private String uf;
    private String estadio;
    public void setNome(String nome) {
		// TODO Auto-generated method stub
		this.nome = nome;
	}
    
    public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}
	public void setUf(String uf) {
		// TODO Auto-generated method stub
		this.uf =uf;
	}
	public String getEstadio() {
		// TODO Auto-generated method stub
		return estadio;
	}
	public void setEstadio(String estadio) {
		// TODO Auto-generated method stub
		this.estadio = estadio;
	}
	public void setSigla(String sigla) {
		// TODO Auto-generated method stub
		this.sigla = sigla;
	}
	public String getSigla() {
		// TODO Auto-generated method stub
		return sigla;
	}
	
	public String getUf() {
		// TODO Auto-generated method stub
		return uf;
	}
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	
}

