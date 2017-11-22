/*
 * Ashley Ridout
 * April 12, 2017
 * 
 * Constructor and Get methods for Edge
 */

package finalProject;

public class Edge {
	
	protected String start;
	protected String end;
	protected int weight;
	
	//constructor
	public Edge(String start, String end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	//get starting edge
	public String getStart() {
		return start;
	}
	
	//get ending edge
	public String getEnd() {
		return end;
	}
	
	//get weight of edge
	public int getWeight() {
		return weight;
	}
	
	//equals method to verify start and end edges
	public boolean equals(Object object) {
		if (!(object instanceof Edge)) { return false; }
		Edge otherEdge = (Edge) object;
		if (otherEdge.getEnd().equals(end) &&
			otherEdge.getStart().equals(start)) {
			return true;
		}
		return false;
	}
}
