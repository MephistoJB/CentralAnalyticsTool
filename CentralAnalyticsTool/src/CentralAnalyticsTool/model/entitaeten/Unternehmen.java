package CentralAnalyticsTool.model.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Unternehmen {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	private Adresse adresse;

	@OneToMany(mappedBy = "unternehmen")
	private List<Ansprechpartner> ansprechpartner;

	@OneToOne
	private Indikation ersterSchwerpunkt;

	@OneToOne
	private Indikation zweiterSchwerpunkt;

	@OneToOne
	private Indikation dritterSchwerpunkt;

	public Unternehmen() {
		this.ansprechpartner = new ArrayList<Ansprechpartner>();
	}

	public long getId() {
		return id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Ansprechpartner> getAnsprechpartner() {
		return ansprechpartner;
	}

	public void addAnsprechpartner(Ansprechpartner ansprech) {
		this.ansprechpartner.add(ansprech);
	}

	public Indikation getErsterSchwerpunkt() {
		return ersterSchwerpunkt;
	}

	public void setErsterSchwerpunkt(Indikation ersterSchwerpunkt) {
		this.ersterSchwerpunkt = ersterSchwerpunkt;
	}

	public Indikation getZweiterSchwerpunkt() {
		return zweiterSchwerpunkt;
	}

	public void setZweiterSchwerpunkt(Indikation zweiterSchwerpunkt) {
		this.zweiterSchwerpunkt = zweiterSchwerpunkt;
	}

	public Indikation getDritterSchwerpunkt() {
		return dritterSchwerpunkt;
	}

	public void setDritterSchwerpunkt(Indikation dritterSchwerpunkt) {
		this.dritterSchwerpunkt = dritterSchwerpunkt;
	}

}
