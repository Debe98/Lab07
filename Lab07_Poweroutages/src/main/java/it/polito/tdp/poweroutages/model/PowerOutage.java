package it.polito.tdp.poweroutages.model;
import java.sql.Timestamp;
import java.time.*;

public class PowerOutage {
	
	private int id;
	private Nerc nerc;
	private int customersAffected;
	private Year year;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private long oreAssenza;
	
	public PowerOutage(int id, Nerc nerc, int customersAffected, Year year, Timestamp inizio, Timestamp fine) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.customersAffected = customersAffected;
		this.year = year;
		dateEventBegan = inizio.toLocalDateTime();
		dateEventFinished = fine.toLocalDateTime();
		oreAssenza = Duration.between(dateEventBegan, dateEventFinished).toHours();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public long getOreAssenza() {
		return oreAssenza;
	}

	public void setOreAssenza(long oreAssenza) {
		this.oreAssenza = oreAssenza;
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
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + ": " + customersAffected + " ("+year+")";
	}
	
	
	
	
}
