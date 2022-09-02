import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import controllers.FileHandler;
import models.NGram;
import services.FormulaFinder;

public class Driver {

	public static void main(String[] args) {
		String folderPath = "C:\\Users\\foxma\\Documents\\School\\CompSci\\Capstone\\sutras\\";

		String oldCleanedPath = "Old_Cleaned";

		String[] paths = FileHandler.getFilesInFolder(oldCleanedPath);
		
		String context = FileHandler.readFile(paths[4]);
		String context2 = FileHandler.readFile(paths[3]);
		
		Map<String, NGram> map = FormulaFinder.findFormulas(context, context2, 30);
		//Map<String, NGram> map = FormulaFinder.findRepetitions(context, 3);
		
		List<NGram> results = new ArrayList<>(map.values());
		Collections.sort(results, Collections.reverseOrder());
		
		FormulaFinder.cleanResults(results);
		
		for(NGram ngram : results) {
			System.out.println(ngram);
		}

	}

}
