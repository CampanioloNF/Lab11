package it.polito.tdp.bar.model;

public class Stats {


	private int numero_totale_clienti;
	private int numero_clienti_soddisfatti;
	private int numero_clienti_insoddisfatti;
	private int numero_totale_gruppi = 0;
	private int numTavoli = 0;
	private int gruppi_soddisfatti = 0;
	private int gruppi_insoddisfatti = 0;

	public void aggiungiClienti(GruppoClienti clienti) {

		numero_totale_gruppi++;
		numero_totale_clienti += clienti.getNum_persone();

		
		//chiaramente si deve tenere traccia di quante persone formino un gruppo
		// dal momento che uno stesso numero di gruppi insodisfatti può avere un numero di persone insodisfatte diverso
		
		if (clienti.isSoddisfatti()) {
			numero_clienti_soddisfatti += clienti.getNum_persone();
			gruppi_soddisfatti++;

		} else {
			numero_clienti_insoddisfatti += clienti.getNum_persone();
			gruppi_insoddisfatti++;
		}

	}

	public String stampale() {

		//Semplice metodo per stampare le statistiche
		
		String ris = "Numero totale Gruppi:  " + this.numero_totale_gruppi + "\n";
		ris += "Numero totale Gruppi Soddisfatti:  " + this.gruppi_soddisfatti + "\n";
		ris += "Numero totale Gruppi Insoddisfatti:  " + this.gruppi_insoddisfatti + "\n";
		ris += "Numero totale Clienti:  " + this.numero_totale_clienti + "\n";
		ris += "Numero totale Clienti Soddisfatti:  " + this.numero_clienti_soddisfatti + "\n";
		ris += "Numero totale Clienti Insoddisfatti:  " + this.numero_clienti_insoddisfatti + "\n";
		ris += "Numero tavoli con cui lavora il simulatore:  " + this.numTavoli + "\n\n\n";

		return ris;
	}

	//Posso modificare il numero di tavoli
	
	public void setNumTavoli(int numero) {
		numTavoli = numero;
	}

	//Serve per la pulizia di tutte le statistiche
	
	public void cleanup() {
		this.numero_totale_gruppi = 0;
		this.gruppi_soddisfatti = 0;
		this.gruppi_insoddisfatti = 0;
		
		this.numero_totale_clienti = 0; 
		this.numero_clienti_soddisfatti = 0;
		this.numero_clienti_insoddisfatti = 0;
	}

	@Override
	public String toString() {

		String ris = "Numero totale Gruppi:  " + this.numero_totale_gruppi + "\n";
		ris += "Numero totale Gruppi Soddisfatti:  " + this.gruppi_soddisfatti + "\n";
		ris += "Numero totale Gruppi Insoddisfatti:  " + this.gruppi_insoddisfatti + "\n";
		ris += "Numero totale Clienti:  " + this.numero_totale_clienti + "\n";
		ris += "Numero totale Clienti Soddisfatti:  " + this.numero_clienti_soddisfatti + "\n";
		ris += "Numero totale Clienti Insoddisfatti:  " + this.numero_clienti_insoddisfatti + "\n";
		ris += "Numero tavoli con cui lavora il simulatore:  " + this.numTavoli + "\n\n\n";

		return ris;
	}
	
}
