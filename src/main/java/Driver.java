import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import controllers.FileHandler;
import models.NGram;
import services.FormulaFinder;

public class Driver {

	public static void main(String[] args) {
		String oldCleanedPath = "Old_Cleaned";
		
		FileHandler fileHandeler = new FileHandler();
		FormulaFinder formulaFinder = new FormulaFinder();

		String[] paths = fileHandeler.getFilesInFolder(oldCleanedPath);
		
		String context = fileHandeler.readFile(paths[0]);
		String context2 = fileHandeler.readFile(paths[1]);
		
		Map<String, NGram> map = formulaFinder.findFormulas(context, context2, 3);
		//Map<String, NGram> map = formulaFinder.findRepetitions(context, 3);
		
		List<NGram> results = new ArrayList<>(map.values());
		Collections.sort(results, Collections.reverseOrder());
		
		formulaFinder.cleanResults(results);
		
		for(NGram ngram : results) {
			System.out.println(ngram);
		}

	}

}
