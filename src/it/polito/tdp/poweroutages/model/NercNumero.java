package it.polito.tdp.poweroutages.model;

public class NercNumero implements Comparable<NercNumero>{

	private Nerc n;
	private Double num;
	
	public NercNumero(Nerc n, double num) {
		super();
		this.n = n;
		this.num = num;
	}

	public Nerc getN() {
		return n;
	}

	public void setN(Nerc n) {
		this.n = n;
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return n + "---" + num ;
	}

	@Override
	public int compareTo(NercNumero o) {
		// TODO Auto-generated method stub
		return o.num.compareTo(this.num);
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
		NercNumero other = (NercNumero) obj;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}
	
	
}
