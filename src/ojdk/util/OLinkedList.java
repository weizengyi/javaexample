package ojdk.util;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OLinkedList<E> extends AbstractSequentialList<E> implements List<E>,Deque<E>,Cloneable,java.io.Serializable{

	
	transient int size = 0;
	
	transient Node<E> first ;
	
	transient Node<E> last ;
	
	public OLinkedList(){
		
	}
	
	public OLinkedList(Collection<? extends E> c){
		this();
		addAll(c);
	}
	
	private void linkFirst(E e){
		final Node<E> f = first ;
		final Node<E> newNode = new Node<>(null,e,f);
		first = newNode;
		if(f== null)
			last = newNode;
		else f.prev = newNode;
		size++ ;
		modCount++;
	}
	
	void linkLast(E e){
		final Node<E> l = last ;
		final Node<E> newNode = new Node<>(l,e,null);
		last = newNode;
		if(l == null)
			first = newNode;
		else
			l.next = newNode;
		size++;
		modCount++;
	}
	
	void linkBefore(E e,Node<E> succ){
		final Node<E> pred = succ.prev ;
		final Node<E> newNode = new Node<>(pred,e,succ);
		succ.prev = newNode;
		if(pred == null)
			first = newNode;
		else 
			pred.next = newNode;
		size++;
		modCount++;
	}
	
	private E unlinkFirst(Node<E> f){
		final E element = f.item ;
		final Node<E> next = f.next ;
		f.item = null ;
		f.next = null ;
		first = next ;
		if(next == null)
			last = null ;
		else 
			next.prev = null;
		size--;
		modCount++;
		return element;
	}
	
	private E unlinkLast(Node<E> l){
		final E element = l.item ;
		final Node<E> prev = l.prev ;
		l.item = null ;
		l.prev = null ;
		last = prev ;
		if(prev == null)
			first = null ;
		else
			prev.next = null;
		size -- ;
		modCount++;
		return element;
	}
	
	E unlink(Node<E> x){
		final E element = x.item;
		final Node<E> next = x.next ;
		final Node<E> prev = x.prev ;
		
		if(prev == null){
			first = next ;
		}else{
			prev.next = next;
			x.prev = null ;
		}
		
		if(next == null){
			last = prev;
		}else{
			next.prev = prev;
			x.next = null ;
		}
		
		x.item = null;
		size -- ;
		modCount++;
		return element;
	}
	
	public void add(int index,E element){
		checkPositionIndex(index);
		if(index == size)
			linkLast(element);
		else
			linkBefore(element,node(index));
	}
	
	public boolean add(E e){
		linkLast(e);
		return true;
	}
	
	@Override
	public void addFirst(E e) {
		linkFirst(e);
	}

	@Override
	public void addLast(E e) {
		linkLast(e);
	}

	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	@Override
	public E removeFirst() {
		final Node<E> f = first ;
		if( f == null)
			throw new NoSuchElementException();
		return unlinkFirst(f);
	}

	@Override
	public E removeLast() {
		final Node<E> l = last ;
		if(l == null)
			throw new NoSuchElementException();
		return unlinkLast(l);
	}

	@Override
	public E pollFirst() {
		final Node<E> f = first;
		return (f == null) ? null : unlinkFirst(f);
	}

	@Override
	public E pollLast() {
		final Node<E> f = last;
		return (f == null) ? null : unlinkLast(f);
	}

	@Override
	public E getFirst() {
		final Node<E> f = first;
		if(f == null)
			throw new NoSuchElementException();
		return f.item;
	}

	@Override
	public E getLast() {
		final Node<E> l = last ;
		if(l == null)
			throw new NoSuchElementException();
		return l.item;
	}

	@Override
	public E peekFirst() {
		final Node<E> f = first ;
		return (f==null) ?null :f.item;
	}

	@Override
	public E peekLast() {
		final Node<E> l = last ;
		return (l==null) ?null :l.item;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean remove(Object o){
		if(o == null){
			for(Node<E> x = first ; x != null ; x = x.next){
				if(x.item == null){
					unlink(x);
					return true;
				}
			}
		}else{
			for(Node<E> x = first; x != null ; x = x.next){
				if(o.equals(x.item)){
					unlink(x);
					return true;
				}
			}
		}
		return false ;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		return getFirst();
	}

	@Override
	public E peek() {
		final Node<E> f = first ;
		return (f==null)?null:f.item;
	}

	@Override
	public void push(E e) {
		addFirst(e);
		
	}

	@Override
	public E pop() {
		return removeFirst();
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	public int indexOf(Object o){
		int index = 0 ;
		if(o == null){
			for(Node<E> x = first ; x != null ; x = x.next){
				if(x.item == null)
					return index;
				index++;
			}
		}else {
			for(Node<E> x = first ; x!= null ; x = x.next){
				if(o.equals(x.item))
					return index;
				index ++ ;
			}
		}
		return -1 ;
	}
	
	public int lastIndexOf(Object o){
		int index = size ;
		if(o == null){
			for(Node<E> x = last ; x!= null ; x = x.prev){
				index -- ;
				if(x.item == null)
					return index ;
			}
		}else {
			for(Node<E> x = last ; x != null ;x = x.prev){
				index--;
				if(o.equals(x.item))
					return index;
			}
		}
		return -1 ;
	}
	
	private static class Node<E>{
		E item;
		Node<E> next ;
		Node<E> prev ;
		
		Node(Node<E> prev, E element , Node<E> next){
			this.item = element ;
			this.next = next ;
			this.prev = prev ;
		}
	}
	
	Node<E> node(int index){
		if(index < (size >> 1 )){
			Node<E> x = first ;
			for(int i = 0 ;i < index ;i ++)
				x = x.next ;
			return x ;
		}else{
			Node<E> x = last ;
			for(int i = size -1 ; i>index ; i--)
				x = x.prev ;
			return x ;
		}
	}
	
	private boolean isElementIndex(int index){
		return index >= 0 && index < size;
	}
	
	private boolean isPositionIndex(int index){
		return index >= 0 && index <= size ;
	}
	
	private String outOfBoundsMsg(int index){
		return "Index: "+index + ",Size: "+size;
	}
	
	private void checkElementIndex(int index){
		if(!isElementIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
	
	private void checkPositionIndex(int index){
		if(!isPositionIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
	
	private class ListItr implements ListIterator<E>{
		
		private Node<E> lastReturned ;
		private Node<E> next ;
		private int nextIndex;
		private int expectedModCount = modCount ;
		
		ListItr(int index){
			next = (index == size)? null : node(index);
			nextIndex = index ;
		}

		@Override
		public boolean hasNext() {
			
			return nextIndex < size ;
		}

		@Override
		public E next() {
			checkForComodification();
			if(!hasNext())
				throw new NoSuchElementException();
			lastReturned = next ;
			next = next.next;
			nextIndex++;
			return lastReturned.item;
		}

		@Override
		public boolean hasPrevious() {
			return nextIndex > 0;
		}

		@Override
		public E previous() {
			checkForComodification();
			if(!hasPrevious())
				throw new NoSuchElementException();
			lastReturned = next = (next == null)? last : next.prev;
			nextIndex -- ;
			return lastReturned.item;
		}

		@Override
		public int nextIndex() {
			
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			
			return nextIndex-1;
		}

		@Override
		public void remove() {
			checkForComodification();
			if(lastReturned == null)
				throw new IllegalStateException();
			Node<E> lastNext = lastReturned.next;
			unlink(lastReturned);
			if(next == lastReturned)
				next = lastNext;
			else
				nextIndex--;
			lastReturned = null;
			expectedModCount++;
			
		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub
			
		}
		
		final void checkForComodification(){
			if(modCount != expectedModCount)
				throw new ConcurrentModificationException();
		}
		
	}
	
	
	
	

}
