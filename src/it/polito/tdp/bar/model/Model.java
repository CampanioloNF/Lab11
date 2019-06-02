package it.polito.tdp.bar.model;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import it.polito.tdp.bar.model.Evento.TipoEvento;

public class Model {
	
	private static final int  INDEX_INIZIO = 0;
	private static final int  INDEX_FINE = 2000;

	private PriorityQueue<Evento> queue;
	private Stats statistiche;
	private Random rand;
	private Map<Integer, Tavolo> tavoliClienti;
	private Set<Tavolo> tavoli;
	private double fatt;
	
	
    public Model() {
		
	}
	
   	
	//Variabili interne
	 
	
	
	
	              // possibile configurazione diversa passata come parametro
	public void init(int numeroTavoliDa10Totali, int numeroTavoliDa8Totali, int numeroTavoliDa6Totali, 
			int numeroTavoliDa4Totali, double fatt) {
		

		queue = new PriorityQueue<>();
		statistiche = new Stats();
		tavoliClienti = new HashMap<>();
		tavoli = new HashSet<>();
		rand = new Random(42);
	
		//Attraverso questi for aggiungo il numero di tavoli ed il relativo numero di posti
		
		for(int i = 0; i<numeroTavoliDa10Totali; i++) 
			addTavolo(10);
		
		for(int i = 0; i<numeroTavoliDa8Totali; i++) 
			addTavolo(8);
		
		for(int i = 0; i<numeroTavoliDa6Totali; i++) 
			addTavolo(6);
		
		for(int i = 0; i<numeroTavoliDa4Totali; i++) 
			addTavolo(4);
		
		//fattore correttivo nella ricerca dei tavoli personalizzabile
		this.fatt=fatt;
		
		this.queue.clear();
		
		
		long tempoArrivo = 0;
		
		//carichiamo ora gli eventi iniziali
		// vengono caricati 2000 eventi ARRIVO_GRUPPO_CLIENTI
		
		
		for(int i = Model.INDEX_INIZIO; i<Model.INDEX_FINE; i++) {
		 
			tempoArrivo = tempoArrivo + rand.nextInt(11) + 1;
			
			//duration è un tempo casuale compreso tra 60 e 120 min
			long duration = (long) (60 + Math.random() * 60);
			
			//tollerazia è un numero compreso tra 0.0 e 1.0
			float tolleranza = rand.nextFloat();
			
			int num_pers = rand.nextInt(11)+1;
			
			GruppoClienti clienti = new GruppoClienti(tempoArrivo, duration, tolleranza, num_pers);
			
			queue.add(new Evento(tempoArrivo,TipoEvento.ARRIVO_GRUPPO_CLIENTI, clienti));
		   
		}
		System.out.println(queue.size());
		run();
		
	}
	
	
	public void run() {
		
		while(!queue.isEmpty()) {
			
			Evento ev = queue.poll();
			
			GruppoClienti clienti = ev.getClienti();
			
			switch(ev.getTipo()) {
			
			case ARRIVO_GRUPPO_CLIENTI:
			 			
				Tavolo tavolo = trovaTavolo(clienti.getNum_persone(), fatt);
				
				//se questo tavolo esiste
				if(tavolo!=null) {
					
					//sistemo i clienti nel tavolo 
					
			    	tavoliClienti.put(clienti.getId(), tavolo);
					tavolo.setLibero(false);
					clienti.setSoddisfatti(true);
					clienti.setTavolo(tavolo);
					
					//System.out.println(clienti.getTavolo());
					
					// si crea dunque un nuovo evento di uscita dal tavolo 
					
					queue.add(new Evento(ev.getIndex()+clienti.getDurata(), TipoEvento.USCITA_GRUPPO_CLIENTI, clienti));
					
				}
				
				//nel caso non ci sia un tavolo libero 
			      // o decido che non sia il caso di farli occupare un tavolo
				
				else {
					
					float tolleranza = clienti.getTolleranza();
					float probabilita = rand.nextFloat();
					
					if(tolleranza >=probabilita) 
						//i clienti sono soddisfatti anche se serviti al bancone!
						clienti.setSoddisfatti(true);
					else 
					    clienti.setSoddisfatti(false);
			
				}
				
				//alla fine mi ricordo di aggiungere il cliente alle statistiche
				//E' importante farlo dopo averne valutato la sua soddisfazione
				statistiche.aggiungiClienti(clienti);
				break;
			
				
			case USCITA_GRUPPO_CLIENTI:
			      
	              //sostanzialmente devo trovare il tavolo e segnarlo come libero
			
				 Tavolo t = tavoliClienti.get(clienti.getId());
			     t.setLibero(true);
			     tavoliClienti.remove(clienti.getId(), t);
			     break;
				
  					  
				
				 		
			}
			
		}
		
		System.out.println(statistiche.toString());
		
	}

	private void addTavolo(int numPostiASedere) {
		
		tavoli.add(new Tavolo(numPostiASedere));
		statistiche.setNumTavoli(tavoli.size());
				}

	
	private Tavolo trovaTavolo(int num_persone, double fatt) {
		
		// Devo restituire un tavolo OTTIMALE per queste persone
		
		Tavolo trovato = null;
	
		int gap = Integer.MAX_VALUE;
	
		for(Tavolo tav : tavoli) {
			
			if(tav.isLibero() && ((fatt*tav.getNumPostiASedere())<=num_persone)) {
				
					if(tav.getNumPostiASedere()-num_persone<gap) {
						gap = tav.getNumPostiASedere()-num_persone;
						trovato = tav;
					
						
				}
				
			}
		}
		
		//altrimenti null
		
		return trovato;
	}


	public String getStat() {
		return statistiche.toString();
		
	}
}
