package calculator2;

public class List<E extends Comparable> implements ListInterface<E>{

public class List<E extends Comparable<E>> implements ListInterface<E> {


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
        
        public int compareTo(Node other) {
        	return this.data.compareTo(other.data);
        }

    }

    Node current;

    List(){
        current = null;
    }

    @Override
    public boolean isEmpty() {
        return ( current == null );
    }

    @Override
    public ListInterface<E> init() {
        current = null;
        return this;
    }

    @Override
    public int size() {
        int size = 0;

        if (current == null) {
            return size;
        }
        Node initialCurrent = current;
        goToFirst();
        size++; //First node counted in
        
        while (current.next != null) {
        	current = current.next;
            size++;
        }
        current = initialCurrent;

        return size;
    }
    
    @Override
    public ListInterface<E> insert(E d) {
		Node temp = new Node(d);
		
		if (current == null) {
			current = temp;
			return this;
		}
		//If the current node is smaller or equal to new node move new node to the right
		if (current.compareTo(temp) <= 0) {
			while (current.next != null) {
				current = current.next;
				if (current.compareTo(temp) > 0) {
					current = current.prior;
					break;
				}
			}
			temp.prior = current;
			temp.next = current.next;
			if (temp.next != null) {
				current.next.prior = temp;
			}
			current.next = temp;
			current = temp;
		//If the current node is bigger to new node move new node to the left
		} else if (current.compareTo(temp) > 0) {
			while (current.prior != null) {
				current = current.prior;
				if (current.compareTo(temp) < 0) {
					current = current.next;
					break;
				}
			}
			temp.next = current;
			temp.prior = current.prior;
			if (temp.prior != null) {
				current.prior.next = temp;
			}
			current.prior = temp;
			current = temp;
		}
		return this;
    }

    @Override
    public E retrieve(){
        if (current == null) {
            //throw new APException("The list is empty.");
        	return null;
        } else {
            return current.data;
        }
    }

    @Override
    public ListInterface<E> remove() {
        if (current == null) {
            return this;
        }
        //Correct previous and next nodes (if any) to skip current node
        if (current.prior != null) {
            if (current.next != null) {
                current.prior.next = current.next;
                current.next.prior = current.prior;
                current = current.next;
            } else {
                current.prior.next = null;
                current = current.prior;
            }
        } else {
            if (current.next != null) {
                current.next.prior = null;
                current = current.next;
            } else {
                current = null;
            }
        }
        return this;
    }

    @Override
    public boolean find(E d) {
        if (current == null) {
          return false;
        }
        Node temp = new Node(d);
        //Iterate through the list and return true if node match found
        goToFirst();
        if (current.compareTo(temp) == 0) {
        	return true;
        }
        while (current.next != null) {
        	current = current.next;
        	if (current.compareTo(temp) == 0) {
            	return true;
            }
        }
        //If node was not found - set 'current' to the correct node
        goToFirst();
        if (current.compareTo(temp) > 0) {
        	return false;
        }
        while (current.next != null) {
        	current = current.next;
        	if (current.compareTo(temp) > 0) {
        		current = current.prior;
            	return false;
            }
        }
        return false;
    }

    @Override
    public boolean goToFirst() {
        if (current == null) {
            return false;
        }
        Node temp = current;
        while (temp.prior != null) {
            temp = temp.prior;
        }
        current = temp;
        return true;
    }

    @Override
    public boolean goToLast() {
      if (current == null) {
          return false;
      }
      Node temp = current;
      while (temp.next != null) {
          temp = temp.next;
      }
      current = temp;
      return true;
    }

    @Override
    public boolean goToNext() {
        if (current == null || current.next == null) {
            return false;
        }
        current = current.next;
        return true;
    }

    @Override
    public boolean goToPrevious() {
        if (current == null || current.prior == null) {
            return false;
        }
        current = current.prior;
        return true;
    }

    @Override
    public ListInterface<E> copy() {
        List result = new List();
        
        if (current == null) {
        	return result;
        }
        //Make a backup of current node
        Node initialCurrent = current;
        Node temp;
        
        goToFirst();
        //Deep copy the list
        while (current.next != null) {
        	result.insert(current.data);
        }
        //Restore current node
        current = initialCurrent;
        
        return result;
    }

}
