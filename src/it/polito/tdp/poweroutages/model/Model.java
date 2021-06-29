package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.poweroutages.db.PowerOutagesDAO;
import it.polito.tdp.poweroutages.model.Evento.EventType;

public class Model {

	private Graph<Nerc,DefaultWeightedEdge> grafo;
	private Map<Integer,Nerc> idMap;
	private PowerOutagesDAO dao;
	private Map<Nerc,List<NercNumero>> mappaVicini;
	
	private PriorityQueue<Evento> queue;
	private int CATASTROFE;
	private Map<Nerc,Integer> listaBonus;
	private Map<Nerc, List<NercData>> mappaAiuti;
	private int kRif;
	
	
	public Model() {
		dao = new PowerOutagesDAO();
		idMap = new HashMap<>();
		dao.loadAllNercs(idMap);
		mappaVicini = new HashMap<>();
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, idMap.values());
		
		
		for(Adiacenti a : dao.getArchi(idMap)) {
			Nerc n1 = a.getN1();
			Nerc n2 = a.getN2();
			Graphs.addEdge(grafo,n1, n2, a.getPeso());
			List<NercNumero> listaN1 = mappaVicini.get(n1);
			List<NercNumero> listaN2 = mappaVicini.get(n2);
			
			if(listaN1 == null) {
				listaN1 = new ArrayList<>();
			}
			listaN1.add(new NercNumero(n2, a.getPeso()));
			if( listaN2 == null) {
				listaN2 = new ArrayList<>();
			}
			listaN2.add(new NercNumero(n1, a.getPeso()));
			mappaVicini.put(n1, listaN1);
			mappaVicini.put(n2,listaN2);
		}		
	}
	
	public void creaGrafo() {
		
		System.out.println("GRAFO CREATO");
		System.out.println("#vertici: " + grafo.vertexSet().size());
		System.out.println("#archi: " + grafo.edgeSet().size());
		
	}
	
	public List<NercNumero> getVicini(Nerc nerc){
		List<NercNumero> result = mappaVicini.get(nerc);
		Collections.sort(result);
		return result;
	}
	
	public List<Nerc> getVertici(){
		return new ArrayList<>(grafo.vertexSet());
	}
	
	public void init(int k ) {
		this.CATASTROFE = 0;
		this.listaBonus = new HashMap<>();
		this.queue = new PriorityQueue<>();
		this.mappaAiuti = new HashMap<>();
		kRif = k;
		
		for(Nerc n: this.getVertici()) {
			listaBonus.put(n,0);
			List<NercData> result = new ArrayList<>();
			for(Nerc n2: Graphs.neighborListOf(grafo, n)) {
				result.add(new NercData(n2,null));
			}
			this.mappaAiuti.put(n, result);	
	
		}
		
		for(InterruzioneCorrente ic: dao.getInterruzioni(idMap)) {
			Nerc n = ic.getNerc();
			queue.add(new Evento(ic.getData_inizio(),n,EventType.INIZIO,ic.getData_fine()));
		}
	}
	
	public void run() {
		Evento e;
		while((e =queue.poll())!= null) {
			Nerc n = e.getNerc();
			LocalDate data = n.getDataEnergia();
			switch(e.getTipo()) {
				case INIZIO:
					List<Nerc> possibileAiuto = new ArrayList<>();
					Nerc nuovoNerc = null;
					List<NercNumero>listaVicini = this.getVicini(n);
					if(!this.mappaAiuti.get(n).isEmpty()) {

						for(NercData nVicino : this.mappaAiuti.get(n)) {
							if(nVicino.getData() != null && nVicino.getData().plusMonths(kRif).isAfter(data) && nVicino.getN().isLibero()) {
								possibileAiuto.add(nVicino.getN());
								
							}
						}
						if(possibileAiuto.isEmpty()) {
	
							for(int i = listaVicini.size()-1; i > -1 ; i--) {
								if(listaVicini.get(i).getN().isLibero()) {
									nuovoNerc = listaVicini.get(i).getN();
									break;
								}
							}
						}else if(possibileAiuto.size() > 1) {
							for(int i = listaVicini.size()-1; i > -1 ; i--) {
								Nerc nAiuto = listaVicini.get(i).getN();
								if(nAiuto.isLibero() && possibileAiuto.contains(nAiuto)) {
									nuovoNerc = nAiuto;
								}
							}
						}else {
							nuovoNerc = possibileAiuto.get(0);
						}
						
						if(nuovoNerc != null) {
							queue.add(new Evento(e.getSecondaData(), nuovoNerc, EventType.FINE, null));
							nuovoNerc.setLibero(false);
							this.mappaAiuti.get(n).remove(nuovoNerc);
							this.mappaAiuti.get(n).add(new NercData(nuovoNerc, data));
							break;
						}						
					}
					this.CATASTROFE++;
					break;
				case FINE:
					this.listaBonus.put(n, listaBonus.get(n) +1);
					n.setLibero(true);
					break;
			}
		}
	}
	
	public int getCtastrofi() {
		return this.CATASTROFE;
	}
	
	public Map<Nerc,Integer> getBonus(){
		return this.listaBonus;
	}
}
