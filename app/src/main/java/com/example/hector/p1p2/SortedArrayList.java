package com.example.hector.p1p2;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("serial")
public class SortedArrayList<E extends Comparable<E>> implements SortedList<E>,Serializable {
	private int currentSize;

	private E elements[];

	@SuppressWarnings("unchecked")
	public SortedArrayList() {
		currentSize = 0;
		elements = (E[]) new Object[1];
	}

	public boolean isMember(E name) {
		for (int i = 0; i < this.elements.length; i++) {
			if (this.elements[i].toString().equals(name.toString()))
				return true;
		}
		return false;
	}

	private void reAllocate() {
		@SuppressWarnings("unchecked")
		E reAllocatedSpace[] = (E[]) new Object[2 * this.elements.length];
		for (int i = 0; i < this.currentSize; ++i)
			reAllocatedSpace[i] = this.elements[i];

		this.elements = reAllocatedSpace;

	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Argument object cannot be null.");
		}
		int target = -1;
		for (int i = 0; i < this.currentSize; ++i) {
			if (this.elements[i].equals(obj)) {
				target = i;
				break;
			}
		}
		if (target == -1) {
			return false;
		} else {
			for (int i = target; i < this.currentSize - 1; ++i) {
				this.elements[i] = this.elements[i + 1];
			}
			this.elements[--this.currentSize] = null;
			return true;
		}
	}

	@Override
	public boolean remove(int index) {
		if (index >= 0 && index < this.currentSize) {
			for (int i = index; i < this.currentSize - 1; ++i) {
				this.elements[i] = this.elements[i + 1];
			}
			this.elements[--this.currentSize] = null;
			return true;

		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public int removeAll(E obj) {
		int quantity = 0;
		while (this.remove(obj)) {
			quantity++;
		}
		return quantity;
	}

	@Override
	public E first() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.elements[0];
		}
	}

	@Override
	public E last() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.elements[this.currentSize - 1];
		}
	}

	@Override
	public E get(int target) {
		if (target >= 0 && target < elements.length) {
			return this.elements[target];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.currentSize; ++i) {
			this.elements[i] = null;
		}
		this.currentSize = 0;
	}

	@Override
	public boolean contains(E obj) {
		return this.firstIndex(obj) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int firstIndex(E obj) {
		for (int i = 0; i < this.currentSize; ++i) {
			if (this.elements[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		for (int i = this.currentSize - 1; i >= 0; --i) {
			if (this.elements[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void add(E obj) {
		if (obj == null)
			throw new IllegalArgumentException("Argument can't be null.");
		if (this.currentSize == this.elements.length)
			reAllocate();
		
		boolean flag = false;

		if(this.isEmpty()){
			this.elements[currentSize++] = obj;
		}
		else {
			if (this.currentSize == this.elements.length) {
				reAllocate();
			} 
			
			else {
				for (int i = 0; i < this.currentSize; i++) {
					if (((Comparable<E>) this.elements[i]).compareTo(obj) > 0) {
						this.add(i, obj);	
						flag = true;
						break;
					} 
						
				}
				if(flag == false){
					this.elements[currentSize++] = obj;
				}
			}
		}
	}


	@Override
	public void add(int index, E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument object cannot be null.");
		}
		if (index == this.size()){
			this.add(obj);
		}
		else if (index >= 0 && index < this.currentSize){
			if (this.currentSize == this.elements.length){
				reAllocate();
			}
			// move everybody one spot to the back
			for (int i=this.currentSize; i > index; --i){
				this.elements[i] = this.elements[i-1];
			}
			// add element at position index
			this.elements[index] = obj;
			this.currentSize++;
		}
		else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public E set(int index, E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Object cannot be null");
		}
		if (index>=0 && index < this.size()){
			E temp = this.elements[index];
			this.elements[index] = obj;
			return temp;
		}
		else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public ListIterator<E> iterator(int index) {
		return new ListIterator<E>(index);
	}


	@SuppressWarnings("hiding")
	private class ListIterator<E> implements Iterator<E>{

		private int currentPosition;

		public ListIterator(int index){
			this.currentPosition = index;
		}

		@Override
		public boolean hasNext() {
			return this.currentPosition < size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (this.hasNext()){
				return (E) elements[this.currentPosition++];
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
