package WorkBooster.model.entitaeten;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jahresuebersicht {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int jahr;

	private double umsatz;

	private int verordnungen;

	private double herstellerabgabgepreis;

	private double apothekenpreis;

	public long getId() {
		return id;
	}
	
	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public double getUmsatz() {
		return umsatz;
	}

	public void setUmsatz(double umsatz) {
		this.umsatz = umsatz;
	}

	public int getVerordnungen() {
		return verordnungen;
	}

	public void setVerordnungen(int verordnungen) {
		this.verordnungen = verordnungen;
	}

	public double getHerstellerabgabgepreis() {
		return herstellerabgabgepreis;
	}

	public void setHerstellerabgabgepreis(double herstellerabgabgepreis) {
		this.herstellerabgabgepreis = herstellerabgabgepreis;
	}

	public double getApothekenpreis() {
		return apothekenpreis;
	}

	public void setApothekenpreis(double apothekenpreis) {
		this.apothekenpreis = apothekenpreis;
	}

	@Override
	public String toString() {
		return this.jahr + " " + this.getUmsatz() + " " + this.getVerordnungen();
	}
}
