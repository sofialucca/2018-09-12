package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		m.creaGrafo();
		List<Nerc> vertici = m.getVertici();
		
		System.out.println(vertici);
		System.out.println(m.getVicini(vertici.get(1)));
		
		m.init(4);
		m.run();
		
		System.out.println(m.getCtastrofi());
		System.out.println(m.getBonus());
		
	}

}
