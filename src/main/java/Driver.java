import java.util.List;

import controllers.FileHandler;
import models.Sutra;
import services.FormulaFinder;
import services.NGrams;

public class Driver {

	public static void main(String[] args) {
		String folderPath = "C:\\Users\\foxma\\Documents\\School\\CompSci\\Capstone\\sutras\\";

		String oldCleanedPath = "C:\\Users\\foxma\\Documents\\School\\CompSci\\CleaningData\\Cleaning Data\\Cleaned";

		String[] paths = FileHandler.getFilesInFolder(oldCleanedPath);

		String context = FileHandler.readFile(paths[0]);

		String[] tokens = paths[0].split("/");
		String title = tokens[tokens.length - 1];

		Sutra sutra = new Sutra(title, context);

		List<String> temp = NGrams.stringToList(context);

		NGrams.genNGram(temp, 3);

		NGrams.removeDups(NGrams.genNGram(temp, 3));
		
		FormulaFinder.findRepatitions(temp, 3);
	}

}
