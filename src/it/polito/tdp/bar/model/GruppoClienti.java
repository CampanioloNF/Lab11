package it.polito.tdp.bar.model;

public class GruppoClienti {

	

	static private int idGruppoClienti = 0;
	private int id;
	private long index;
	private long durata;
	private float tolleranza;
	private int num_persone;
	private boolean soddisfatti;
	
	private Tavolo tavolo;

	//per la creazione di un GruppoClienti è necessaria la conoscenza delle seguenti cose 
	// durata e tolleranza vengono ricavati attraverso l'uso di un oggetto Random
	
	public GruppoClienti(long index, long durata, float tolleranza, int num_persone) {
		this.id = ++idGruppoClienti;
		this.index = index;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.num_persone = num_persone;
		this.soddisfatti = false;
		
		this.tavolo = null;
	}
	
	@Override
	public String toString() {
		return "Customer#" + id;
	}

	//Serie di getter e setter
	
	public Tavolo getTavolo() {
		return tavolo;
	}
	
	public void setTavolo(Tavolo tavolo) {
		this.tavolo=tavolo;
	}
	
	public static int getIdGruppoClienti() {
		return idGruppoClienti;
	}

	public static void setIdGruppoClienti(int idGruppoClienti) {
		GruppoClienti.idGruppoClienti = idGruppoClienti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public long getDurata() {
		return durata;
	}

	public void setDurata(long durata) {
		this.durata = durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	public int getNum_persone() {
		return num_persone;
	}

	public boolean isSoddisfatti() {
		return soddisfatti;
	}

	public void setSoddisfatti(boolean soddisfatti) {
		this.soddisfatti = soddisfatti;
	}

	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		GruppoClienti other = (GruppoClienti) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

