package com.boaglio.idwar.idnumerico;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "CHAVE_COM_NUMBER")
public class ChaveComNumber {

	@Id
	private Long id;

	private String cliente;
	
	private LocalDateTime tempo;

	public ChaveComNumber() {}
	
	public ChaveComNumber(Long id, String cliente, LocalDateTime tempo) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.tempo = tempo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getTempo() {
		return tempo;
	}

	public void setTempo(LocalDateTime tempo) {
		this.tempo = tempo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, id, tempo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChaveComNumber other = (ChaveComNumber) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(id, other.id)
				&& Objects.equals(tempo, other.tempo);
	}

	@Override
	public String toString() {
		return "ChaveComNumber [id=" + id + ", cliente=" + cliente + ", tempo=" + tempo + "]";
	}
	
}
