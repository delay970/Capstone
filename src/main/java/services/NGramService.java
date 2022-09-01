package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.NGram;

public class NGramService {

	public List<String> stringToList(String c) {
		List<String> context = new ArrayList<String>();
		c.replaceAll("/n", " ");
		String[] temp = c.split(" ");
		context = Arrays.asList(temp);
		return context;
	}

	public Map<String, NGram> createNGrams(List<String> context, int n) {

		Map<String, NGram> ngrams = new HashMap<String, NGram>();

		for (int i = 0; i < context.size() - n+1; i++) {
			StringBuilder temp = new StringBuilder();

			// builds ngram starting at word i
			for (int j = i; j < n + i; j++) {
				temp.append(context.get(j) + " ");
			}
			temp.deleteCharAt(temp.length() - 1);

			NGram ngram = ngrams.get(temp.toString());

			// increments count if exists otherwises adds new ngram to map
			if (ngram != null) {
				ngram.increment();
			} else {
				ngram = new NGram(temp.toString(), n, 1, 1);
				ngrams.put(temp.toString(), ngram);
			}

			
		}

		return ngrams;
	}

	// This fucntion seems to not have the correct unique count for sub patterns
	// when in a larger pattern multiple times
	// unique count is also wrong if larger formlua is made up of the same
	// overlaping subformula
	// this seems to be an issues where if a formula starts and ends with the same
	// sub formula it misses a subtraction that it should have
	//
	public Map<String, NGram> mergeUp(Map<String, NGram> ngrams1, Map<String, NGram> ngrams2) {

		Map<String, NGram> results = new HashMap<String, NGram>();

		Map<String, Integer> left = new HashMap<String, Integer>();
		Map<String, Integer> right = new HashMap<String, Integer>();

		for (Map.Entry<String, NGram> entry : ngrams1.entrySet()) {
			left.put(entry.getKey(), entry.getValue().getTotal());
			right.put(entry.getKey(), entry.getValue().getTotal());
		}

		for (Map.Entry<String, NGram> entry : ngrams2.entrySet()) {
			String key = entry.getKey();
			NGram ngram = entry.getValue();
			String leftSubgram = key.substring(0, key.lastIndexOf(" "));
			String rightSubgram = key.substring(key.indexOf(" ") + 1);

			int leftCount = left.get(leftSubgram);

			leftCount -= ngram.getTotal();
			left.replace(leftSubgram, leftCount);

			int rightCount = right.get(rightSubgram);

			rightCount -= ngram.getTotal();
			right.replace(rightSubgram, rightCount);
		}

		for (Map.Entry<String, NGram> entry : ngrams1.entrySet()) {

			String key = entry.getKey();
			NGram ngram = entry.getValue();
			int leftCount = left.get(key);
			int rightCount = right.get(key);

			if (leftCount < 0 || rightCount < 0) { // if either is less than 0 then something went wrong
				System.out.println("opps");
				return null;
			}
			if (leftCount == 0 || rightCount == 0) { // if either count is 0 then it is not a formula
				continue;
			}

			if (leftCount < rightCount) {
				ngram.setUnique(leftCount);
				results.put(key, ngram);
			} else {
				ngram.setUnique(rightCount);
				results.put(key, ngram);
			}
		}

		return results;
	}

	public Map<String, NGram> findCommonElements(Map<String, NGram> ngrams1, Map<String, NGram> ngrams2) {
		Map<String, NGram> results = new HashMap<String, NGram>();

		// gets common elements
		Set<String> keySet = ngrams1.keySet();
		keySet.retainAll(ngrams2.keySet());

		// selects count with fewest apearences and rebuilds set to map
		for (String key : keySet) {
			NGram ng1 = ngrams1.get(key);
			NGram ng2 = ngrams2.get(key);
			if (ng1.getTotal() < ng2.getTotal()) {
				results.put(key, ng1);
			} else {
				results.put(key, ng2);
			}

		}

		return results;
	}

}
