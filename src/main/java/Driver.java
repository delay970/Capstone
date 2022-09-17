import controllers.FileHandler;
import services.FileCleaner;
import services.FormulaFinder;

public class Driver {

	public static void main(String[] args) {
		String oldCleanedPath = "Old_Cleaned";
		String uncleanedFiles = "sutras";
		
		FileHandler fileHandeler = new FileHandler();
		FormulaFinder formulaFinder = new FormulaFinder();
		FileCleaner fileCleaner = new FileCleaner();
				
		String[] paths = fileHandeler.getFilesInFolder(uncleanedFiles);
		
		fileCleaner.cleanFile(paths[0]);
		
//		String context = fileHandeler.readFile(paths[0]).toString();
//		String context2 = fileHandeler.readFile(paths[1]).toString();
//		
//		
//		Map<String, NGram> map = formulaFinder.findFormulas(context, context2, 3);
//		//Map<String, NGram> map = formulaFinder.findRepetitions(context, 3);
//		
//		List<NGram> results = new ArrayList<>(map.values());
//		Collections.sort(results, Collections.reverseOrder());
//		
//		formulaFinder.cleanResults(results);
//		
//		for(NGram ngram : results) {
//			System.out.println(ngram);
//		}

	}

}
