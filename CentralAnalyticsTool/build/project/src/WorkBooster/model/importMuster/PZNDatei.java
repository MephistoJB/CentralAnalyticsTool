package WorkBooster.model.importMuster;

import java.util.ArrayList;

public class PZNDatei {

	private int id;
	
	private String dateiname;
	
	private int jahr;
	
	private ArrayList<PZNDateiEintrag> eintraege;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateiname() {
		return dateiname;
	}

	public void setDateiname(String dateiname) {
		this.dateiname = dateiname;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public ArrayList<PZNDateiEintrag> getEintraege() {
		return eintraege;
	}

	public void setEintraege(ArrayList<PZNDateiEintrag> eintraege) {
		this.eintraege = eintraege;
	}
	
	
}
