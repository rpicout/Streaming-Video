package streamingVideo;


import heuristique.Gloutonne;
import heuristique.LocalSearch;
import heuristique.SacADos;
import model.Data;
import streamingVideo.ReadData;

public class Main {

	public static void main(String[] args) {
		
		String video1 = "InputData/me_at_the_zoo.txt";
		String video2 = "InputData/trending_today.txt";
		String video3 = "InputData/video_worth_spreading.txt";
		String video4 = "InputData/kittens.txt";
		
		String name = null;
		for (int i = 1; i < 4; i++) {
			if (i == 1)
				name = video1;
			else if (i == 2)
				name = video2;
			else if (i == 3)
				name = video3;
			else if (i == 4)
				name = video4;
			
			ReadData f = new ReadData();
			Data data = f.getFromFile(name);
			
			WriteResult wr = new WriteResult();
			WriteScore ws = new WriteScore();
			
			Gloutonne gloutonne = new Gloutonne();
			data = gloutonne.getSolution(data);
			
			wr.writeGloutonne(data, name);
			ws.writeGloutonne(data, name);
			
			SacADos sacADos = new SacADos();
			data = sacADos.getSolution(data);
			wr.writeSacADos(data, name);
			ws.writeSacADos(data, name);
			
			LocalSearch ls = new LocalSearch();
			data = ls.getSolution(data);
			wr.writeLocalSearch(data, name);
			ws.writeLocalSearch(data, name);
		}
	}

}
