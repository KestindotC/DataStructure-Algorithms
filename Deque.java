import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int inisize=0;	 // initial size of deque
	private Node first;      // index of first item
    private Node last;       // index of last item

	// construct an empty deque
	public Deque() {}
	   

	// is the deque empty?
	public boolean isEmpty() {
		return first == null;
	}                

	// return the number of items on the deque
	public int size() {
		return inisize;
	}                       


	// add the item to the front
	public void addFirst(Item item) {
		throwIfNull(item);
		Node newFirst = new Node();
		newFirst.item = item;
                                newFirst.previous = null;
		if(!isEmpty()){
			newFirst.next = first;
			first.previous = newFirst;
		}
		first = newFirst;
		if(last == null) last = first;

		inisize++;
	}         



	// add the item to the end
	public void addLast(Item item) {
		throwIfNull(item);
		Node newLast = new Node();
		newLast.item = item;
                                newLast.next=null;
		if(last!=null){
		last.next = newLast;
		newLast.previous = last;
		}
		last = newLast;
		if(isEmpty()) first = last;
		inisize++;
	}          

	// remove and return the item from the front
	public Item removeFirst() {
		throwIfEmpty();
		Node oldFirst = first;
		first = first.next;

		if(isEmpty())last=null;
         		else first.previous=null;

		inisize--;
		return oldFirst.item;
		}              


	// remove and return the item from the end
	public Item removeLast() {
		throwIfEmpty();
		Node oldLast = last;
		last = last.previous;
		if (last==null) first=null;
                //System.out.println("Empty!");}
		else last.next=null;

		inisize--;
		return oldLast.item;

	}                


	// return an iterator over items in order from front to end
	@Override
public Iterator<Item> iterator() {
            return new ListIterator();  }
       
	private class ListIterator implements Iterator<Item>{
		private Node current = first;

		@Override
		public boolean hasNext(){
			return current != null;
		}
@Override
		public Item next(){
			if (current == null) throw new NoSuchElementException();

			Item item = current.item;
			current = current.next;
			return item;
		}
@Override
		public void remove(){
		throw new UnsupportedOperationException();
		}


	}        

	
private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private void throwIfEmpty() {
        if (isEmpty ())
            throw new NoSuchElementException();
    }

    private void throwIfNull(Item item) {
        if (item == null)
            throw new NullPointerException();
    }

	public static void main(String[] args) {
		
//                Deque<Integer> tryyy = new Deque<Integer>();
//                System.out.println(tryyy.removeLast());
//                tryyy.addFirst(-3);               
//                tryyy.addFirst(-16456);
//                tryyy.addFirst(53);                           
//                tryyy.removeFirst();
//                tryyy.removeLast();
//                tryyy.removeLast(); 
                //System.out.println(tryyy.first.item);
                //System.out.println(tryyy.first.next.item);
                //System.out.println(tryyy.first.next.next.item);
                //System.out.println(tryyy.first.next.next.next.item);
//                System.out.println(tryyy.last);
//                System.out.println(tryyy.last.previous.item);
//                System.out.println(tryyy.last.previous.previous.item);
//                System.out.println(tryyy.last.previous.previous.previous.item);
                
                //System.out.println(tryyy.size());
//                Iterator<Integer> cal = tryyy.iterator();      
//                cal.next();
               
               
	} 



}




//Throw a java.lang.NullPointerException if the client attempts to add a null item
//Throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
//Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator
//Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.