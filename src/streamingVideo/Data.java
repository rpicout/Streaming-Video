package streamingVideo;

public class Data {

	private int nbVideo;
	private int nbEndpoint;
	private int nbRequest;
	private int nbCaches;
	private int sizeEndpoint;
	private int[] sizeVideo;
	private int[][] dataCenterLatency;
	private int[][] cachesLatency;
	private int[][] request;
	

	public Data(int nbVideo, int nbEndpoint, int nbRequest, int nbCaches, int sizeEndpoint,int[] sizeVideo, int[][] dataCenterLatency, int[][] cachesLatency, int[][] request) {
		this.nbVideo = nbVideo;
		this.nbEndpoint = nbEndpoint;
		this.nbRequest = nbRequest;
		this.nbCaches = nbCaches;
		this.sizeEndpoint = sizeEndpoint;
		this.sizeVideo = sizeVideo;
		this.dataCenterLatency = dataCenterLatency ;
		this.cachesLatency = cachesLatency;
		this.request = request;
	}

	public int getNbVideo() {
		return nbVideo;
	}

	public int getNbEndpoint() {
		return nbEndpoint;
	}

	public int getNbRequest() {
		return nbRequest;
	}

	public int getNbCaches() {
		return nbCaches;
	}

	public int getSizeEndpoint() {
		return sizeEndpoint;
	}
	
	public int[] getSizeVideo() {
		return sizeVideo;
	}

	public int[][] getDataCenterLatency() {
		return dataCenterLatency;
	}

	public int[][] getCachesLatency() {
		return cachesLatency;
	}

	public int[][] getRequest() {
		return request;
	}
	

}
