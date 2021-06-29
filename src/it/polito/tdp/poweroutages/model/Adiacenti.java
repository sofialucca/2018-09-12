package it.polito.tdp.poweroutages.model;

public class Adiacenti implements Comparable<Adiacenti>{

	private Nerc n1;
	private Nerc n2;
	private Double peso;

	public Adiacenti(Nerc n1, Nerc n2, double peso) {
		super();
		this.n1 = n1;
		this.n2 = n2;
		this.peso = peso;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Nerc getN1() {
		return n1;
	}

	public void setN1(Nerc n1) {
		this.n1 = n1;
	}

	public Nerc getN2() {
		return n2;
	}

	public void setN2(Nerc n2) {
		this.n2 = n2;
	}

	@Override
	public int compareTo(Adiacenti o) {
		// TODO Auto-generated method stub
		return o.peso.compareTo(this.peso);
	}

	@Override
	public String toString() {
		return n1 + "---" + n2 + " (" + peso + ")";
	}
	
	
}
