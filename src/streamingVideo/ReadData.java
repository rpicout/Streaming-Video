package streamingVideo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.CacheToEndpoint;
import model.Data;
import model.VideoRequest;

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
				int sizeEndpoint = scanner.nextInt(); // on lit la taille des caches serveurs
				
				data = new Data(nbVideo, nbEndpoint, nbRequest, nbCaches, sizeEndpoint);

				System.out.println(data.getNbVideo() + " " + data.getNbEndpoint() + " " + data.getNbRequest() + " " + data.getNbCaches() + " " + data.getSizeEndpoint()); // TODO A supprimer avant de rendre

				// Lecture des tailles de chaque vidéo
				for (int i = 0; i < data.getNbVideo(); i++) {
					data.setSizeVideo(i, scanner.nextInt());
					System.out.print(data.getSizeVideo(i) + " "); // TODO A supprimer avant de rendre
				}
				
				//Lecture de la latency entre chaque endpoint et le data center et les caches serveurs.
				for (int i = 0; i < data.getNbEndpoint(); i++) {
					data.getEndpoint(i).setNumero(i);
					data.getEndpoint(i).setDataCenterLatency(scanner.nextInt()); // Latency entre l'endpoint i et le data center
					data.getEndpoint(i).setNbCacheConnected(scanner.nextInt()); // Nombre de caches connecté à l'endpoint i
					System.out.println(data.getEndpoint(i).getDataCenterLatency() + " " + data.getEndpoint(i).getNbCacheConnected()); //TODO A supprimer avant de rendre
					for (int j = 0 ; j < data.getEndpoint(i).getNbCacheConnected(); j++) {
						data.getEndpoint(i).addCache(j, new CacheToEndpoint(scanner.nextInt(), scanner.nextInt()));
						System.out.println(data.getEndpoint(i).getCache(j).getNumero() + " " + data.getEndpoint(i).getCache(j).getLatency()); // TODO A supprimer avant de rendre
					}
				}
				
				// Lecture des request descriptions
				for(int i = 0; i < data.getNbRequest(); i ++) {
					int numeroVideo = scanner.nextInt();
					int numeroEndpoint = scanner.nextInt();
					int nbVideoRequest = data.getEndpoint(numeroEndpoint).getVideo().size();
					data.getEndpoint(numeroEndpoint).addVideo(nbVideoRequest, new VideoRequest(numeroVideo, scanner.nextInt()));
					System.out.println(data.getEndpoint(numeroEndpoint).getVideo(nbVideoRequest).getNumero() + " " +
							data.getEndpoint(numeroEndpoint).getNumero() + " " + 
							data.getEndpoint(numeroEndpoint).getVideo(nbVideoRequest).getRequests()); //TODO A supprimer avant de rendre
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
