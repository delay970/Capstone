package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormulaFinder {
	public static Map<String, Integer> findRepatitions(List<String> context, int minSize) {
		Map<String, Integer> results = new HashMap();
		for (int i = minSize; i < context.size() / 2; i++) {
			List<String> ng = NGrams.genNGram(context, i);
			Map<String, Integer> ngrams1 = NGrams.removeDups(ng);
			ngrams1.values().removeIf(val -> 1 == val);
			
			ng = NGrams.genNGram(context, i);
			Map<String, Integer> ngrams2 = NGrams.removeDups(ng);
			ngrams2.values().removeIf(val -> 1 == val);
			
			if(ngrams2.isEmpty()) {
				break;
			}
			
			ngrams1 = NGrams.mergeUp(ngrams1, ngrams2);
			
			if(ngrams1.isEmpty()) {
				continue;
			}
			
			results.putAll(ngrams1);
		}
		
		for (Map.Entry<String, Integer> entry : results.entrySet()) {
			
			System.out.println(entry.getKey() +": "+entry.getValue());
			
		}
		
		return results;
	}
}
