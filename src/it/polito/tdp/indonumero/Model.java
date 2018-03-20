package it.polito.tdp.indonumero;

import java.security.*;

public class Model {
	
	private int NMAX=100;
	private int TMAX=7;
	
	private int segreto; // num da indovinare
	private int tentativi; // tentativi gia fatti
	private boolean inGame; //mi dice se c'è una partita in corso
	
	
	public Model(){
		inGame=false;
	}
	
	/**
	 * avvia una nuova partita, generando un nuovo num segreto
	 */
	public void newGame() {
		this.segreto=(int)(Math.random()*NMAX)+1;
    	this.tentativi=0;
    	this.inGame=true;
    	
	}
	
	/**
	 * Fai un tentativo per indovinare il num segreto
	 * @param t 
	 * @return 0 se è corretto, +1 se è troppo grande, -1 se è troppo piccolo
	 */
	public int tentativo(int t) {
		if (!inGame)
			throw new IllegalStateException("partita non attiva\n");
		if(!valoreValido(t))
			throw new InvalidParameterException("tentativo fuori range\n");
		
		tentativi++;
		if(tentativi==TMAX) {
			inGame=false;		//partita finita
		}
		
		if(t==segreto) {
			inGame=false;		//partita finita
			return 0;
		}
		if(t<segreto)
			return -1;
		return +1;
	}

	public int getSegreto() {
		return segreto;
	}

	public boolean isInGame() {
		return inGame;
	}
	
	public int getTentativi() {
		return this.tentativi;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	/**
	 * controlla se il tentativo fornito rispetta le regole formali del gioco, cioè è nel range da 1 a NMAX
	 * @param tentativo
	 * @return {@code true} se è valido
	 */
	public boolean valoreValido(int tentativo) {
		return tentativo>=1 && tentativo<=NMAX;
	}
	
}
