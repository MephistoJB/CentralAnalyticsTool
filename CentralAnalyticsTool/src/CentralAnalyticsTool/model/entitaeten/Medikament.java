package CentralAnalyticsTool.model.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Medikament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String pznName;

	private String indikation;

	private String hersteller;

	private String wirkstoffgruppe;

	@OneToMany
	private List<Jahresuebersicht> jahresuebersichten;

	public Medikament() {
		this.jahresuebersichten = new ArrayList<Jahresuebersicht>();
	}

	public long getId() {
		return id;
	}

	public String getPznName() {
		return pznName;
	}

	public void setPznName(String pznName) {
		this.pznName = pznName;
	}

	public String getIndikation() {
		return indikation;
	}

	public void setIndikation(String indikation) {
		this.indikation = indikation;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public String getWirkstoffgruppe() {
		return wirkstoffgruppe;
	}

	public void setWirkstoffgruppe(String wirkstoffgruppe) {
		this.wirkstoffgruppe = wirkstoffgruppe;
	}

	public List<Jahresuebersicht> getJahresuebersichten() {
		return jahresuebersichten;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (Jahresuebersicht jUeber : jahresuebersichten) {
			buf.append(" / ");
			buf.append(jUeber.getJahr());
		}
		return this.getId() + ": " + this.getPznName()
				+ buf.toString().substring(4);
	}

	public void addJahresuebersicht(Jahresuebersicht jUeber) {
		this.jahresuebersichten.add(jUeber);
	}
}