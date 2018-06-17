package model;

public class VideoRequest {

	private int numero;
	private int requests;
	
	/*
	 * Constructeur
	 */
	public VideoRequest(int numero, int requests) {
		this.numero = numero;
		this.requests = requests;
	}

	
	/*
	 * Getters / Setters 
	 */
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}
	
	
}
