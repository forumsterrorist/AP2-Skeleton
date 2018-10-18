package assignment2;

public class List<E extends Comparable<E>> implements ListInterface<E> {
	
	private Node current;
	private int elementCount;
	
	public List(){
		init();
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
    	current = null;
		elementCount = 0;
		
        return this;
    }


    @Override
    public ListInterface<E> insert(E d) {
    	if (!this.goToFirst()) {
    		current = new Node(d);
        	elementCount++;
        	return this;
    	} else {
    		while (d.compareTo(current.data) > 0) {
    			if (!this.goToNext()) {
    				Node node = new Node(d, current, null);
    				current.next = node;
    				current = node;
    				elementCount++;
    				return this;
    			}
    		}
    		if (current.prior == null) {
    			Node node = new Node(d, null, current);
    			current.prior = node;
    			current = node;
    			elementCount++;
    			return this; 
    		} else {
    			Node node = new Node(d, current.prior, current);
    			current.prior.next = node;
    			current.prior = node;
    			current = node;
    			elementCount++;
    			return this;
    		}
    	}
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
    	if (!goToFirst()) {
    		return false;
    	} else {
    		while (!current.data.equals(d)) {
    			if (!goToNext()) {
    				return false;
    			}
    		}
    		return true;
    	}
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
    	if (current != null && current.next != null && size() > 0 ) {
    		current = current.next;
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    @Override
    public boolean goToPrevious() {
    	if (current != null && current.prior != null && size() > 0) {
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