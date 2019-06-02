package it.polito.tdp.bar.model;

public class Tavolo {


	static private int totaleTavoli = 0;
	private int idClienti;
	private int idTavolo;
	private int numPostiASedere;
	private boolean libero;

	//per fare un tavolo ci vuole il numero di posti (e il legno)
	
	public Tavolo (int numPostiASedere) {
		this.libero = true;
		this.numPostiASedere = numPostiASedere;
		idTavolo = ++totaleTavoli;
		//inzialmente viene assegnato un idClienti associato sicuramente a nessun cliente
		idClienti = -1;
	}

	public int getIdClienti() {
		return idClienti;
	}

	public void setIdClienti(int idClienti) {
		this.idClienti = idClienti;
	}

	public int getIdTavolo() {
		return idTavolo;
	}

	public void setIdTavolo(int idTavolo) {
		this.idTavolo = idTavolo;
	}

	public int getNumPostiASedere() {
		return numPostiASedere;
	}

	public void setNumPostiASedere(int numPostiASedere) {
		this.numPostiASedere = numPostiASedere;
	}

	public boolean isLibero() {
		return libero;
	}

	public void setLibero(boolean libero) {
		this.libero = libero;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTavolo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		if (idTavolo != other.idTavolo)
			return false;
		return true;
	}
}
