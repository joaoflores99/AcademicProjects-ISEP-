/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;
 
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;
 
 
/**
 *
 * @author DEI-ISEP
 * @param <E> Generic list element type
 */
public class DoublyLinkedList<E> implements Iterable<E>, Cloneable {
 
// instance variables of the DoublyLinkedList
    private final Node<E> header;     // header sentinel
    private final Node<E> trailer;    // trailer sentinel
    private int size = 0;       // number of elements in the list
    private int modCount = 0;   // number of modifications to the list (adds or removes)
 
    /**
     * Creates both elements which act as sentinels
     */
    public DoublyLinkedList() {
 
        header = new Node<>(null, null, null);      // create header
        trailer = new Node<>(null, header, null);   // trailer is preceded by header
        header.setNext(trailer);                    // header is followed by trailer
    }
 
    /**
     * Returns the number of elements in the linked list
     *
     * @return the number of elements in the linked list
     */
    public int size() {
        return size;
    }
 
    /**
     * Checks if the list is empty
     *
     * @return true if the list is empty, and false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }
 
    /**
     * Returns (but does not remove) the first element in the list
     *
     * @return the first element of the list
     */
    public E first() {
        if (!isEmpty()) {
            return header.getNext().getElement();
        }
        return null;
    }
 
    /**
     * Returns (but does not remove) the last element in the list
     *
     * @return the last element of the list
     */
    public E last() {
        if (!isEmpty()) {
            return trailer.getPrev().getElement();
        }
        return null;
    }
 
// public update methods
    /**
     * Adds an element e to the front of the list
     *
     * @param e element to be added to the front of the list
     */
    public void addFirst(E e) {
        addBetween(e, header, header.next);
    }
 
    /**
     * Adds an element e to the end of the list
     *
     * @param e element to be added to the end of the list
     */
    public void addLast(E e) {
        addBetween(e, trailer.prev, trailer);
    }
 
    /**
     * Removes and returns the first element of the list
     *
     * @return the first element of the list
     */
    public E removeFirst() {
        if (!isEmpty()) {
            E e = remove(header.next);
           
            return e;
        }
       
        return null;
    }
 
    /**
     * Removes and returns the last element of the list
     *
     * @return the last element of the list
     */
    public E removeLast() {
        if (!isEmpty()) {
            E e = remove(trailer.prev);
 
            return e;
        }
       
        return null;
    }
   
// private update methods
 
    /**
     * Adds an element e to the linked list between the two given nodes.
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node node = new Node(e, predecessor, successor);
       
        node.setNext(successor);
        node.setPrev(predecessor);
        predecessor.setNext(node);
        successor.setPrev(node);
       
        size++;
        modCount++;
    }
 
    /**
     * Removes a given node from the list and returns its content.
     */
    private E remove(Node<E> node) {
        (node.next).setPrev(node.getPrev());
        (node.prev).setNext(node.getNext());
       
        size--;
        modCount++;
       
        return node.getElement();
    }
 
// Overriden methods        
 
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DoublyLinkedList<?> other = (DoublyLinkedList<?>) obj;
        if (this.size != other.size) {
            return false;
        }
        ListIterator <?> itr = other.listIterator();
        ListIterator <?> itr2 = this.listIterator();
        while (itr.hasNext()) {
            if (!itr.next().equals(itr2.next())) {
                return false;
            }
        }
        return true;
    }
   
   
    @Override
    public Object clone() throws CloneNotSupportedException {
        DoublyLinkedList<E> clonedList = new DoublyLinkedList<>();
 
        for (E element : this) {
            clonedList.addLast(element);
        }
 
        return clonedList;
 
    }
   
//---------------- nested DoublyLinkedListIterator class ----------------        
    private class DoublyLinkedListIterator implements ListIterator<E> {
 
        private DoublyLinkedList.Node<E> nextNode,prevNode,lastReturnedNode; // node that will be returned using next and prev respectively
        private int nextIndex;  // Index of the next element
        private int expectedModCount;  // Expected number of modifications = modCount;
 
        public DoublyLinkedListIterator() {
            this.prevNode = header;
            this.nextNode = header.getNext();
            lastReturnedNode = null;
            nextIndex = 0;
            expectedModCount = modCount;
        }
 
        final void  checkForComodification() {  // invalidate iterator on list modification outside the iterator
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }        
       
        @Override
        public boolean hasNext() {
            if (nextNode != trailer) {
                return true;
            }
            return false;
        }
 
        @Override
        public E next() throws NoSuchElementException {
            checkForComodification();
            if (hasNext()) {
                lastReturnedNode = nextNode;
                prevNode = nextNode;
                nextNode = nextNode.getNext();
               
                nextIndex++;
               
                return lastReturnedNode.getElement();
            }
           
            lastReturnedNode = null;
           
            throw new NoSuchElementException("End of list reached.");
        }
 
        @Override
        public boolean hasPrevious() {      
            if (prevNode != header) {
                return true;
            }
            return false;
        }
 
        @Override
        public E previous() throws NoSuchElementException{
            checkForComodification();
            if (hasPrevious()) {
                lastReturnedNode = prevNode;
                nextNode = prevNode;
                prevNode = prevNode.getPrev();
               
               
                nextIndex--;
               
                return lastReturnedNode.getElement();
            }
           
            lastReturnedNode = null;
           
            throw new NoSuchElementException("Beginning of list reached.");
        }
 
        @Override
        public int nextIndex() {
            return nextIndex;
        }
 
        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }
 
        @Override
        public void remove() throws NoSuchElementException {
            checkForComodification();
           
            if (lastReturnedNode == null || DoublyLinkedList.this.isEmpty()) {
                throw new NoSuchElementException();
            }
           
            prevNode = lastReturnedNode.getPrev();
            nextNode = lastReturnedNode.getNext();
           
            DoublyLinkedList.this.remove(lastReturnedNode);
             
            expectedModCount++;
           
        }
 
        @Override
        public void set(E e) throws NoSuchElementException {
            if (lastReturnedNode==null) throw new NoSuchElementException();
            checkForComodification();
           
            lastReturnedNode.setElement(e);
        }
 
        @Override
        public void add(E e) {
            checkForComodification();
 
            DoublyLinkedList.this.addBetween(e, prevNode, nextNode);
            expectedModCount++;
            prevNode = nextNode.getPrev();
        }
 
    }    //----------- end of inner DoublyLinkedListIterator class ----------
   
//---------------- Iterable implementation ----------------
    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }
   
    public ListIterator<E> listIterator() {
        return new DoublyLinkedListIterator();
    }
   
//---------------- nested Node class ----------------
    private static class Node<E> {
 
        private E element;      // reference to the element stored at this node
        private Node<E> prev;   // reference to the previous node in the list
        private Node<E> next;   // reference to the subsequent node in the list
 
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
 
        public E getElement() {
            return element;
        }
 
        public Node<E> getPrev() {
            return prev;
        }
 
        public Node<E> getNext() {
            return next;
        }
       
        public void setElement(E element) { // Not on the original interface. Added due to list iterator implementation
            this.element = element;
        }        
 
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
 
        public void setNext(Node<E> next) {
            this.next = next;
        }
    } //----------- end of nested Node class ----------
   
}