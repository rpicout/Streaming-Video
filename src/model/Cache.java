package model;

import java.util.ArrayList;

public class Cache {

	private int numero;
	private int currentCharge;
	private int size;
	private ArrayList<Integer> video = new ArrayList<Integer>();
	
	public Cache() {}
	
	/*
	 * Constructeur
	 */
	public Cache(int numero, int size) {
		this.numero = numero;
		this.size = size;
		this.currentCharge = 0;
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
	
	
	public int getCurrentCharge() {
		return currentCharge;
	}
	
	public void setCurrentCharge(int currentCharge) {
		this.currentCharge = currentCharge;
	}

	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	public ArrayList<Integer> getVideo() {
		return video;
	}
	
	public int getVideo(int i) {
		return video.get(i);
	}

	public void setVideo(ArrayList<Integer> video) {
		this.video = video;
	}
	
	public void addVideo(int video) {
		this.video.add(video);
	}
	public void removeVideo(int video) {
		this.video.remove(video);
	}
	
}
