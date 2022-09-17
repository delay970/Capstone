package services;

import controllers.FileHandler;

public class FileCleaner {

	public void cleanFile(String Path) {
		FileHandler fileHandler = new FileHandler();
		String dirtyContext = fileHandler.readFile(Path);
		
		dirtyContext = removeHeader(dirtyContext);
		
		dirtyContext = removeSubstrings(dirtyContext);
		
		//dirtyContext = removeRomanNum(dirtyContext);
		
		dirtyContext = removeNumbers(dirtyContext);
		
		dirtyContext = removeCapitalization(dirtyContext);
		
		dirtyContext = removePunctuation(dirtyContext);
		
		//dirtyContext = removeExtraWhitespace(dirtyContext);

		fileHandler.writeFile(dirtyContext);
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
				//  for the next char and not have the same index
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
		return context.replaceAll("", "");

	}

	private String removeCapitalization(String context) {
		return context.toLowerCase();

	}

	private String removeExtraWhitespace(String context) {
		context = context.replaceAll("\\s", " ");
		return context.trim().replaceAll(" +", " ");

	}

//
//	public String cleanWord(string &word) {
//		string cleanedWord = "";
//
//		int count = 0;
//
//		for (int i = 0; i < word.size(); i++) {
//
//			char& c = word[i];
//
//			if (count == 3) {// this looks for --- or longer and removes everything above that line
//				return "remove_everything";
//			}
//
//			if (c == '-') {
//				count++;
//				continue;
//			}
//			else {
//				count = 0;
//			}
//
//			if (c == '/' || c == '*') {// removes full line after these characters
//				return "remove_line";
//			}
//
//			if ((c >= 0 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 127)) { //ingore puncuation and numbers
//				continue;
//			}
//
//
//			if (c >= 65 && c <= 90) {  // capital to lowercase
//				c += 32;
//			}
//
//			if (c < 0) {

//				cleanedWord += c;
//				i++;
//				switch (c) { // checks for unicode capitals and converts them to lowecase
//				case -60:
//					c = word[i];
//					if (c == -128 || c == -86)
//						c++;
//					break;
//				case -59:
//					c = word[i];
//					if (c == -86 || c == -102)
//						c++;
//					break;
//				case -31:
//					c = word[i];
//					cleanedWord += c;
//					i++;
//					switch (c) {
//					case -71:
//						c = word[i];
//						if (c == -102 || c == -124 || c == -84 || c == -122 || c == -94)
//							c++;
//						break;
//					case -72:
//						c = word[i];
//						if (c == -74 || c == -116)
//							c++;
//						break;
//					}
//					break;
//				case -61:
//					c = word[i];
//					if (c == -111)
//						c = -79;
//					break;
//				case -30:
//					cleanedWord.pop_back();
//					i++;
//					i++;
//					continue;
//					break;
//				}
//			}
//			cleanedWord += c;
//		}
//
//		bool rn = true;
//		for (int i = 0; i < cleanedWord.size(); i++) {
//			char& c = cleanedWord[i];
//
//			if (c != 'i' && c != 'v' && c != 'x' && c != 'l') {
//				rn = false;
//				break;
//			}
//		}
//
//		if (rn) {
//			return "is_rn";
//		}
//
//		return cleanedWord;
//	}
//
//	void toFile(vector<string> &data, const string &path) {
//		ofstream writer;
//		writer.open(path, ios::out);
//		writer.close();
//
//		writer.open(path, ios::app);
//		for (int i = 0; i < data.size(); i++) {
//			writer << data[i]<<" ";
//		}
//
//		writer.close();
//	}
//
//	vector<string> cleanData(string& raw_text) {
//		vector<string> data;
//		string temp = "";
//		for (int i = 0; i < raw_text.size(); i++) {
//
//			char c = raw_text[i];
//
//			if (c == '[' || c == '{' || c == '<') {//removes things inside brackets
//				char t = c;
//				while (c != t + 2) {
//					i++;
//					c = raw_text[i];
//				}
//				continue;
//			}
//
//			if (c == '(') {//removes things inside parenthis
//				char t = c;
//				while (c != t + 1) {
//					i++;
//					c = raw_text[i];
//				}
//				continue;
//			}
//
//			if (c == 9 || c == 10 || c == ' ') { //end of word
//				string cleanedWord = cleanWord(temp);
//				temp = "";
//				if (cleanedWord.empty()) {
//					continue;
//				}
//
//				if (cleanedWord == "is_rn") {
//					continue;
//				}
//
//				if (cleanedWord == "remove_line") {
//					while (c != 10) {
//						i++;
//						c = raw_text[i];
//					}
//					continue;
//				}
//
//				if (cleanedWord == "remove_everything") {
//					data.clear();
//					continue;
//				}
//
//				data.push_back(cleanedWord);
//				continue;
//			}
//			temp += c;
//		}
//		return data;
//	}
//
//	int main() {
//
//		const string path = "C:/Users/foxma/source/repos/Ngrams 2/Ngrams 2/Data/the_files";
//		for (const auto& entry : filesystem::directory_iterator(path)) {
//			string p = entry.path().string();
//			string raw_text = readFile(p);
//			cout << "file loaded" << endl<<endl;
//			vector<string> data = cleanData(raw_text);
//			cout << "file cleaned" << endl;
//			int pos = p.find_last_of('\\');
//			string path = "Cleaned_2" + p.substr(pos);
//			toFile(data, path);
//		}
//	}
}
