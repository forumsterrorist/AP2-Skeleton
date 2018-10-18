package assignment2;


public class Set<E extends Comparable <E>> implements SetInterface<E> {
	private ListInterface<E> list;
	
	Set() {
		init();
	}
	
	Set(Set<E> set) {
		list = set.list.copy();
	}

	public void init() {
		list = new List<E>();
		return;
	}

	public void add(E d) {
		if (this.isInList(d)) {
			return;
		} else {
			list.insert(d);
			return;
		}
	}

	public void remove(E toRemove) {
		if (!this.isInList(toRemove)) {
			return;
		} else {
			list.find(toRemove);
			list.remove();
		}
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

	public SetInterface<E> union(SetInterface<E> set2) {
		Set<E> output = new Set<E>((Set<E>) set2);
		E temp;
		
		if (this.list.goToFirst()) {
			do {
				temp = this.list.retrieve();
				output.add(temp);
			} while (this.list.goToNext());
		}
		
		return output;
	}

	public SetInterface<E> intersection(SetInterface<E> set2) {
		Set<E> output = new Set<>();
		Set<E> copyOfSet2 = new Set<>((Set<E>) set2);
		E temp;
		
		while(!copyOfSet2.isEmpty()) {
			temp = copyOfSet2.retrieve();
			if (this.isInList(temp)) {
				output.add(temp);
			}
			copyOfSet2.remove(temp);
		}
		
		return output;
	}

	public SetInterface<E> complement(SetInterface<E> set2) {
		Set<E> output = new Set<>(this);
		Set<E> copyOfSet2 = new Set<>((Set<E>) set2);
		E temp;
		
		while(!copyOfSet2.isEmpty()) {
			temp = copyOfSet2.retrieve();
			if (output.isInList(temp)) {
				output.remove(temp);
			}
			copyOfSet2.remove(temp);
		}
		
		return output;
	}

	public SetInterface<E> symmetricDifference(SetInterface<E> set2) {
		SetInterface<E> output = this.complement(set2);
		output = output.union(set2.complement(this));
		
		return output;
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