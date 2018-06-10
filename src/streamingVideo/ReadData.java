package streamingVideo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import streamingVideo.Data;

public class ReadData {

	public Data getFromFile(String name) {

		int nbVideo = 0;
		int nbEndpoint = 0;
		int nbRequest = 0;
		int nbCaches = 0;
		int sizeEndpoint = 0;
		int sizeVideo[] = null;
		int[][] dataCenterLatency = null;
		int[][] cachesLatency = null;
		int[][] request = null;

		try {
			
			File f = new File(name);
			Scanner scanner = new Scanner(f);

			try {
				// Lecture de la première ligne
				nbVideo = scanner.nextInt(); // lit le nombre de vidéos
				nbEndpoint = scanner.nextInt(); // on lit le nombre d'endpoints
				nbRequest = scanner.nextInt(); // on lit le nombre de request descriptions
				nbCaches = scanner.nextInt(); // on lit le nombre de caches serveur
				sizeEndpoint = scanner.nextInt(); // on lit la taille des caches serveurs

				System.out.println(nbVideo + " " + nbEndpoint + " " + nbRequest + " " + nbCaches + " " + sizeEndpoint); // TODO A supprimer avant de rendre

				//intialisation des tableaux
				sizeVideo = new int[nbVideo];
				dataCenterLatency = new int[nbEndpoint][2];
				cachesLatency = new int[nbCaches*nbEndpoint][3];
				for(int i =0 ; i<nbCaches*nbEndpoint; i++) {
					cachesLatency[i][0] = -1; //Numéro de l'endpoint concené par les données de la ligne i
					cachesLatency[i][1] = -1; // Numéro du cache connecté à l'endpoint
					cachesLatency[i][2] = (int)Double.POSITIVE_INFINITY;  // LAtency entre l'edpoint et le cache serveur
					// TODO les dernières lignes du tableaux resteront à -1 et l'infini si tout les caches ne sont pas relier à tous les endpoints
					//ce n'est peut être pas la meilleur solution mais je ne trouvais pas comment creer un tableau de pile la bonne taille ..
				}
				request = new int[nbRequest][3];
				
				// Lecture des tailles de chaque vidéo
				for (int i = 0; i < nbVideo; i++) {
					sizeVideo[i] = scanner.nextInt();
					System.out.print(sizeVideo[i] + " "); // TODO A supprimer avant de rendre
				}
				//Lecture de la latency entre chaque endpoint et le data center et les caches serveurs.
				int k = 0;
				for (int i = 0; i < nbEndpoint; i++) {
					dataCenterLatency[i][0] = scanner.nextInt(); // Latency entre l'endpoint i et le data center
					dataCenterLatency[i][1] = scanner.nextInt(); // Nombre de caches connecté à l'endpoint i
					System.out.println(dataCenterLatency[i][0] + " " + dataCenterLatency[i][1]); //TODO A supprimer avant de rendre
					int c = dataCenterLatency[i][1];
					if(c != 0 ) {
						for (int j = 0 ; j < c; j++) {
							cachesLatency[k][0] = i;
							cachesLatency[k][1] = scanner.nextInt();
							cachesLatency[k][2] = scanner.nextInt();
							System.out.println(cachesLatency[k][0] + " "+ cachesLatency[k][1]+" "+cachesLatency[k][2]); // TODO A supprimer avant de rendre
							k++;
						}
					}
				}
				
				
				// Lecture des request descriptions
				for(int i =0 ; i < nbRequest ;i ++) {
					for(int j = 0 ; j <3 ; j++) {
						request[i][j] = scanner.nextInt();
						System.out.print(request[i][j]); //TODO A supprimer avant de rendre
						System.out.print(" "); //TODO A supprimer avant de rendre
					}
					System.out.println(); //TODO A supprimer avant de rendre
				}
				

			} catch (NoSuchElementException exception) {
				System.out.println("erreur");
			}

			scanner.close();
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas été trouvé");
		}
		return new Data(nbVideo, nbEndpoint, nbRequest, nbCaches, sizeEndpoint,sizeVideo, dataCenterLatency , cachesLatency , request);
	}

}
