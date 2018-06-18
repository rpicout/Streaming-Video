package model;

public class LatenceToCacheServer {
	
	private int cache;
	private int latency;
	
	/*
	 * Constructeur
	 */
	public LatenceToCacheServer(int numeroCacheServer, int latency) {
		this.cache = numeroCacheServer;
		this.latency = latency;
	}


	/*
	 * Getters / Setters
	 */
	public int getCache() {
		return cache;
	}

	public void setCache(int cache) {
		this.cache = cache;
	}


	public int getLatency() {
		return latency;
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}
	
	

}
