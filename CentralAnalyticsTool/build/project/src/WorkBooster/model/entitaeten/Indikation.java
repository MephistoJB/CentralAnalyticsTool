package WorkBooster.model.entitaeten;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Indikation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String ersterIndikationsschluesselteil;
	
	private String zweiterIndikationsschluesselteil;
	
	private String Indikationsname;

	public String getErsterIndikationsschluesselteil() {
		return ersterIndikationsschluesselteil;
	}

	public void setErsterIndikationsschluesselteil(
			String ersterIndikationsschluesselteil) {
		this.ersterIndikationsschluesselteil = ersterIndikationsschluesselteil;
	}

	public String getZweiterIndikationsschluesselteil() {
		return zweiterIndikationsschluesselteil;
	}

	public void setZweiterIndikationsschluesselteil(
			String zweiterIndikationsschluesselteil) {
		this.zweiterIndikationsschluesselteil = zweiterIndikationsschluesselteil;
	}

	public String getIndikationsname() {
		return Indikationsname;
	}

	public void setIndikationsname(String indikationsname) {
		Indikationsname = indikationsname;
	}

	public long getId() {
		return id;
	}
	
}