package WorkBooster.model.entitaeten;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VERSICHERTER")
public class Versicherter extends Person {

	private String versichertennummer;

	private String zusatznummer;

	public String getVersichertennummer() {
		return versichertennummer;
	}

	public void setVersichertennummer(String versichertennummer) {
		this.versichertennummer = versichertennummer;
	}

	public String getZusatznummer() {
		return zusatznummer;
	}

	public void setZusatznummer(String zusatznummer) {
		this.zusatznummer = zusatznummer;
	}

}
