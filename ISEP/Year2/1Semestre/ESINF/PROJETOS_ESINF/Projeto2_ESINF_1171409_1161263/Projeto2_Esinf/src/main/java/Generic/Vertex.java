/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generic;

/**
 *
 * @author morei
 */
import Generic.Edge;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */
public class Vertex<V, E> {
    
    private int key ;                     //Vertex key number
    private V  element ;                 //Vertex information
    private List<Pair<V, Edge<V,E>>> outVerts; //adjacent vertices
       
    public Vertex () { 
        key = -1; element = null; outVerts = new ArrayList<>();} 
    
    public Vertex (int k, V vInf) {
        key = k; element = vInf; outVerts = new ArrayList<>(); }
    
    public Vertex (Vertex<V,E> v) {
        key = v.getKey(); element = v.getElement(); 
        outVerts = new ArrayList<>(); 
        for (int i = 0; i < outVerts.size(); i++){
            V vert = v.outVerts.get(i).getKey();
            Edge<V,E> edge = v.outVerts.get(i).getValue();
            Pair<V, Edge<V,E>> pair = new Pair<>(vert, edge);
            outVerts.add(pair);
        }
    }
  
    public int getKey() { return key; }  
    public void setKey(int k) { key = k; }  
    
    public V getElement() { return element; }    
    public void setElement(V vInf) { element = vInf; }      
 
    public void addAdjVert(V vAdj, Edge<V,E> edge){ 
        Pair<V, Edge<V,E>> pair = new Pair<>(vAdj, edge);
        outVerts.add(pair); 
    }
    
    public V getAdjVert(Edge<V,E> edge){
        
        for (int i = 0; i < outVerts.size(); i++){
            Edge<V,E> e = outVerts.get(i).getValue();
            if (edge.equals(e)) {
                V vert = outVerts.get(i).getKey();
                return vert;
            }
        }
        
        return null;
    }
    
    public void remAdjVert(V vAdj){ 
        int i;
        for (i = 0; i < outVerts.size(); i++) {
            if (outVerts.get(i).getKey().equals(vAdj)) {
                break;
            }
        }
        outVerts.remove(i);
    }
    
    public Edge<V,E> getEdge(V vAdj){ 
        
        for (int i = 0; i < outVerts.size(); i++){
            if (outVerts.get(i).getKey().equals(vAdj)) {
                return outVerts.get(i).getValue();
            }
        }
        
        return null; 
    }
    
    public int numAdjVerts() { return outVerts.size();}
    
    public Iterable<V> getAllAdjVerts() {
        List<V> adjVerts = new ArrayList<>();
        for (int i = 0; i < outVerts.size(); i++){ 
            adjVerts.add(outVerts.get(i).getKey());
        }
        return adjVerts;
    }
    
    public Iterable<Edge<V,E>> getAllOutEdges() {  
        List<Edge<V,E>> outEdges = new ArrayList<>();
        for (int i = 0; i < outVerts.size(); i++){ 
            outEdges.add(outVerts.get(i).getValue());
        }
        return outEdges;
    }
         
    @Override
    public boolean equals(Object otherObj) {
        
        if (this == otherObj){
            return true;
        }
        
        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;
        
        Vertex<V,E> otherVertex = (Vertex<V,E>) otherObj;
        
        if (this.key != otherVertex.key)
            return false;
        
        if (this.element != null && otherVertex.element != null &&
                 !this.element.equals(otherVertex.element))
                return false;
         
        //adjacency vertices should be equal
        if (this.numAdjVerts() != otherVertex.numAdjVerts())
            return false;
        
        //and edges also
        Iterator<Edge<V,E>> it1 = this.getAllOutEdges().iterator();
        while (it1.hasNext()){
            Iterator<Edge<V,E>> it2 = otherVertex.getAllOutEdges().iterator();
            boolean exists=false;
            while (it2.hasNext()){
                if (it1.next().equals(it2.next()))
                   exists=true;
            }
            if (!exists)
                return false; 
        }
        return true;        
    }
     
    @Override
    public Vertex<V,E> clone() {
        
        Vertex<V,E> newVertex = new Vertex<>();
        
        newVertex.setKey(key); 
        newVertex.setElement(element);
        
        for (int i = 0; i < outVerts.size(); i++){
            V vert = outVerts.get(i).getKey();
            newVertex.addAdjVert(vert, this.getEdge(vert));
        }
 
        return newVertex;
    }
    
    @Override
    public String toString() {
        String st="";
        if (element != null)
            st = element + " (" + key + "): \n";
            if (!outVerts.isEmpty())
                for (int i = 0; i < outVerts.size(); i++){
                    st += outVerts.get(i).getValue();
                }
        return st; 
    }   
 
}
