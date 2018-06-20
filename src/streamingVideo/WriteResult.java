package streamingVideo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Data;


public class WriteResult {
	
	File file;
	
	public void writeGloutonne (Data data, String name) {
		file = new File("OutputData/gloutonneSolution.result."+ name.substring(10));
		write(data);
	}
	
	public void writeSacADos (Data data, String name){
		file = new File("OutputData/sacADosSolution.result."+ name.substring(10));
		write(data);
	}
	
	public void writeLocalSearch (Data data, String name){
		file = new File("OutputData/localSearchSolution.result."+ name.substring(10));
		write(data);
	}
	
	public void write (Data data) {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(data.getNbCachesUsed() + "\n");
		    for (int i = 0; i < data.getNbCaches(); i++) {
		    	fw.write("" + i);
		    	for (int j = 0; j < data.getCache(i).getVideo().size(); j++) {
		    		fw.write(" " + data.getCache(i).getVideo(j));
		    	}
		    	fw.write("\n");
		    }
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}