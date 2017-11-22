/*
 * Ashley Ridout
 * April 14, 2017
 * 
 * Adjacency List to store input graph for heuristicShortestPath
 */

package finalProject;

import java.util.List;
import java.util.ArrayList;

public class AdjacencyList {
	//create list
	private List<Vertex> list;
	
	//constructor with sole capacity argument
	public AdjacencyList(int capacity) {
		list = new ArrayList<Vertex>(capacity);
	}
	
	//capacity will be 26
	public AdjacencyList() {
		this(26);
	}
	
	//get method to return list
	public List<Vertex> getList() {
		return list;
	}
	
	//method to return number of vertices
	public int numberOfVertices() {
		return list.size();
	}
	
	//find vertex within list with the vertex with the same name as the key
	public Vertex findVertex(String key) {
		for (Vertex v : list) {
			if (v.getName().equals(key)) {
				return v;
			}
		}
		return null;
	}
	
	//method to get vertices adjacent to current vertex
	public ArrayList<Vertex> getAdjacentVertices(Vertex v) {
		ArrayList<Vertex> adjacentVertices = new ArrayList<>();
		
		for (Edge e : v.getEdgeWeight()) {
			adjacentVertices.add(findVertex(e.getEnd()));
		}
		
		return adjacentVertices;
	}
	
	//method to add vertex
	public void addVertex(Vertex v) {
		list.add(v);
	}
}
