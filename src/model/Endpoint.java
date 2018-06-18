package model;

import java.util.ArrayList;

public class Endpoint {
	
	private int numero;
	private int dataCenterLatency;
	private int nbCacheConnected;
	private ArrayList<LatenceToCacheServer> latenceToCacheServer = new ArrayList<LatenceToCacheServer>();
	private ArrayList<Integer> videos = new ArrayList<Integer>();
	
	public Endpoint() {
		
	}
	/*
	 * Constructeur
	 */
	public Endpoint(int numero, int dataCenterLatency, int nbCacheConnected, ArrayList<LatenceToCacheServer> latenceToCacheServer,
			ArrayList<Integer> videos) {
		this.numero = numero;
		this.dataCenterLatency = dataCenterLatency;
		this.nbCacheConnected = nbCacheConnected;
		this.latenceToCacheServer = latenceToCacheServer;
		this.videos = videos;
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

	
	public ArrayList<LatenceToCacheServer> getLatenceToCacheServer() {
		return latenceToCacheServer;
	}
	
	public LatenceToCacheServer getLatenceToCacheServer(int i) {
		return latenceToCacheServer.get(i);
	}
	
	public void setLatenceToCacheServer(ArrayList<LatenceToCacheServer> latenceToCacheServer) {
		this.latenceToCacheServer = latenceToCacheServer;
	}
	
	public void addLatenceToCacheServer(LatenceToCacheServer latenceToCacheServer) {
		this.latenceToCacheServer.add(latenceToCacheServer);
	}
	
	
	public ArrayList<Integer> getVideos() {
		return videos;
	}
	
	public int getVideos(int i) {
		return videos.get(i);
	}

	public void setVideos(ArrayList<Integer> videos) {
		this.videos = videos;
	}

	public void addVideos(int videos) {
		this.videos.add(videos);
	}

	
	public LatenceToCacheServer findLatence(int cache){
		for (int i = 0; i < latenceToCacheServer.size(); i++){
			if (latenceToCacheServer.get(i).getCache() == cache)
				return latenceToCacheServer.get(i);
		}
		return null;
	}
}
