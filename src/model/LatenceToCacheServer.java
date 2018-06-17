package model;

public class LatenceToCacheServer {
	
	private int numeroCacheServer;
	private int latency;
	
	/*
	 * Constructeur
	 */
	public LatenceToCacheServer(int numeroCacheServer, int latency) {
		this.numeroCacheServer = numeroCacheServer;
		this.latency = latency;
	}


	/*
	 * Getters / Setters
	 */
	public int getNumeroCacheServer() {
		return numeroCacheServer;
	}

	public void setNumeroCacheServer(int numeroCacheServer) {
		this.numeroCacheServer = numeroCacheServer;
	}


	public int getLatency() {
		return latency;
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}
	
	

}
