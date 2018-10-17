package assignment2;

public class List<E extends Comparable<E>> implements ListInterface<E> {
	
	private Node current;
	private int elementCount;
	
	List(){
		current = null;
		elementCount = 0;
	}
	
    private class Node {

        E data;
        Node prior, next;

        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }
    }

    @Override
    public boolean isEmpty() {
    	if (size() == 0) {
    		return true;
    	}
        return false;
    }

    @Override
    public ListInterface<E> init() {
    	ListInterface<E> output = new List<E>();
    	
        return output;
    }


    @Override
    public ListInterface<E> insert(E d) {
    	if (size() == 0) {
	    	current = new Node(d);
	    	elementCount++;
	    	return this;
    	}
    /*	goToFirst();
    	while(d.compareTo(current.data) > 0 && goToNext() == true) {
    	}
    	if (current.prior == null) {
    		Node newNode = new Node(d, null, current);
    		current.prior = newNode;
    		current = newNode;
    		elementCount++;
            return this;
    	}
    	else if(current.next == null) {
    		Node newNode = new Node(d, current, null);
    		current.next = newNode;
    		current = newNode;
    		elementCount++;
            return this;
    	}
    	else {
    		Node newNode = new Node(d, current, current.next);
    		current = newNode;
    		current.prior.next = current;
    		current.next.prior = current;
    		elementCount++;
            return this;
    	}*/
    	goToLast();
    	Node newNode = new Node(d, current, null);
		current.next = newNode;
		current = newNode;
		elementCount++;
        return this;
    }
    
    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public E retrieve() {
    	if (isEmpty() == false) {
            return current.data;
    	}
    	return null;
    }

    @Override
    public ListInterface<E> remove() {
    	if (isEmpty() == true) {
    		return null;
    	}
    	if (size() == 1) {
    		current = null;
    	}
    	else if (current.next == null) {
    		current = current.prior;
    		current.next = null;
    	}
    	else if (current.prior == null) {
    		current = current.next;
    		current.prior = null;
    	}
    	else {
    		current.prior.next = current.next;
    		current.next.prior = current.prior;
    		current = current.next;
    	}
    	elementCount--;
        return this;
    }

    @Override
    public boolean find(E d) {
    	if (isEmpty() == true){
			return false;
		}
    	goToFirst();
    	while (d != current.data && goToNext() == true) {
    	}
    	if (d == current.data) {
    		return true;
    	}
    	if (goToFirst() == true && d.compareTo(current.data) < 0) {
    		return false;
    	}
    	while (d.compareTo(current.data) > 0  && goToNext() == true) {
    	}
    	return false;
    }

    @Override
    public boolean goToFirst() {
    	if (size() == 0) {
    		return false;
    	} else {
    		while (current.prior != null) {
    			current = current.prior;
    		}
    		return true;
    	}
    }

    @Override
    public boolean goToLast() {
    	if (size() == 0) {
    		return false;
    	} else {
    		while (current.next != null) {
    			current = current.next;
    		}
    		return true;
    	}
    }

    @Override
    public boolean goToNext() {
    	if (current.next != null && size() > 0  ) {
    		current = current.next;
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    @Override
    public boolean goToPrevious() {
    	if (current.prior != null && size() > 0) {
    		current = current.prior;
    		return true;
    	}	
        return false;
    }

    @Override
    public ListInterface<E> copy() {
    	List<E> result = new List<E>();
    	Node temp;
    	
    	if (goToFirst() == true) {
    		temp = current;
			result.insert(temp.data);
    		while(goToNext() == true) {
    			temp = current;
    			result.insert(temp.data);
    		}
    	}
        return result;
    }
}