package com.boaglio.idwar.uuid;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "CHAVE_COM_UUID")
public class ChaveComUUID {

	@Id
	private String id;

	private String cliente;
	
	private LocalDateTime tempo;

	public ChaveComUUID() {}
	
	public ChaveComUUID(String id, String cliente, LocalDateTime tempo) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.tempo = tempo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		ChaveComUUID other = (ChaveComUUID) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(id, other.id)
				&& Objects.equals(tempo, other.tempo);
	}

	@Override
	public String toString() {
		return "ChaveComUUID [id=" + id + ", cliente=" + cliente + ", tempo=" + tempo + "]";
	}
	
}
