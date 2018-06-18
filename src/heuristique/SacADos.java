package heuristique;

import java.util.ArrayList;

import model.Data;
import model.LatenceToCacheServer;
import model.Request;

public class SacADos {
	
	private int[] numeroEndpointLatenceMin;

	//On cherche la latence la plus petite pour chacune des vidéos
	public LatenceToCacheServer[] getTabLatenceMin(Data data) {
		LatenceToCacheServer[] tabLatenceMin = new LatenceToCacheServer[data.getNbCaches()];
		numeroEndpointLatenceMin = new int[data.getNbCaches()];
		for (int i = 0; i < data.getNbCaches(); i++) {
			LatenceToCacheServer latenceToCacheServer = null;
			int numeroEndpoint = 0;
			for (int j = 0; j < data.getNbEndpoint(); j++) {
				for (int k = 0; k < data.getEndpoint(j).getNbCacheConnected(); k++) {
					if (data.getEndpoint(j).getLatenceToCacheServer(k).getCache() == i &&
						data.getEndpoint(j).getLatenceToCacheServer(k).getLatency() <= data.getEndpoint(j).getDataCenterLatency()) {
						if (latenceToCacheServer == null) {
							latenceToCacheServer = data.getEndpoint(j).getLatenceToCacheServer(k);
							numeroEndpoint = j;
						}
						else if (data.getEndpoint(j).getLatenceToCacheServer(k).getLatency() < latenceToCacheServer.getLatency()) {
							latenceToCacheServer = data.getEndpoint(j).getLatenceToCacheServer(k);
							numeroEndpoint = j;
						}
					}
				}
			}
			tabLatenceMin[i] = latenceToCacheServer;
			numeroEndpointLatenceMin[i] = numeroEndpoint;
		}
		return tabLatenceMin;
	}
	
	//On trie le tableau contenant les latences minimales par ordre croissant
	public int[] getTriLatence(Data data, LatenceToCacheServer[] tabLatenceMin){
		int[] triLatence = new int[data.getNbCaches()]; 
		for (int i = 0; i < data.getNbCaches(); i++)
			triLatence[i] = i;
		for (int i = 0; i < data.getNbCaches(); i++) {
			for (int j = data.getNbCaches()-1; j > i; j--) {
				if (tabLatenceMin[j-1].getLatency() > tabLatenceMin[j].getLatency()) {
					
					
					int numLatence = triLatence[j-1];
					triLatence[j-1] = triLatence[j];
					triLatence[j] = numLatence;
					
					int numEndpoint = numeroEndpointLatenceMin[j-1];
					numeroEndpointLatenceMin[j-1] = numeroEndpointLatenceMin[j];
					numeroEndpointLatenceMin[j] = numEndpoint;
				}
			}
		}
		return triLatence;
	}
	
	//On trie les requêtes par ordre décroissant
	public Request[] getTabRequest(Data data){
		Request[] request = data.getRequest();
		for (int i = 0; i < data.getNbRequest(); i++) {
			for (int j = data.getNbRequest() - 1; j > i; j--) {
				if (request[j].getRequests() > request[j-1].getRequests()) {
					Request currentRequest = request[j];
					request[j-1] = request[j];
					request[j] = currentRequest;
				}
			}
		}
		return request;
	}

	public Data getSolution(Data data) {
		
		for (int i = 0; i < data.getNbCaches(); i++) {
			data.getCache(i).getVideo().clear();
			data.getCache(i).setCurrentCharge(0);
		}
		
		LatenceToCacheServer[] tabLatenceMin = getTabLatenceMin(data);
		
		int[] triLatence = getTriLatence(data, tabLatenceMin);
		Request[] triRequest = getTabRequest(data);
		
		ArrayList<Integer> latenceTested = new ArrayList<Integer>();
		ArrayList<Integer> endpointTested = new ArrayList<Integer>();
		
		for (int i = 0; i < data.getNbCaches(); i++) {
			int numCache = tabLatenceMin[triLatence[i]].getCache();
			int endpoint = triRequest[i].getEndpoint();
			boolean isInsert = false;
			for (int j = 0; j < data.getNbRequest(); j++){
				int video = triRequest[j].getVideo();
				int currentCharge = data.getCache(numCache).getCurrentCharge();
				if (data.getSizeVideo(video) + currentCharge < data.getSizeCacheServer()){
					data.getCache(numCache).addVideo(video);
					data.getCache(numCache).setCurrentCharge(data.getSizeVideo(video) + currentCharge);
					data.getCacheConnected(video).addCache(numCache);
					isInsert = true;
				}
			}
			if (!isInsert){
				latenceTested.add(tabLatenceMin[triLatence[i]].getCache());
				endpointTested.add(numeroEndpointLatenceMin[triLatence[i]]);
				int numeroEndpoint = 0;
				LatenceToCacheServer currentLatence = null;
				for (int j = 0; j < data.getNbEndpoint(); j++){
					if (j != numeroEndpointLatenceMin[triLatence[i]]){
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
			}
		}
		
		return data;
	}

}