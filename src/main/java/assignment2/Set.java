package assignment2;


public class Set<E extends Comparable <E>> implements SetInterface<E> {
	private List<E> list;
	
	public Set() {
		list = new List<E>();
	}

	public void init() {
		list = new List<E>();
		return;
	}

	public void add(E d) {		
		list.insert(d);
		return;
	}

	public void remove(E toRemove) {
		list.find(toRemove);
		list.remove();
	}

	public E retrieve() {
		return list.retrieve();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean isInList(E element) {
		return list.find(element);
	}

	public SetInterface<E> union(SetInterface<E> set1) {
		return null;
	}

	public SetInterface<E> intersection(SetInterface<E> set1) {
		return null;
	}

	public SetInterface<E> complement(SetInterface<E> set1) {
		return null;
	}

	public SetInterface<E> symmetricDifference(SetInterface<E> set1) {
		return null;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder("");
		boolean end;
		
		end = list.goToFirst();
		if (end == false) {
			return output.toString();
		}
		while (end) {
			output.append(this.retrieve().toString());
			output.append(' ');
			end = list.goToNext();
		}
		
		return output.toString().substring(0, output.toString().length() - 1);
	}
}