package services;

public class FileCleaner {

	
	public static void testModifyStringInMethod(String test) {
		test = test.toLowerCase().replace('l', 'f');
		System.out.println(test);
	}
	
	public static void cleanLine(String Line) {
		
	}
//	
//	public String readFile(String filepath) {
//		string data = "";
//		ifstream reader;
//		reader.open(filepath, ios::in);
//		char c;
//		while (reader.get(c)) {
//			data += c;
//		}
//		return data;
//	}
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
