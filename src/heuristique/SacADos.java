package heuristique;

import java.util.ArrayList;

import model.Data;
import model.LatenceToCacheServer;
import model.Request;

public class SacADos {
	
	private int[][] numeroEndpointLatenceMin;

	//On cherche la latence la plus petite pour chacune des vidéos
	public LatenceToCacheServer[][] getTabLatenceMin(Data data) {
		LatenceToCacheServer[][] tabLatenceMin = new LatenceToCacheServer[data.getNbCaches()][data.getNbEndpoint()];
		numeroEndpointLatenceMin = new int[data.getNbCaches()][data.getNbEndpoint()];
		for (int i = 0; i < data.getNbCaches(); i++) {
			LatenceToCacheServer latenceToCacheServer = null;
			int numeroEndpoint = 0;
			int numMin = 0;
			for (int j = 0; j < data.getNbEndpoint(); j++) {
				boolean canSwitch = false;
				if (j == 0) {
					for (int k = 0; k < data.getEndpoint(j).getNbCacheConnected(); k++) {
						if (data.getEndpoint(j).getLatenceToCacheServer(k).getCache() == i &&
								data.getEndpoint(j).getLatenceToCacheServer(k).getLatency() <= data.getEndpoint(j).getDataCenterLatency()) {
							tabLatenceMin[i][j] = data.getEndpoint(j).getLatenceToCacheServer(k);
							numeroEndpointLatenceMin[i][j] = j;
						}
					}
					
				}
				else {
					for (int k = 0; k < data.getEndpoint(j).getNbCacheConnected(); k++) {
						if (data.getEndpoint(j).getLatenceToCacheServer(k).getCache() == i && 
								data.getEndpoint(j).getLatenceToCacheServer(k).getLatency() <= data.getEndpoint(j).getDataCenterLatency()) {
							boolean isInsert = false;
							int m = 0;
							while (!isInsert ||	m <= j) {
								if (tabLatenceMin[i][m] == null) {
									tabLatenceMin[i][m] = data.getEndpoint(j).getLatenceToCacheServer(k);
									numeroEndpointLatenceMin[i][m] = j;
									for (int l = 0; l < m; l++) {
										if (tabLatenceMin[i][l].getLatency() >= tabLatenceMin[i][m].getLatency()) {
											numMin = l;
											canSwitch = true;
										}
									}
									isInsert = true;
								}
								m++;
							}
							
						}
					}
				}
				if (canSwitch) {
					LatenceToCacheServer latenceMin = tabLatenceMin[i][numMin];
					tabLatenceMin[i][numMin] = tabLatenceMin[i][j];
					tabLatenceMin[i][j] = latenceMin;	
						
					int endpointMin = numeroEndpointLatenceMin[i][numMin];
					numeroEndpointLatenceMin[i][numMin] = numeroEndpointLatenceMin[i][j];
					numeroEndpointLatenceMin[i][j] = endpointMin;	
				}
			}	
		}
		return tabLatenceMin;
	}
	
	//On trie le tableau contenant les latences minimales par ordre croissant
	public int[] getTriLatence(Data data, LatenceToCacheServer[][] tabLatenceMin){
		int[] triLatence = new int[data.getNbCaches()]; 
		for (int i = 0; i < data.getNbCaches(); i++)
			triLatence[i] = i;
		for (int i = data.getNbCaches() - 1; i >= 0; i--) {
			int numLatenceMax = 0;
			for (int j = 0; j <= i; j++) {
				if (tabLatenceMin[triLatence[j]][0].getLatency() > tabLatenceMin[triLatence[numLatenceMax]][0].getLatency()) {
					numLatenceMax = j;
				}
			}
			int latenceMax = triLatence[numLatenceMax];
			triLatence[numLatenceMax] = triLatence[i];
			triLatence[i] = latenceMax;
		}
		return triLatence;
	}
	
	//On trie les requêtes par ordre décroissant
	public Request[] getTabRequest(Data data){
		Request[] request = data.getRequest();
		for (int i = data.getNbRequest() - 1; i >= 0; i--) {
			int numRequest = 0;
			for (int j = 0; j <= i; j++) {
				if (request[j].getRequests() < request[numRequest].getRequests()) {
					numRequest = j;
				}
			}
			Request currentRequest = request[i];
			request[i] = request[numRequest];
			request[numRequest] = currentRequest;
		}
		return request;
	}

	public Data getSolution(Data data) {
		
		for (int i = 0; i < data.getNbCaches(); i++) {
			data.getCache(i).getVideo().clear();
			data.getCache(i).setCurrentCharge(0);
		}
		for (int i = 0; i < data.getNbVideo(); i++){
			data.getCacheConnected(i).getCache().clear();
		}
		
		LatenceToCacheServer[][] tabLatenceMin = getTabLatenceMin(data);
		
		int[] triLatence = getTriLatence(data, tabLatenceMin);
		Request[] triRequest = getTabRequest(data);
		
		ArrayList<Integer> latenceTested = new ArrayList<Integer>();
		ArrayList<Integer> endpointTested = new ArrayList<Integer>();
		
		for (int i = 0; i < data.getNbCaches(); i++) {
			int iter = 0;
			boolean isInsert = false;
			while(!isInsert) {
				int numCache = tabLatenceMin[triLatence[i]][iter].getCache();
				int endpointLatence = numeroEndpointLatenceMin[triLatence[i]][iter];
				for (int j = 0; j < triRequest.length; j++){
					int endpoint = triRequest[j].getEndpoint();
					int video = triRequest[j].getVideo();
					boolean isInside = true;
					if (endpoint == endpointLatence) {
						if (data.getCacheConnected(video).getCache().size() == 0) {
							isInside = false;
						}
						else {
							for (int k = 0; k < data.getCache(numCache).getVideo().size(); k++){
								if (data.getCache(numCache).getVideo(k) == video){
									isInside = true;
								} else isInside = false;
							}
						}
						if (!isInside) {
							int currentCharge = data.getCache(numCache).getCurrentCharge();
							if (data.getSizeVideo(video) + currentCharge < data.getSizeCacheServer()){
								data.getCache(numCache).addVideo(video);
								data.getCache(numCache).setCurrentCharge(data.getSizeVideo(video) + currentCharge);
								data.getCacheConnected(video).addCache(numCache);
								isInsert = true;
							}
						}
					}
				}
				iter++;
			}
			/*if (!isInsert){
				latenceTested.add(tabLatenceMin[triLatence[i]].getCache());
				endpointTested.add(numeroEndpointLatenceMin[triLatence[i]]);
				int numeroEndpoint = 0;
				LatenceToCacheServer currentLatence = null;
				for (int j = 0; j < data.getNbEndpoint(); j++){
					boolean isTested = false;
					for (int k = 0; k < endpointTested.size(); k++) {
						if (j != endpointTested.get(k)){
							isTested = true;
						}
					}
					if (!isTested){
						for (int k = 0; k < data.getEndpoint(i).getNbCacheConnected(); k++){
							if (data.getEndpoint(j).getLatenceToCacheServer(k).getCache() == numCache &&
								data.getEndpoint(j).getLatenceToCacheServer(k).getLatency() <= data.getEndpoint(j).getDataCenterLatency()){
								if (currentLatence == null) {
									currentLatence = data.getEndpoint(j).getLatenceToCacheServer(k);
									numeroEndpoint = j;
								}
								else if (data.getEndpoint(j).getLatenceToCacheServer(k).getLatency() < currentLatence.getLatency()) {
									currentLatence = data.getEndpoint(j).getLatenceToCacheServer(k);
									numeroEndpoint = j;
								}
							}
						}
					}
				}	
				tabLatenceMin[i] = currentLatence;
				numeroEndpointLatenceMin[i] = numeroEndpoint;
				triLatence = getTriLatence(data, tabLatenceMin);
				i--;
			}
			else {
				latenceTested.clear();
				endpointTested.clear();
			}*/
			
		}
		
		return data;
	}

}