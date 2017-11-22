/*
 * Ashley Ridout
 * April 12, 2017
 * 
 * Stores methods for the Vertex
 */

package finalProject;

import java.util.ArrayList;

public class Vertex {
	
	protected String name;
	protected ArrayList<Edge> edgeWeight;
	protected int ddv;
	
	//Constructors
	public Vertex(String name, ArrayList<Edge> edgeWeight, int ddv) {
		this.name = name;
		this.edgeWeight = edgeWeight;
		this.ddv = ddv;
	}
	
	public Vertex(String name) {
		this(name, new ArrayList<Edge>());
	}
	
	public Vertex(String name, ArrayList<Edge> edgeWeight) {
		this(name, edgeWeight, -1);
	}
	
	//get name
	public String getName() {
		return name;
	}
	
	//get ArrayList
	public ArrayList<Edge> getEdgeWeight() {
		return edgeWeight;
	}
	
	//set and get distance from Z (end vertex)
	public void setddv(int distance) {
		ddv = distance;
	}
	
	public int getddv() {
		return ddv;
	}
	
	//find the number of edges in ArrayList
		public int numberOfEdges() {
			return edgeWeight.size();
		}
		
	//add starting edge to ArrayList
	public void addStartEdge(Edge edge) {
		edgeWeight.add(edge);
	}
	
	//return an edge at current vertex
	//otherwise returns null
	public Edge findEdge(String end) {
		for (Edge edge : edgeWeight) {
			if (edge.getEnd().equals(end)) {
				return edge;
			}
		}
		return null;
	}

	//return edge with the smallest weight
	public Edge findSmallestWeightEdge() {
		Edge smallestWeight = edgeWeight.get(0);
		for (int i = 1; i < edgeWeight.size(); i++) {
			if (edgeWeight.get(i).getWeight() < smallestWeight.getWeight()) {
				smallestWeight = edgeWeight.get(i);
			}
		}
		return smallestWeight;
	}

	//return sum of distance from Z and weight of edge
	public int getSumOfWeights(Vertex endVertex) {
		return getddv() + endVertex.findEdge(name).getWeight();
	}
	
	//equals method for name of vertex
	public boolean equals(Object object) {
		if (!(object instanceof Vertex)) { return false; }
		if (((Vertex) object).getName().equals(name)) {
			return true;
		}
		return false;
	}
}
