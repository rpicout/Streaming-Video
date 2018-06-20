package heuristique;

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
		currentSol = gloutonne.getSolution(currentSol);
		Data newSol = currentSol;

		while (currentSol.getScore() >= newSol.getScore()) {
			Random r = new Random();
			int videoAleatoire = 0 + r.nextInt(currentSol.getNbVideo() - 0);

			for (int j = 0; j < currentSol.getNbCaches(); j++) {
				// Enlever cette vid�o du cache server auquel elle se trouve
				for (int k = 0; k < currentSol.getNbCaches(); k++) {
					if (k != j && currentSol.getCache(k).getVideo()
							.contains(currentSol.getCache(k).getVideo(videoAleatoire))) {
						newSol.getCache(k).removeVideo(videoAleatoire);
					}
				}
				// Ins�rer la vid�o i dans le cache server j en respectant les contraintes
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