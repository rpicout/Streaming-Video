package model;

import java.util.ArrayList;

public class CacheConnected {

	private ArrayList<Integer> cache = new ArrayList<Integer>();
	
	public CacheConnected() {}
	/*
	 * Constructeur
	 */
	public CacheConnected(ArrayList<Integer> cache) {
		this.cache = cache;
	}

	/*
	 * Getters / Setters
	 */
	public ArrayList<Integer> getCache() {
		return cache;
	}
	
	public int getCache(int i) {
		return cache.get(i);
	}

	public void setCache(ArrayList<Integer> cache) {
		this.cache = cache;
	}
	
	public void addCache(int cache) {
		this.cache.add(cache);
	}
	
}
