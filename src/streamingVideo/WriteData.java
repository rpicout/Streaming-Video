package streamingVideo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Data;

public class WriteData {
	
	public void write (Data data) {
		
		File file = new File("OutputData/gloutonneSolution.txt");
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
