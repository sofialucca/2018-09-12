package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;

public class Nerc {
	private int id;
	private String value;
	private LocalDate dataEnergia;
	private boolean libero;
	
	public LocalDate getDataEnergia() {
		return dataEnergia;
	}

	public void setDataEnergia(LocalDate dataEnergia) {
		this.dataEnergia = dataEnergia;
	}

	public boolean isLibero() {
		return libero;
	}

	public void setLibero(boolean libero) {
		this.libero = libero;
	}

	public Nerc(int id, String value) {
		this.id = id;
		this.value = value;
		this.dataEnergia = null;
		this.libero = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nerc other = (Nerc) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		return builder.toString();
	}
}
