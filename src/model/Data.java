package model;

public class Data {

	private int nbVideo;
	private int nbEndpoint;
	private int nbRequest;
	private int nbCaches;
	private int sizeCacheServer;
	private int[] sizeVideo;
	private Endpoint[] endpoint;
	private Cache[] cache;
	private Request[] request;
	private CacheConnected[] cacheConnected;
	
	/*
	 * Constructeur
	 */
	public Data(int nbVideo, int nbEndpoint, int nbRequest, int nbCaches, int sizeEndpoint) {
		this.nbVideo = nbVideo;
		this.nbEndpoint = nbEndpoint;
		this.nbRequest = nbRequest;
		this.nbCaches = nbCaches;
		this.sizeCacheServer = sizeEndpoint;
		sizeVideo = new int[nbVideo];
		
		endpoint = new Endpoint[nbEndpoint];
		for (int i = 0; i < nbEndpoint; i++)
			endpoint[i] = new Endpoint();
		
		cache = new Cache[nbCaches];
		for (int i = 0; i < nbCaches; i++)
			cache[i] = new Cache();
		
		request = new Request[nbRequest];
		for (int i = 0; i < nbRequest; i++)
			request[i] = new Request();
		
		cacheConnected = new CacheConnected[nbVideo];
		for (int i = 0; i < nbVideo; i++)
			cacheConnected[i] = new CacheConnected();
	}


	/*
	 * Getters / Setters
	 */
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
		return sizeCacheServer;
	}

	public void setSizeEndpoint(int sizeEndpoint) {
		this.sizeCacheServer = sizeEndpoint;
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


	public Endpoint[] getEndpoint() {
		return endpoint;
	}
	
	public Endpoint getEndpoint(int i) {
		return endpoint[i];
	}

	public void setEndpoint(Endpoint[] endpoint) {
		this.endpoint = endpoint;
	}
	
	public void setEndpoint(int i, Endpoint endpoint) {
		this.endpoint[i] = endpoint;
	}
	
	
	public int getSizeCacheServer() {
		return sizeCacheServer;
	}

	public void setSizeCacheServer(int sizeCacheServer) {
		this.sizeCacheServer = sizeCacheServer;
	}


	public Cache[] getCache() {
		return cache;
	}
	
	public Cache getCache(int i) {
		return cache[i];
	}

	public void setCache(Cache[] cache) {
		this.cache = cache;
	}
	
	public void setCache(int i, Cache cache) {
		this.cache[i] = cache;
	}


	public Request[] getRequest() {
		return request;
	}
	
	public Request getRequest(int i) {
		return request[i];
	}

	public void setRequest(Request[] request) {
		this.request = request;
	}
	
	public void setRequest(int i, Request request) {
		this.request[i] = request;
	}
	
	public int getNbCachesUsed() {
		int nbCachesUsed = 0;
		for (int i = 0; i < nbCaches; i++) {
			if (getCache(i).getVideo().size() != 0)
				nbCachesUsed ++;
		}
		return nbCachesUsed;
	}
	

	public CacheConnected[] getCacheConnected() {
		return cacheConnected;
	}

	public CacheConnected getCacheConnected(int i) {
		return cacheConnected[i];
	}

	public void setCacheConnected(CacheConnected[] cacheConnected) {
		this.cacheConnected = cacheConnected;
	}
	
	public void setCacheConnected(int i, CacheConnected cacheConnected) {
		this.cacheConnected[i] = cacheConnected;
	}


	public long getTimeSaved() {
		long timeSaved = 0;
		
		for (int i = 0; i < nbRequest; i++) {
			if (i == 4927) {
			int endpoint = getRequest(i).getEndpoint();
			int video = getRequest(i).getVideo();
			int request = getRequest(i).getRequests();
			for (int j = 0; j < cacheConnected[video].getCache().size(); j++) {
				int cache = cacheConnected[video].getCache(j);
				boolean isChanged = false;
				int k = 0;
				while (!isChanged && k < getEndpoint(endpoint).getNbCacheConnected()){
					if (getEndpoint(endpoint).getLatenceToCacheServer(k).getCache() == cache) {
						if (getEndpoint(endpoint).findLatence(cache) < getEndpoint(endpoint).getDataCenterLatency()) {
							timeSaved += request * (getEndpoint(endpoint).getDataCenterLatency() - getEndpoint(endpoint).findLatence(cache));
							isChanged = true;
						}
					}
					k++;
				}
			}
			}else {
				int endpoint = getRequest(i).getEndpoint();
				int video = getRequest(i).getVideo();
				int request = getRequest(i).getRequests();
				for (int j = 0; j < cacheConnected[video].getCache().size(); j++) {
					int cache = cacheConnected[video].getCache(j);
					boolean isChanged = false;
					int k = 0;
					while (!isChanged && k < getEndpoint(endpoint).getNbCacheConnected()){
						if (getEndpoint(endpoint).getLatenceToCacheServer(k).getCache() == cache) {
							if (getEndpoint(endpoint).findLatence(cache) < getEndpoint(endpoint).getDataCenterLatency()) {
								timeSaved += request * (getEndpoint(endpoint).getDataCenterLatency() 
										- getEndpoint(endpoint).findLatence(cache));
								isChanged = true;
							}
						}
						k++;
					}
				}
			}
		}
		return timeSaved;
	}

	public long getScore() {
		long timeSaved = getTimeSaved();
		long totalRequests = 0;
		for (int i = 0; i < nbRequest; i++)
			totalRequests += request[i].getRequests();
		long score = (timeSaved / totalRequests) * 1000;
		return score;
	}
}