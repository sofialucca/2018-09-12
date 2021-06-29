package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;

public class InterruzioneCorrente {

	private LocalDate data_inizio;
	private LocalDate data_fine;
	private Nerc nerc;
	
	
	public InterruzioneCorrente(LocalDate data_inizio, LocalDate data_fine, Nerc nerc) {
		super();
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.nerc = nerc;
	}


	public LocalDate getData_inizio() {
		return data_inizio;
	}


	public void setData_inizio(LocalDate data_inizio) {
		this.data_inizio = data_inizio;
	}


	public LocalDate getData_fine() {
		return data_fine;
	}


	public void setData_fine(LocalDate data_fine) {
		this.data_fine = data_fine;
	}


	public Nerc getNerc() {
		return nerc;
	}


	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	
	
	
	
}
