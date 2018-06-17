package streamingVideo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Data;
import model.LatenceToCacheServer;
import model.Request;

public class ReadData {

	public Data getFromFile(String name) {

		Data data = null;
		
		try {
			
			File f = new File(name);
			Scanner scanner = new Scanner(f);

			try {
				// Lecture de la première ligne
				int nbVideo = scanner.nextInt(); // lit le nombre de vidéos
				int nbEndpoint = scanner.nextInt(); // on lit le nombre d'endpoints
				int nbRequest = scanner.nextInt(); // on lit le nombre de request descriptions
				int nbCaches = scanner.nextInt(); // on lit le nombre de caches serveur
				int sizeCacheServer = scanner.nextInt(); // on lit la taille des caches serveurs
				
				data = new Data(nbVideo, nbEndpoint, nbRequest, nbCaches, sizeCacheServer);

				System.out.println(data.getNbVideo() + " " + data.getNbEndpoint() + " " + data.getNbRequest() + " " + data.getNbCaches() + " " + data.getSizeEndpoint()); // TODO A supprimer avant de rendre

				// Lecture des tailles de chaque vidéo
				for (int i = 0; i < data.getNbVideo(); i++) {
					data.setSizeVideo(i, scanner.nextInt());
					System.out.print(data.getSizeVideo(i) + " "); // TODO A supprimer avant de rendre
				}
				
				for (int i = 0; i < data.getNbCaches(); i++) {
					data.getCache(i).setNumero(i);
					data.getCache(i).setSize(sizeCacheServer);
				}
				
				//Lecture de la latency entre chaque endpoint et le data center et les caches serveurs.
				for (int i = 0; i < data.getNbEndpoint(); i++) {
					data.getEndpoint(i).setNumero(i);
					data.getEndpoint(i).setDataCenterLatency(scanner.nextInt()); // Latency entre l'endpoint i et le data center
					data.getEndpoint(i).setNbCacheConnected(scanner.nextInt()); // Nombre de caches connecté à l'endpoint i
					System.out.println(data.getEndpoint(i).getDataCenterLatency() + " " + data.getEndpoint(i).getNbCacheConnected()); //TODO A supprimer avant de rendre
					for (int j = 0 ; j < data.getEndpoint(i).getNbCacheConnected(); j++) {
						data.getEndpoint(i).addLatenceToCacheServer(new LatenceToCacheServer(scanner.nextInt(), scanner.nextInt()));
						System.out.println(data.getEndpoint(i).getLatenceToCacheServer(j).getNumeroCacheServer() + " " + data.getEndpoint(i).getLatenceToCacheServer(j).getLatency()); // TODO A supprimer avant de rendre
					}
				}
				
				// Lecture des request descriptions
				for(int i = 0; i < data.getNbRequest(); i ++) {
					int video = scanner.nextInt();
					int endpoint = scanner.nextInt();
					data.setRequest(i, new Request(video, endpoint, scanner.nextInt()));
					data.getEndpoint(endpoint).addVideos(video);
					System.out.println(data.getRequest(i).getVideo() + " " +
							data.getRequest(i).getEndpoint() + " " + 
							data.getRequest(i).getRequests()); //TODO A supprimer avant de rendre
				}
				

			} catch (NoSuchElementException exception) {
				System.out.println("erreur");
			}

			scanner.close();
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas été trouvé");
		}
		
		return data;
	}

}
