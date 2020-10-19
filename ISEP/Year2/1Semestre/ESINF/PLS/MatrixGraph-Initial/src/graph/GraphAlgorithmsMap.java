package graph;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * Implementation of graph algorithms for a (undirected) graph structure
 * Considering generic vertex V and edge E types
 *
 * Works on AdjancyMatrixGraph objects
 *
 * @author DEI-ESINF
 *
 */
public class GraphAlgorithmsMap {

    private static <T> LinkedList<T> reverse(LinkedList<T> list) {
        LinkedList<T> reversed = new LinkedList<T>();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            reversed.push(it.next());
        }
        return reversed;
    }

    /**
     * Performs depth-first search of the graph starting at vertex. Calls
     * package recursive version of the method.
     *
     * @param graph Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (including vertex), null if
     * vertex does not exist
     *
     */
    public static <V, E> LinkedList<V> BFS(AdjacencyMatrixGraph<V, E> graph, V vertex) {

        int index = graph.toIndex(vertex);
        if (index == -1) {
            return null;
        }
        LinkedList<V> resultQueue = new LinkedList<V>();
        boolean[] knownVertices = new boolean[graph.numVertices];

        LinkedList<Integer> auxQueue = new LinkedList<Integer>();

        resultQueue.add(graph.vertices.get(index));
        auxQueue.add(index);

        knownVertices[index] = true;

        while (!auxQueue.isEmpty()) {
            index = auxQueue.remove();
            for (int i = 0; i < graph.numVertices; i++) {
                if (graph.edgeMatrix[index][i] != null) {
                    int dest = i;
                    if (knownVertices[dest] == false) {
                        knownVertices[dest] = true;
                        resultQueue.add(graph.vertices.get(dest));
                        auxQueue.add(dest);
                    }
                }
            }
        }
        return resultQueue;
    }

    /**
     * Performs depth-first search of the graph starting at vertex. Calls
     * package recursive version of the method.
     *
     * @param graph Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (empty if none), null if vertex
     * does not exist
     */
    public static <V, E> LinkedList<V> DFS(AdjacencyMatrixGraph<V, E> graph, V vertex) {
        int index = graph.toIndex(vertex);
        if (index == -1) {
            return null;
        }

        LinkedList<V> resultQueue = new LinkedList<V>();
        resultQueue.add(vertex);
        boolean[] knownVertices = new boolean[graph.numVertices];
        DFS(graph, index, knownVertices, resultQueue);
        return resultQueue;

    }

    /**
     * Actual depth-first search of the graph starting at vertex. The method
     * adds discovered vertices (including vertex) to the queue of vertices
     *
     * @param graph Graph object
     * @param index Index of vertex of graph that will be the source of the
     * search
     * @param known previously discovered vertices
     * @param verticesQueue queue of vertices found by search
     *
     */
    static <V, E> void DFS(AdjacencyMatrixGraph<V, E> graph, int index, boolean[] knownVertices, LinkedList<V> verticesQueue) {
        knownVertices[index] = true;
        for (int i = 0; i < graph.numVertices; i++) {
            if (graph.edgeMatrix[index][i] != null && knownVertices[i] == false) {
                verticesQueue.add(graph.vertices.get(i));
                DFS(graph, i, knownVertices, verticesQueue);
            }
        }

    }

    /**
     * All paths between two vertices Calls recursive version of the method.
     *
     * @param graph Graph object
     * @param dest Destination vertex of path
     * @param path LinkedList with paths (queues)
     * @return false if vertices not in the graph
     *
     */
    public static <V, E> boolean allPaths(AdjacencyMatrixGraph<V, E> graph, V source, V dest, LinkedList<LinkedList<V>> paths) {
        if (graph.checkVertex(source) && graph.checkVertex(dest)) {
            int sourceIdx = graph.toIndex(source), destIdx = graph.toIndex(dest);
            boolean knownVertices[] = new boolean[graph.numVertices];
            LinkedList<V> auxList = new LinkedList<>();

            allPaths(graph, sourceIdx, destIdx, knownVertices, auxList, paths);

            return true;
        }

        return false;
    }

    /**
     * Actual paths search The method adds vertices to the current path (stack
     * of vertices) when destination is found, the current path is saved to the
     * list of paths
     *
     * @param graph Graph object
     * @param sourceIdx Index of source vertex
     * @param destIdx Index of destination vertex
     * @param knownVertices previously discovered vertices
     * @param auxStack stack of vertices in the path
     * @param path LinkedList with paths (queues)
     *
     */
    static <V, E> void allPaths(AdjacencyMatrixGraph<V, E> graph, int sourceIdx, int destIdx, 
            boolean[] knownVertices, LinkedList<V> auxStack, LinkedList<LinkedList<V>> paths) {
        V vOrig = graph.vertices.get(sourceIdx);
        V vDst = graph.vertices.get(destIdx);
        knownVertices[sourceIdx] = true;
        auxStack.add(vOrig);

        for (V vAdj : graph.directConnections(vOrig)) {
            int vAdjIdx = graph.vertices.indexOf(vAdj);
            //for (int vAdjIdx = 0; vAdjIdx < graph.numVertices; vAdjIdx++) {
            //if (graph.edgeMatrix[sourceIdx][vAdjIdx] != null) {
            if (vAdj == vDst) {
                auxStack.add(graph.vertices.get(vAdjIdx));
                paths.add(auxStack);
                auxStack.removeLast();
            } else if (knownVertices[vAdjIdx] == false) {
                allPaths(graph, vAdjIdx, destIdx, knownVertices, auxStack, paths);
            }
            //}
        }

        knownVertices[sourceIdx] = false;
        auxStack.removeLast();
    }

    /**
     * Transforms a graph into its transitive closure uses the Floyd-Warshall
     * algorithm
     *
     * @param graph Graph object
     * @param dummyEdge object to insert in the newly created edges
     * @return the new graph
     */
    public static <V, E> AdjacencyMatrixGraph<V, E> transitiveClosure(AdjacencyMatrixGraph<V, E> graph, E dummyEdge) {
        AdjacencyMatrixGraph<V, E> newGraph = (AdjacencyMatrixGraph<V, E>) graph.clone();
        for (int i = 0; i < newGraph.numVertices; i++) {
            for (int k = 0; k < newGraph.numVertices; k++) {
                if (i != k && newGraph.edgeMatrix[i][k] != null) {
                    for (int j = 0; j < newGraph.numVertices; j++) {
                        if (i != j && j != k && newGraph.edgeMatrix[k][j] != null) {
                            newGraph.insertEdge(i, k, dummyEdge);
                        }
                    }
                }
            }
        }

        return newGraph;
    }

}
