package heuristique;

import model.Data;

public class Gloutonne {
	
	public Data getSolution(Data data) {
		
		//On remet à zéro la solution
		for (int i = 0; i < data.getNbCaches(); i++) {
			data.getCache(i).getVideo().clear();
			data.getCache(i).setCurrentCharge(0);
		}
		
		for (int i = 0; i < data.getNbVideo(); i++) {
			boolean isAdd = false;
			int endpoint = 0;
			int k = 0;
			while (endpoint < data.getNbEndpoint() && !isAdd) {
				for (int j = 0; j < data.getEndpoint(endpoint).getVideos().size(); j++) {
					if (data.getEndpoint(endpoint).getVideos(j) == i) {
						while (k < data.getEndpoint(endpoint).getNbCacheConnected() && !isAdd) {
							int cacheServer = data.getEndpoint(endpoint).getLatenceToCacheServer(k).getCache();
							if (data.getCache(cacheServer).getCurrentCharge() + data.getSizeVideo(i) <= data.getSizeCacheServer()) {
								data.getCache(cacheServer).addVideo(i);
								int newCharge = data.getCache(cacheServer).getCurrentCharge() + data.getSizeVideo(i);
								data.getCache(cacheServer).setCurrentCharge(newCharge);
								isAdd = true;
							}
							k++;
						}
					}
				}
				endpoint++;
			}
				
		}
		
		return data;
	}

}
