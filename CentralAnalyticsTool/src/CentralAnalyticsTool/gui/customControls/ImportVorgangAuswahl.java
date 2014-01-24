package CentralAnalyticsTool.gui.customControls;

public enum ImportVorgangAuswahl {
	Jahresuebersicht, Versichertenuebersicht;

	@Override
	public String toString() {
		switch(this){
		case Jahresuebersicht: return "Jahresübersicht";
		case Versichertenuebersicht: return "Versichtertenübersicht";
		default: return super.toString(); 
		}
	}
}
