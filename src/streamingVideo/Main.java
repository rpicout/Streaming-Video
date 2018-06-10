package streamingVideo;


import streamingVideo.ReadData;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String name = "Data/me_at_the_zoo.txt";
		//String name = "Data/kittens.txt";
		//String name = "Data/trending_today.txt";
		//String name = "Data/video_worth_spreading.txt";
		
		ReadData f = new ReadData();
		Data r = f.getFromFile(name);
		
		System.out.println("Nombre de vidéo : "+ r.getNbVideo()); //TODO A supprimer avant de rendre 
	}

}
