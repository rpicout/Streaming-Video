package model;

import java.util.ArrayList;

public class Data {

	private int nbVideo;
	private int nbEndpoint;
	private int nbRequest;
	private int nbCaches;
	private int sizeEndpoint;
	private int[] sizeVideo;
	private EndpointInfo[] endpoint;
	
	
	public Data(int nbVideo, int nbEndpoint, int nbRequest, int nbCaches, int sizeEndpoint) {
		this.nbVideo = nbVideo;
		this.nbEndpoint = nbEndpoint;
		this.nbRequest = nbRequest;
		this.nbCaches = nbCaches;
		this.sizeEndpoint = sizeEndpoint;
		sizeVideo = new int[nbVideo];
		endpoint = new EndpointInfo[nbEndpoint];
		for (int i = 0; i < nbEndpoint; i++)
			endpoint[i] = new EndpointInfo();
	}


	public int getNbVideo() {
		return nbVideo;
	}

	public void setNbVideo(int nbVideo) {
		this.nbVideo = nbVideo;
	}


	public int getNbEndpoint() {
		return nbEndpoint;
	}

	public void setNbEndpoint(int nbEndpoint) {
		this.nbEndpoint = nbEndpoint;
	}


	public int getNbRequest() {
		return nbRequest;
	}

	public void setNbRequest(int nbRequest) {
		this.nbRequest = nbRequest;
	}


	public int getNbCaches() {
		return nbCaches;
	}

	public void setNbCaches(int nbCaches) {
		this.nbCaches = nbCaches;
	}


	public int getSizeEndpoint() {
		return sizeEndpoint;
	}

	public void setSizeEndpoint(int sizeEndpoint) {
		this.sizeEndpoint = sizeEndpoint;
	}


	public int[] getSizeVideo() {
		return sizeVideo;
	}
	
	public int getSizeVideo(int i) {
		return sizeVideo[i];
	}

	public void setSizeVideo(int[] sizeVideo) {
		this.sizeVideo = sizeVideo;
	}
	
	public void setSizeVideo(int i, int sizeVideo) {
		this.sizeVideo[i] = sizeVideo;
	}


	public EndpointInfo[] getEndpoint() {
		return endpoint;
	}
	
	public EndpointInfo getEndpoint(int i) {
		return endpoint[i];
	}

	public void setEndpoint(EndpointInfo[] endpoint) {
		this.endpoint = endpoint;
	}
	
	public void setEndpoint(int i, EndpointInfo endpoint) {
		this.endpoint[i] = endpoint;
	}

}