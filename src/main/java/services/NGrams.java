package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NGrams {

	public static List<String> stringToList(String c) {
		List<String> context = new ArrayList<String>();
		String[] temp = c.split(" ");
		context = Arrays.asList(temp);
		return context;
	}

	public static List<String> genNGram(List<String> context, int n) {

		List<String> ngram = new ArrayList<String>();

		for (int i = 0; i < context.size() - n; i++) {
			StringBuilder temp = new StringBuilder();
			for (int j = i; j < n + i; j++) {
				temp.append(context.get(j) + " ");
			}
			temp.deleteCharAt(temp.length() - 1);
			ngram.add(temp.toString());
		}

		return ngram;
	}

	public static Map<String, Integer> removeDups(List<String> ngrams) {
		Map<String, Integer> count = new HashMap<String, Integer>();

		for (String ngram : ngrams) {
			Integer num = count.get(ngram);
			if (num != null) {
				num++;
				count.replace(ngram, num);
			} else {
				count.put(ngram, 1);
			}
		}

//		for (Map.Entry<String, Integer> entry : count.entrySet())
//			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		return count;
	}

	public static Map<String, Integer> mergeUp(Map<String, Integer> ngrams1, Map<String, Integer> ngrams2) {

		Map<String, Integer> results = new HashMap<String, Integer>();

		Map<String, Integer> left = new HashMap<String, Integer>(ngrams1);
		Map<String, Integer> right = new HashMap<String, Integer>(ngrams1);

		for (Map.Entry<String, Integer> entry : ngrams2.entrySet()) {
			String key = entry.getKey();
			String leftSubgram = key.substring(0, key.lastIndexOf(" "));
			String rightSubgram = key.substring(key.indexOf(" ") + 1);
System.out.println(leftSubgram);
			int leftCount = left.get(leftSubgram) - entry.getValue();
			left.replace(leftSubgram, leftCount);

			int rightCount = right.get(rightSubgram) - entry.getValue();
			right.replace(rightSubgram, rightCount);

		}

		for (Map.Entry<String, Integer> entry : ngrams1.entrySet()) {

			String key = entry.getKey();
			int leftCount = left.get(key);
			int rightCount = right.get(key);
			if (leftCount < 0 || rightCount < 0) {
				System.out.println("opps");
				return null;
			}
			if (leftCount == 0 || rightCount == 0) {
				continue;
			}

			if (leftCount < rightCount) {
				results.put(key, leftCount);
			} else {
				results.put(key, rightCount);
			}
		}

		return results;
	}
}
