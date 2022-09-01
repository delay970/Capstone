package models;

import java.util.Objects;

public class NGram implements Comparable<NGram>{
	public String ngram;
	public int size;
	public int unique;
	public int total;

	public NGram() {
		super();
	}

	public NGram(String ngram, int size, int unique, int total) {
		super();
		this.ngram = ngram;
		this.size = size;
		this.unique = unique;
		this.total = total;
	}

	public void increment() {
		unique++;
		total++;
	}
	
	public void decrement() {
		unique--;
	}
	
	public String getNgram() {
		return ngram;
	}

	public void setNgram(String ngram) {
		this.ngram = ngram;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getUnique() {
		return unique;
	}

	public void setUnique(int unique) {
		this.unique = unique;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ngram, size, total, unique);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NGram other = (NGram) obj;
		return Objects.equals(ngram, other.ngram) && size == other.size && total == other.total
				&& unique == other.unique;
	}

	@Override
	public String toString() {
		return "NGram [ngram=" + ngram + ", size=" + size + ", unique=" + unique + ", total=" + total + "]";
	}

	@Override
	public int compareTo(NGram ngram) {
		int result = this.size - ngram.size;
		if(result == 0) {
			result = this.ngram.compareTo(ngram.getNgram());
		}
		return result;
	}
	
	

}
