package models;

import java.util.Objects;

public class Sutra {
	public String name;
	public String context;
	
	public Sutra(String name, String context) {
		super();
		this.name = name;
		this.context = context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public int hashCode() {
		return Objects.hash(context, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sutra other = (Sutra) obj;
		return Objects.equals(context, other.context) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Sutra [name=" + name + ", context=" + context + "]";
	}
	
	
	
}
