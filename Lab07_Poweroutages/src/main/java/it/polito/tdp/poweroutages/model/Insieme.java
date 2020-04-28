package it.polito.tdp.poweroutages.model;

import java.time.Year;
import java.util.LinkedList;
import java.util.List;

public class Insieme {

	private List<PowerOutage> powerOutages;
	private int totPersoneCoinvolte;
	private long totOreDisservizio;
	private Year annoInizio;
	
	public Insieme() {
		super();
		this.powerOutages = new LinkedList<PowerOutage>();
		this.totPersoneCoinvolte = 0;
		this.totOreDisservizio = 0;
		this.annoInizio = null;
	}
	
	public Insieme(Insieme i) {
		this.powerOutages = new LinkedList<PowerOutage>(i.powerOutages);
		this.totPersoneCoinvolte = i.totPersoneCoinvolte;
		this.totOreDisservizio = i.totOreDisservizio;
		this.annoInizio = i.annoInizio;
	}

	public List<PowerOutage> getPowerOutages() {
		return powerOutages;
	}

	public int getTotPersoneCoinvolte() {
		return totPersoneCoinvolte;
	}

	public long getTotOreDisservizio() {
		return totOreDisservizio;
	}
	
	public Year getAnnoInizio() {
		return annoInizio;
	}

	public void addPO(PowerOutage po) {
		powerOutages.add(po);
		totOreDisservizio += po.getOreAssenza();
		totPersoneCoinvolte += po.getCustomersAffected();
		if (annoInizio == null)
			annoInizio = powerOutages.get(0).getYear();
	}
	
	public void removePO() {
		PowerOutage po = powerOutages.remove(powerOutages.size()-1);
		totOreDisservizio -= po.getOreAssenza();
		totPersoneCoinvolte -= po.getCustomersAffected();
		if (powerOutages.isEmpty())
			annoInizio = null;
	}
	
	public void copyInsieme (Insieme i) {
		this.powerOutages = new LinkedList<PowerOutage>(i.powerOutages);
		this.totPersoneCoinvolte = i.totPersoneCoinvolte;
		this.totOreDisservizio = i.totOreDisservizio;
		this.annoInizio = i.annoInizio;
	}

	@Override
	public String toString() {
		return totPersoneCoinvolte + " (" + totOreDisservizio + ") - " + powerOutages;
	}
	
	
	
}
