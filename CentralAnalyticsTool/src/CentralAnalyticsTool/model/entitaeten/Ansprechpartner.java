package CentralAnalyticsTool.model.entitaeten;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANSPRECHPARTNER")
public class Ansprechpartner extends Person {

	private String position;

	@ManyToOne()
	private Unternehmen unternehmen;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Unternehmen getUnternehmen() {
		return unternehmen;
	}

	public void setUnternehmen(Unternehmen unternehmen) {
		this.unternehmen = unternehmen;
	}

}
