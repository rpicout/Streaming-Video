package streamingVideo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Data;

public class WriteScore {
	
	File file;
	
	public void writeGloutonne (Data data, String name) {
		file = new File("OutputData/gloutonneSolution.score."+ name.substring(10));
		write(data, name);
	}
	
	public void writeSacADos (Data data, String name){
		file = new File("OutputData/sacADosSolution.score."+ name.substring(10));
		write(data, name);
	}
	
	public void writeLocalSearch (Data data, String name){
		file = new File("OutputData/localSearchSolution.score."+ name.substring(10));
		write(data, name);
	}
	
	public void write (Data data, String name) {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("Vidéo testée : " + name + "\n");
			fw.write("Score : " + data.getScore());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}