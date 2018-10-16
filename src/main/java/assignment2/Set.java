package assignment2;

public class Set<E> implements SetInterface {
	
	private List<E> current;
	private int top;
	
	Set<E>() {
		init(E e);
	}
	
	Set(Identifier input) {
		init();
		add(input);
	}
	
	Set(Set<E> input) {
		this.current = input.current;
	}
	
	

	@Override
	public void init(E e) {
		current = new List<e>;
	}
	
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private void grow() {
		Identifier[] temp = new Identifier[set.length + 1];
		
		for (int i = 0; i < set.length; i++) {
			temp[i] = set[i];
		}
		
		set = temp;
	}

	@Override
	public void add(Identifier input) {
			if (this.contains(input)) {
				return;
			} else if (top == set.length - 1) {
				grow();
			}
			top++;
			set[top] = input;
	}

	@Override
	public Identifier get() {
		return set[top];
	}

	@Override
	public void remove(Identifier input) {
		Identifier[] temp = new Identifier[set.length - 1];
		
		int counter = 0;
		
		for (int i = 0; i < temp.length; i++) {
			if (set[i].equals(input)) {
				continue;
			} else {
				temp[counter] = set[i];
				counter++;
			}
		}
		
		set = temp;
		top--;
	}

	@Override
	public int size() {
		return set.length;
	}

	@Override
	public boolean equals(Set input) {
		Set temp = new Set(input);
		Identifier compare;
		
		for (int i = 0; i < top; i++) {
			compare = temp.get();
			temp.remove(compare);
			if (!compare.equals(set[i])) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean contains(Identifier input) {
		
		for (int i = 0; i < set.length; i++) {
			if (set[i] == null) {
				continue;
			} else if (set[i].equals(input)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public SetInterface union(Set input) {
		SetInterface output = new Set();
		
		for (Identifier i : set) {
			output.add(i);
		}
		
		for (Identifier j : input.set) {
			output.add(j);
		}
		
		return output;
	}

	@Override
	public SetInterface intersection(Set input) {
		SetInterface output = new Set();
		
		for (Identifier i : set) {
			if (input.contains(i)) {
				output.add(i);
			}
		}
		
		return output;
	}

	@Override
	public SetInterface difference(Set input) {
		SetInterface output = new Set();
		
		for (Identifier i : set) {
			if (!input.contains(i)) {
				output.add(i);
			}
		}
		
		return output;
	}

	@Override
	public SetInterface symmetricDifference(Set input) {
		SetInterface output = new Set();
		
		for (Identifier i : set) {
			if (!input.contains(i)) {
				output.add(i);;
			}
		}
		
		for (Identifier i : input.set) {
			if (!this.contains(i)) {
				output.add(i);
			} 
		}
		
		return output;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder(" ");
		
		if (isEmpty()) {
			return output.toString();
		} else if (top == 0) {
			output.append(set[0].get());
			output.append(' ');
			return output.toString();
		}
		
		for (Identifier i : set) {
			output.append(i.get());
			output.append(" ");
		}
		
		return output.toString();
	}
}
