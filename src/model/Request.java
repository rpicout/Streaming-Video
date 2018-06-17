package model;

public class Request {

	private int video;
	private int requests;
	private int endpoint;
	
	public Request() {}
	
	/*
	 * Constructeur
	 */
	public Request(int video, int endpoint, int requests) {
		this.video = video;
		this.endpoint = endpoint;
		this.requests = requests;
	}

	
	/*
	 * Getters / Setters 
	 */
	public int getVideo() {
		return video;
	}

	public void setVideo(int video) {
		this.video = video;
	}
	

	public int getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(int endpoint) {
		this.endpoint = endpoint;
	}


	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}
	
	
}
