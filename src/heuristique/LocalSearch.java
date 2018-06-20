package heuristique;

import java.util.ArrayList;
import java.util.Random;

import model.Data;

public class LocalSearch {

	public Data getSolution(Data data) {
		/**
		 * CurrentSol = solution al�atoire satisfaisant les contraintes du programme
		 * lin�aire Tant que nous ne trouvons pas de solution optimale Faire
		 * CurrentTpsCharg = r�sultat de la fonction objective obtenu avec CurrentSol
		 * Prendre une vid�o i al�atoirement Pour j allant de 1 � C //nombre de cache
		 * server Enlever cette vid�o du cache server auquel elle se trouve Ins�rer la
		 * vid�o i dans le cache server j en respectant les contraintes Sol = solution
		 * obtenue en ayant ins�rer i dans j TpsCharg = r�sultat de la fonction
		 * objective obtenu avec Sol Si CurrentTpsCharg > TpsCharg Alors CurrentTpsCharg
		 * = TpsCharg CurrentSol = Sol Sinon On garde inchang� CurrentSol et
		 * CurrentTpsCharg Fin Si Fin Pour Fin Tant que
		 * 
		 * 
		 */

		// solution al�atoire
		Data currentSol = null;
		Gloutonne gloutonne = new Gloutonne();
		currentSol = gloutonne.getSolution(data);
		Data newSol = currentSol;
		
		ArrayList<Integer> videoTested = new ArrayList<Integer>();

<<<<<<< HEAD
		do {
			videoTested.clear();
			Data solution = newSol;
			while (videoTested.size() < currentSol.getNbVideo()) {
				Random r = new Random();
				int videoAleatoire = 0 + r.nextInt(currentSol.getNbVideo() - 0);
	
				boolean isBetter = false;
				int j = 0;
				while (!isBetter && j < newSol.getNbCaches()) {
	
					// Enlever cette vid�o du cache server auquel elle se trouve
					for (int k = 0; k < newSol.getNbCaches(); k++) {
						int newCharge = newSol.getCache(j).getCurrentCharge() + newSol.getSizeVideo(videoAleatoire);
						// On v�rifie qu'on puisse mettre la vid�o dans le cache server
						if (newCharge <= newSol.getSizeCacheServer()) {
							if (k != j && newSol.getCache(k).getVideo().contains(videoAleatoire)) {
								newSol.getCache(k).removeVideo(videoAleatoire);
								newSol.getCacheConnected(videoAleatoire).removeCache(k);
								newSol.getCache(k).setCurrentCharge(newSol.getCache(k).getCurrentCharge() - newSol.getSizeVideo(videoAleatoire));
								
								// Ins�rer la vid�o i dans le cache server j en respectant les contraintes
								newSol.getCache(j).addVideo(videoAleatoire);
								newSol.getCache(j).setCurrentCharge(newCharge);
								newSol.getCacheConnected(videoAleatoire).addCache(j);
								if (newSol.getScore() >= currentSol.getScore()) {
									isBetter = true;
									//currentSol = newSol;
								}
							}
						}
=======
		while (currentSol.getScore() >= newSol.getScore()) {
			Random r = new Random();
			int videoAleatoire = 0 + r.nextInt(currentSol.getNbVideo() - 0);
			int cacheServeur = 0; 
			
				// Enlever cette vid�o du cache server auquel elle se trouve
				for (int k = 0; k < currentSol.getNbCaches(); k++) {
					if (k != cacheServeur && currentSol.getCache(k).getVideo()
							.contains(currentSol.getCache(k).getVideo(videoAleatoire))) {
						newSol.getCache(k).removeVideo(videoAleatoire);
>>>>>>> 5dfe00f8e3c3eccf2f1d91f1356b0ffc1a1579de
					}
					j++;
				}
				boolean isTested = false;
				for (int i = 0; i < videoTested.size(); i++)
					if (videoTested.get(i) == videoAleatoire)
						isTested = true;
				if (!isTested)
					videoTested.add(videoAleatoire);
				if (videoTested.size() == currentSol.getNbVideo()) {
					if (solution.getScore() > newSol.getScore()) {
						videoTested.clear();
						solution = newSol;
					}
					else
						newSol = solution;
				}
<<<<<<< HEAD
			}
		}while (currentSol.getScore() < newSol.getScore());
=======
				// Ins�rer la vid�o i dans le cache server j en respectant les contraintes
				newSol.getCache(cacheServeur).addVideo(videoAleatoire);
				int newCharge = newSol.getCache(cacheServeur).getCurrentCharge() + newSol.getSizeVideo(videoAleatoire);
				newSol.getCache(cacheServeur).setCurrentCharge(newCharge);
				newSol.getCacheConnected(videoAleatoire).addCache(cacheServeur);
				cacheServeur++;
			
		}
>>>>>>> 5dfe00f8e3c3eccf2f1d91f1356b0ffc1a1579de


		return newSol;
	}
}
