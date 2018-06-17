package streamingVideo;


import heuristique.Gloutonne;
import model.Data;
import streamingVideo.ReadData;

public class Main {

	public static void main(String[] args) {
		
		String name = "InputData/me_at_the_zoo.txt";
		//String name = "InputData/kittens.txt";
		//String name = "InputData/trending_today.txt";
		//String name = "InputData/video_worth_spreading.txt";
		
		ReadData f = new ReadData();
		Data data = f.getFromFile(name);
		
		Gloutonne gloutonne = new Gloutonne();
		data = gloutonne.getSolution(data);
		
		WriteData w = new WriteData();
		w.write(data);
		
		System.out.println("Nombre de vidéo : "+ data.getNbVideo()); //TODO A supprimer avant de rendre 
	}

}
