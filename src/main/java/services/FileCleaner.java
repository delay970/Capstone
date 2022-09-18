package services;

import controllers.FileHandler;

public class FileCleaner {

	public String cleanFile(String path) {
		FileHandler fileHandler = new FileHandler();
		String context = fileHandler.readFile(path);

		context = removeHeader(context);

		context = removeSubstrings(context);

		context = removeNumbers(context);

		context = removeCapitalization(context);

		context = removePunctuation(context);

		context = removeRomanNum(context);

		context = removeExtraWhitespace(context);
		
		//saveCleanedFile(context, "temp.txt");
		saveCleanedFile(context, path);

		return context;
	}

	private String removeHeader(String context) {

		int index = 0; // index that will be used to find what to remove

		String header = "---";

		index = context.lastIndexOf(header); // remove eveything above ---

		if (index != -1) {
			context = context.substring(index + header.length());
		}

		return context;
	}

	private String removeSubstrings(String context) {

		String[] openSequence = { "*[", "/", "(", "[" };
		String[] closeSequence = { "]*", "/", ")", "]" };

		// ask about words in curly braces
		context = deleteBetweenStrings(openSequence, closeSequence, context);
		return context;
	}

	private String deleteBetweenStrings(String[] openArr, String[] closeArr, String context) {
		StringBuilder contextBuilder = new StringBuilder(context);
		for (int i = 0; i < openArr.length; i++) {
			String open = openArr[i];
			String close = closeArr[i];

			int index = contextBuilder.indexOf(open);
			while (index != -1) {
				int index2 = contextBuilder.indexOf(close, index + 1);
				// index+1 ensures that if open == close it will look
				// for the next char and not have the same index
				contextBuilder.delete(index, index2 + close.length());
				index = contextBuilder.indexOf(open);
			}

		}

		return contextBuilder.toString();
	}

	private String removePunctuation(String context) {
		context = context.replaceAll("-", " ");
		return context.replaceAll("\\p{IsPunctuation}", "");

	}

	private String removeNumbers(String context) {

		return context.replaceAll("\\d", "");

	}

	private String removeRomanNum(String context) {
		return context.replaceAll("\\s+([ivxl])+\\s", "");

	}

	private String removeCapitalization(String context) {
		return context.toLowerCase();

	}

	private String removeExtraWhitespace(String context) {
		context = context.replaceAll("\\s", " ");
		return context.trim().replaceAll(" +", " ");

	}

	private void saveCleanedFile(String context, String path) {
		
		FileHandler fileHandler = new FileHandler();
		
		String[] pathParts = path.replaceAll("\\\\", "/").split("/");
		String writePath = "Cleaned/" + pathParts[pathParts.length - 1];

		fileHandler.writeFile(context, writePath);
	}
}
