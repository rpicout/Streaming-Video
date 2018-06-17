package model;

public class CacheToEndpoint {

	private int numero;
	private int latency;
	
	/*
	 * Constructeur
	 */
	public CacheToEndpoint(int numero, int latency) {
		this.numero = numero;
		this.latency = latency;
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
	

	public int getLatency() {
		return latency;
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}
	
}
