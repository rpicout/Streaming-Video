package model;

import java.util.ArrayList;

public class EndpointInfo {
	
	private int numero;
	private int dataCenterLatency;
	private int nbCacheConnected;
	private ArrayList<CacheToEndpoint> cache = new ArrayList<CacheToEndpoint>();
	private ArrayList<VideoRequest> video = new ArrayList<VideoRequest>();
	
	public EndpointInfo() {
		
	}
	/*
	 * Constructeur
	 */
	public EndpointInfo(int numero, int dataCenterLatency, int nbCacheConnected, ArrayList<CacheToEndpoint> cache, 
			ArrayList<VideoRequest> video) {
		this.numero = numero;
		this.dataCenterLatency = dataCenterLatency;
		this.nbCacheConnected = nbCacheConnected;
		this.cache = cache;
		this.video = video;
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


	public int getDataCenterLatency() {
		return dataCenterLatency;
	}

	public void setDataCenterLatency(int dataCenterLatency) {
		this.dataCenterLatency = dataCenterLatency;
	}


	public int getNbCacheConnected() {
		return nbCacheConnected;
	}

	public void setNbCacheConnected(int nbCacheConnected) {
		this.nbCacheConnected = nbCacheConnected;
	}

	
	public ArrayList<CacheToEndpoint> getCache() {
		return cache;
	}
	
	public CacheToEndpoint getCache(int i) {
		return cache.get(i);
	}

	public void setCache(ArrayList<CacheToEndpoint> cache) {
		this.cache = cache;
	}
	
	public void addCache(int i, CacheToEndpoint cache) {
		this.cache.add(i, cache);
	}

	
	public ArrayList<VideoRequest> getVideo() {
		return video;
	}
	
	public VideoRequest getVideo(int i) {
		return video.get(i);
	}

	public void setVideo(ArrayList<VideoRequest> video) {
		this.video = video;
	}

	public void addVideo(int i, VideoRequest video) {
		this.video.add(i, video);
	}

}
