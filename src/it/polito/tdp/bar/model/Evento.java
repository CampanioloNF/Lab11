package it.polito.tdp.bar.model;

                                                  
public class Evento implements Comparable<Evento>{
                    // implemento comparable per la PriorityQueue
	
	
	public enum TipoEvento{
		
		ARRIVO_GRUPPO_CLIENTI,
		USCITA_GRUPPO_CLIENTI
		
	}
		//scandisce l'arrivo dei clienti (meglio long che int)
	private long index ;
	private TipoEvento tipo ;
	
	   //in pratica il gruppo di clienti caratterizza l'evento
	private GruppoClienti clienti;

	
	/*
	 * Un evento è caratterizzato da:
	 *  - Un numero che ne determina la sequenza
	 *  - Un tipo ARRIVO / USCITA
	 *  - Un gruppo di clienti (quanti sono? Quale è la loro tolleranza?)
	 */
    
	public Evento(long tempoArrivo, TipoEvento tipo, GruppoClienti clienti) {
		super();
		this.index = tempoArrivo;
		this.tipo = tipo;
		this.clienti = clienti;
	}


	public long getIndex() {
		return index;
	}


	public TipoEvento getTipo() {
		return tipo;
	}


	public GruppoClienti getClienti() {
		return clienti;
	}


	//interessa solo la marcatura temporale
	@Override
	public int compareTo(Evento other) {
		return Long.compare(this.index, other.index);
	}
	
	public String toString() {
		return index +" "+tipo;
	}
}	
