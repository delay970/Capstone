package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.NGram;

public class FormulaFinder {
	public static Map<String, NGram> findRepatitions(String text, int minSize) {

		Map<String, NGram> results = new HashMap<String, NGram>();
		NGramService ngramService = new NGramService();

		List<String> context = ngramService.stringToList(text);

		for (int i = minSize; i < context.size() / 2; i++) {
			Map<String, NGram> ngrams1 = ngramService.createNGrams(context, i);

			ngrams1.values().removeIf(ngram -> 1 == ngram.getTotal());

			Map<String, NGram> ngrams2 = ngramService.createNGrams(context, i + 1);
			ngrams2.values().removeIf(ngram -> 1 == ngram.getTotal());

			if (ngrams1.isEmpty()) {
				break;
			}

			Map<String, NGram> temp = ngramService.mergeUp(ngrams1, ngrams2);

			if (temp.isEmpty()) {
				continue;

			}

			results.putAll(temp);

		}
		
		return results;
	}

	public static Map<String, NGram> findFormulas(String text1, String text2, int minSize) {
		Map<String, NGram> results = new HashMap<String, NGram>();
		NGramService ngramService = new NGramService();

		List<String> context1 = ngramService.stringToList(text1);
		List<String> context2 = ngramService.stringToList(text2);

		int size;
		if (context1.size() < context2.size()) {
			size = context1.size();
		} else {
			size = context2.size();
		}

		for (int i = minSize; i < size / 2; i++) {
			// get ngrams of docs 1 and 2 of length i
			Map<String, NGram> doc1ngrams = ngramService.createNGrams(context1, i);
			Map<String, NGram> doc2ngrams = ngramService.createNGrams(context2, i);

			Map<String, NGram> ngrams1 = ngramService.findCommonElements(doc1ngrams, doc2ngrams);

			doc1ngrams = ngramService.createNGrams(context1, i + 1);
			doc2ngrams = ngramService.createNGrams(context2, i + 1);

			Map<String, NGram> ngrams2 = ngramService.findCommonElements(doc1ngrams, doc2ngrams);

			if (ngrams1.isEmpty()) {
				break;
			}

			Map<String, NGram> temp = ngramService.mergeUp(ngrams1, ngrams2);

			if (temp.isEmpty()) {
				continue;

			}

			results.putAll(temp);

		}

		return results;
	}

	public static void cleanResults(List<NGram> results) {
		NGramService ngramService = new NGramService();
		for (int i = 0; i < results.size(); i++) {
			NGram ngram = results.get(i); // result we are varifing
			int total = ngram.getTotal();

			if (total == ngram.getUnique()) {
				continue;
			}

			String ngramString = ngram.getNgram();

			for (int j = 0; j < i; j++) {
				NGram result = results.get(j); // known result
				
				if(result.getUnique() == 0) {
					continue;
				}
				
				String resultString = result.getNgram();

				int pos = resultString.indexOf(ngramString);
				int count = 0;
				while (pos != -1) {
					count++;
					pos = resultString.indexOf(ngramString, pos + 1);
				}
				
				total -= count * result.getUnique();
			}
			
			ngram.setUnique(total);
			
		}
		results.removeIf(ngram -> ngram.getUnique()==0);
	}
	
	
}
