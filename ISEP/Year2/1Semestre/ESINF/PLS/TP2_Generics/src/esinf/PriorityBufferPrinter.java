/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author DEI-ISEP
 * @param <E> Generic type of buffer element
 */
public class PriorityBufferPrinter<E extends Document> implements Iterable<E> {

    private final ArrayList<E> buffer;
    private final Integer maxSize;

    /**
     * Creates a new PriorityBufferPrinter with a maximum buffer size maxSize
     *
     * @param maxSize maximum buffer size
     */
    public PriorityBufferPrinter(Integer maxSize) {
        buffer = new ArrayList<>(); // Diamond notation: the type can be inferred by the compiler
        this.maxSize = maxSize;
    }

    /**
     * Adds a document to the buffer.Documents are added using a numeric
     * priority system, being FIFO when priorities are equal.
     *
     * @param doc the document to be added to the buffer
     * @return true if the buffer has space, false otherwise
     */
    public Boolean addDocument(E doc) {
        int usedSize=0;
        for (int i = 0; i < buffer.size(); i++) {
            usedSize+= buffer.get(i).getSize();
        }
        if (usedSize+ doc.getSize() >= maxSize) {
            return false;
        }
        int i=0;
        while (i<buffer.size()){
            if(buffer.get(i).compareTo(doc)>0){
                buffer.add(i,doc);
                return true;
            }
        }
       
        return true;
    }

    /**
     * Gets the next document in the queue.
     *
     * @return The next document in the queue
     */
    public E getDocument() {
        E e1 = buffer.get(0);
        buffer.remove(0);
        return e1;

    }

    /**
     * Deletes a document from the buffer (if it exists), given a name and an
     * author
     *
     * @param name the name of the Document
     * @param author the author of the Document
     * @return true if the document existed, false otherwise
     */
    public Boolean delDocument(String name, String author) {
         int i=0;
        while (i<buffer.size()){
            if(buffer.get(i).getName().equals(name) && buffer.get(i).getAuthor().equals(author)){
                buffer.remove(i);
                i++;
                return true;
            }
        }
       
        return true;
   
//        for (E e : buffer) {
//            if (e.getAuthor().equals(author) && e.getName().equals(name)) {
//                buffer.remove(e);
//                return true;
//            }
//
//        }
//        return false;
    }

    /**
     * Deletes all the documents which are superior to a given size
     *
     * @param size the size above which documents will be deleted
     * @return true if there is at least one Document size
     */
    public Boolean delDocumentsAbove(Integer size) {
           int i=0;
        while (i<buffer.size()){
            if(buffer.get(i).getSize() >size)){
               buffer.remove(i);
               return true;
        }
//        for (E e : buffer) {
//            if (e.getSize() > size) {
//                buffer.remove(e);
//                return true;
//            }
//
       }
        return false;
    }

    @Override
    public String toString() {
        Iterator<E> it = buffer.iterator();
        String a = "";
        while (it.hasNext()) {
            E item = it.next();
            a = a + "Autor" + item.getAuthor() + " Name:" + item.getName()
                    + " Prioraty" + item.getPriority().toString()
                    + " Size" + item.getSize().toString();
        }
        return a;
    }

    @Override
    public Iterator<E> iterator() {
      Iterator <E> eList= buffer.iterator();
      return eList;
    }

}
