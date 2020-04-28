package it.polito.tdp.poweroutages.model;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutage> getPowerOutagesByNerc(Nerc nerc) {
		return podao.getPowerOutagesByNerc(nerc);
	}

	public Insieme getWorstPowerOutagesByNerc (Nerc nerc, int maxAnni, int maxOre) {
		List<PowerOutage> tutti = podao.getPowerOutagesByNerc(nerc);
		
		if (tutti.isEmpty()) return null;
		
		List<Year> anni = new LinkedList<Year>();
		anni.add(tutti.get(0).getYear());
		for (PowerOutage po : tutti) {
			if (po.getYear().isAfter(anni.get(anni.size()-1)))
				anni.add(po.getYear());
		}
		
		Insieme best = new Insieme();
		Insieme temp;
		for (Year y : anni) {
			temp = ricerca(y, tutti, new Insieme(), maxAnni, maxOre, 0);
			if (temp.getTotPersoneCoinvolte() > best.getTotPersoneCoinvolte())
				best = temp;
		}
		
		return best;
	}
	
	Insieme ricerca(Year inizio, List<PowerOutage> daIterare, Insieme temp, int maxAnni, int maxOre, int livello) {

		//SE finisco la lista o supero il numero di anni
		if (livello == daIterare.size() || daIterare.get(livello).getYear().isAfter(inizio.plusYears((long) maxAnni-1))) {
			return new Insieme(temp);
		}
		//SE Questo livello è prima
		else if (daIterare.get(livello).getYear().isBefore(inizio)) {
			Insieme best = ricerca(inizio, daIterare, temp, maxAnni, maxOre, livello+1);
			return best;
		}
		//SE il questo livello supera il numero di ore massimo
		else if (temp.getTotOreDisservizio()+daIterare.get(livello).getOreAssenza() > maxOre) { 
			Insieme best = ricerca(inizio, daIterare, temp, maxAnni, maxOre, livello+1);
			return best;
		}

		else {
			temp.addPO(daIterare.get(livello));
			Insieme best1 = ricerca(inizio, daIterare, temp, maxAnni, maxOre, livello+1);
			temp.removePO();
			Insieme best2 = ricerca(inizio, daIterare, temp, maxAnni, maxOre, livello+1);


			if (best1.getTotPersoneCoinvolte() > best2.getTotPersoneCoinvolte())
				return best1;
			return best2;
		}
	}
	
}
