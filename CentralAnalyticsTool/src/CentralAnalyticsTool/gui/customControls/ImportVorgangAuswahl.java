package CentralAnalyticsTool.gui.customControls;

public enum ImportVorgangAuswahl {
	Jahresuebersicht, Versichertenuebersicht;

	@Override
	public String toString() {
		switch(this){
		case Jahresuebersicht: return "Jahres�bersicht";
		case Versichertenuebersicht: return "Versichterten�bersicht";
		default: return super.toString(); 
		}
	}
}
