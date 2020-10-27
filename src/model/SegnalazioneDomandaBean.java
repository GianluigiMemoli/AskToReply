package model;

public class SegnalazioneDomandaBean extends SegnalazioneBean {

	public DomandaBean getDomandaSegnalata() {
		return domandaSegnalata;
	}

	public void setDomandaSegnalata(DomandaBean domandaSegnalata) {
		this.domandaSegnalata = domandaSegnalata;
	}

	@Override
	public String toString() {
		
		String s = super.toString() + "\n";
		
		s += "SegnalazioneDomandaBean [";
		
		s += "idDomandaSegnalata = " + domandaSegnalata.getId();
		
		s += "]";
		
		return s;
		
	}
	
	private DomandaBean domandaSegnalata;
}
