package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;

public class NercData {

	private Nerc n;
	private LocalDate data;
	
	
	public NercData(Nerc n, LocalDate data) {
		super();
		this.n = n;
		this.data = data;
	}


	public Nerc getN() {
		return n;
	}


	public void setN(Nerc n) {
		this.n = n;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((n == null) ? 0 : n.hashCode());
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
		NercData other = (NercData) obj;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}
	
}
