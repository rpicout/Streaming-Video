package streamingVideo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Data;

public class WriteScore {
	
	File file;
	
	public void writeGloutonne (Data data) {
		file = new File("OutputData/gloutonneSolution.score.txt");
		write(data);
	}
	
	public void writeSacADos (Data data){
		file = new File("OutputData/sacADosSolution.score.txt");
		write(data);
	}
	
	public void write (Data data) {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("Score : " + data.getScore());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}