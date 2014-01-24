package CentralAnalyticsTool.gui.customControls;

public enum AufgabenTabAuswahl {
	Verhandlung, Planung, Import;

	@Override
	public String toString() {
		switch(this){
		case Verhandlung: return "Neue Verhandlung";
		case Planung: return "Neue Planung";
		case Import: return "Neuer Import";
		default: return super.toString(); 
		}
	}
}
