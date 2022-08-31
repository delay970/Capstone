import java.util.Map;
import java.util.Map.Entry;

import controllers.FileHandler;
import models.NGram;
import services.FormulaFinder;

public class Driver {

	public static void main(String[] args) {
		String folderPath = "C:\\Users\\foxma\\Documents\\School\\CompSci\\Capstone\\sutras\\";

		String oldCleanedPath = "C:\\Users\\foxma\\Documents\\School\\CompSci\\Capstone\\Old_Cleaned";

		String[] paths = FileHandler.getFilesInFolder(oldCleanedPath);
		
		String context = FileHandler.readFile(paths[2]);
		String context2 = FileHandler.readFile(paths[3]);
		
		Map<String, NGram> map = FormulaFinder.findFormulas(context, context2, 3);
		
		for(Entry<String, NGram> ngram : map.entrySet()) {
			System.out.println(ngram.getValue());
		}
	}

}
