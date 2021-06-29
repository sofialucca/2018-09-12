package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;

public class Evento implements Comparable<Evento>{

	
	enum EventType{
		INIZIO,
		FINE
	};
	
	private LocalDate data;
	private LocalDate secondaData;
	private Nerc nerc;
	private EventType tipo;
	
	public Evento(LocalDate data, Nerc nerc, EventType tipo, LocalDate secondaData) {
		super();
		this.data = data;
		this.nerc = nerc;
		this.tipo = tipo;
		this.secondaData = secondaData;
	}

	public LocalDate getSecondaData() {
		return secondaData;
	}

	public void setSecondaData(LocalDate secondaData) {
		this.secondaData = secondaData;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public EventType getTipo() {
		return tipo;
	}

	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.data.compareTo(o.data);
	}
	
	
}
