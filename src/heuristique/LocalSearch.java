package heuristique;

import java.util.Random;

import model.Data;

public class LocalSearch {

	public Data getSolution(Data data) {
		/**
		 * CurrentSol = solution aléatoire satisfaisant les contraintes du programme
		 * linéaire Tant que nous ne trouvons pas de solution optimale Faire
		 * CurrentTpsCharg = résultat de la fonction objective obtenu avec CurrentSol
		 * Prendre une vidéo i aléatoirement Pour j allant de 1 à C //nombre de cache
		 * server Enlever cette vidéo du cache server auquel elle se trouve Insérer la
		 * vidéo i dans le cache server j en respectant les contraintes Sol = solution
		 * obtenue en ayant insérer i dans j TpsCharg = résultat de la fonction
		 * objective obtenu avec Sol Si CurrentTpsCharg > TpsCharg Alors CurrentTpsCharg
		 * = TpsCharg CurrentSol = Sol Sinon On garde inchangé CurrentSol et
		 * CurrentTpsCharg Fin Si Fin Pour Fin Tant que
		 * 
		 * 
		 */

		// solution aléatoire
		Data currentSol = null;
		Gloutonne gloutonne = new Gloutonne();
		currentSol = gloutonne.getSolution(currentSol);
		Data newSol = currentSol;

		while (currentSol.getScore() >= newSol.getScore()) {
			Random r = new Random();
			int videoAleatoire = 0 + r.nextInt(currentSol.getNbVideo() - 0);

			for (int j = 0; j < currentSol.getNbCaches(); j++) {
				// Enlever cette vidéo du cache server auquel elle se trouve
				for (int k = 0; k < currentSol.getNbCaches(); k++) {
					if (k != j && currentSol.getCache(k).getVideo()
							.contains(currentSol.getCache(k).getVideo(videoAleatoire))) {
						newSol.getCache(k).removeVideo(videoAleatoire);
					}
				}
				// Insérer la vidéo i dans le cache server j en respectant les contraintes
				newSol.getCache(j).addVideo(videoAleatoire);
				int newCharge = newSol.getCache(j).getCurrentCharge() + newSol.getSizeVideo(videoAleatoire);
				newSol.getCache(j).setCurrentCharge(newCharge);
				newSol.getCacheConnected(videoAleatoire).addCache(j);

			}
		}

		data = currentSol; // Je sais pas si il faut mettre currentSol ou newSol...

		return data;
	}
}
