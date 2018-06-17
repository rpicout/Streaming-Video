package streamingVideo;


import model.Data;
import streamingVideo.ReadData;

public class Main {

	public static void main(String[] args) {
		
		//String name = "Data/me_at_the_zoo.txt";
		//String name = "Data/kittens.txt";
		//String name = "Data/trending_today.txt";
		String name = "Data/video_worth_spreading.txt";
		
		ReadData f = new ReadData();
		Data data = f.getFromFile(name);
		
		System.out.println("Nombre de vidéo : "+ data.getNbVideo()); //TODO A supprimer avant de rendre 
	}

}
