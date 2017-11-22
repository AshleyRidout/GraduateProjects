/*
 * Ashley Ridout
 * April 14, 2017
 *
 * Program to find the shortest path from any named vertex to the end vertex Z
 * using two heuristic algorithms.
 * 
 * The first algorithm finds the shortest path based off the adjacent vertex with the smallest distance from Z dd(v).
 * The second algorithm finds the shortest path based off the sum of dd(v) and the weight of the edge between vertices w(n,v).
 * 
 * This program utilizes the AdjacencyList data structure to hold the weight of edge between vertices graph.
 * This program utilizes the HashMap to hold the smallest distance from Z.
 * 
 * The program prints the path of the start vertex to Z including any paths that were attempted but aborted
 * because they resulted in a dead end due to repeating a vertex already in the path. When a dead end occurs, the next smallest 
 * dd(v) for Algorithm 1 and dd(v) + w(n,v) for Algorithm 2 was chosen.
 */

package finalProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class heuristicShortestPath {
	// Adjacency List data structure holding the graph
	protected static AdjacencyList alGraph = new AdjacencyList();
	// hashmap to hold direct distance to Z
	protected static Map<String, Integer> directDistanceToZ = new HashMap<>();
	// hardcode end node
	protected static final Vertex endVertex = new Vertex("Z");

	public static void main(String[] args) {

		alGraph = createAdjacencyListFromInputGraph();
		directDistanceToZ = readdirectDistanceToZFromFile();

		// Set the edge weights for the graph from directDistanceToZ
		setEdgeWeights(alGraph, directDistanceToZ);

		// scanner to read in starting vertex
		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Please enter the name of the beginning vertex (A through T).");
		String sv = reader.next(); //

		// Run algorithm one
		ddvOnlyAlgorithm(sv);

		// Run algorithm two
		weightAndDdvAlgorithm(sv);

		reader.close();
	}

	/****************************************Algorithm 1*************************************************************
	 * 
	 * Algorithm that picks adjacent vertex with smallest distance to Z (dd(v))
	 * 
	 */

	public static void ddvOnlyAlgorithm(String startingVertex) {
		try {
			// current vertex is starting vertex
			Vertex currentVertex = alGraph.findVertex(startingVertex);

			// vertices adjacent to current vertex
			List<Vertex> adjacentVertices;

			// backtrack list
			List<Vertex> backtrack = new ArrayList<>();
			// flag to know when we're backtracking
			boolean backtrackFlag = false;

			// path's history list
			List<Vertex> history = new ArrayList<>();
			// add starting vertex to history list
			history.add(currentVertex);

			System.out.println("Algorithm 1:\n");
			System.out.print("Path: \n");

			// while loop that runs until we hit the end vertex Z
			while (!currentVertex.equals(endVertex)) {
				// get adjacentVertices of current vertex
				adjacentVertices = alGraph.getAdjacentVertices(currentVertex);

				// select smallest ddv vertex
				Vertex smallestDdvVertex = selectSmallestDdvVertex(adjacentVertices, backtrack, history);

				// assign current vertex as the vertex with the smallest dd(v)
				if (smallestDdvVertex != null) {
					currentVertex = smallestDdvVertex;
				}

				// add current vertex to the history list
				history.add(currentVertex);

				// if we are backtracking
				if (backtrackFlag == true) {
					backtrackFlag = false;
				}
				// if we hit a dead end
				else if (smallestDdvVertex == null) {
					// remove this vertex from the history list
					history.remove(currentVertex);
					// get current vertex to move on
					currentVertex = history.get(history.size() - 2);
					// stop this dead end from happening again
					backtrack.add(history.remove(history.size() - 1));
					// set flag to true
					backtrackFlag = true;
				}
				// print path to console
				else {
					for (int i = 0; i < history.size(); i++) {
						// vertex name
						System.out.print(history.get(i).getName());
						// add arrows between vertices except the last vertex
						if (i != history.size() - 1) {
							System.out.print(" -> ");
						}
					}
					// print shortest length to console
					System.out.print("\n\tShortest path length: ");
					// initialize total
					int total = 0;
					for (int i = 0; i < history.size(); i++) {
						if (i != 0) {
							// get w(n,v) and print
							int weight = history.get(i - 1).findEdge(history.get(i).getName()).getWeight();
							total += weight;
							System.out.print(weight);
						}
						// add "+" between w(n,v)
						if (i != history.size() - 1 && i != 0) {
							System.out.print(" + ");
						}
					}
					// print total weight of all edges in path
					System.out.println(" = " + total + "\n");
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Please enter a starting vertex between (uppercase) A and T.");
		}
	}

	// boolean to make sure vertex is in the list
	public static boolean vertexInList(List<Vertex> list, Vertex node) {
		for (Vertex v : list) {
			if (v.equals(node)) {
				return true;
			}
		}
		return false;
	}

	// selects vertex with the smallest dd(v) that is not in the backtrack list
	// nor the history list
	// if no vertex exists, returns null
	public static Vertex selectSmallestDdvVertex(List<Vertex> list, List<Vertex> backtrack, List<Vertex> history) {
		Vertex shortestDistance = null;
		for (int i = 0; i < list.size(); i++) {
			if (!vertexInList(backtrack, list.get(i)) && !vertexInList(history, list.get(i))) {
				if (shortestDistance == null) {
					shortestDistance = list.get(i);
				} else if (list.get(i).getddv() < shortestDistance.getddv() && !vertexInList(backtrack, list.get(i))
						&& !vertexInList(history, list.get(i))) {
					shortestDistance = list.get(i);
				}
			}
		}
		return shortestDistance;
	}

	/****************************************Algorithm 2*************************************************************
	 * 
	 * Algorithm that picks adjacent vertex whose sum of dd(v) + weight between
	 * vertices w(n,v) is smallest
	 * 
	 */

	public static void weightAndDdvAlgorithm(String start) {
		try {
			// current vertex is starting vertex
			Vertex currentVertex = alGraph.findVertex(start);

			// vertices adjacent to current vertex
			List<Vertex> adjacentVertices;

			// backtrack list
			List<Vertex> backtrack = new ArrayList<>();
			// flag to know we're backtracking
			boolean backtrackFlag = false;

			// path's history list
			List<Vertex> history = new ArrayList<>();
			// add starting vertex to history list
			history.add(currentVertex);

			// Print title for algorithm 1
			System.out.println("\nAlgorithm 2:\n");
			System.out.println("Path:");

			// while loop that runs until we hit the end vertex Z
			while (!currentVertex.equals(endVertex)) {
				// get adjacentVertices of current vertex
				adjacentVertices = alGraph.getAdjacentVertices(currentVertex);

				// select smallest ddv vertex
				Vertex smallestDdvVertex = selectSmallestSumOfDdvAndWeight(adjacentVertices, backtrack, history,
						currentVertex);

				// assign current vertex as the vertex with the smallest dd(v)
				if (smallestDdvVertex != null) {
					currentVertex = smallestDdvVertex;
				}

				// add current vertex to the history list
				history.add(currentVertex);

				// if we are backtracking
				if (backtrackFlag == true) {
					backtrackFlag = false; // Reset flag

				}
				// if we hit a dead end
				else if (smallestDdvVertex == null) {
					// remove this vertex from the history list
					history.remove(currentVertex);
					// get current vertex to move on
					currentVertex = history.get(history.size() - 2);
					// stop this dead end from happening again
					backtrack.add(history.remove(history.size() - 1));
					// set flag to true
					backtrackFlag = true;
				}
				// print path to console
				else {
					for (int i = 0; i < history.size(); i++) {
						// vertex name
						System.out.print(history.get(i).getName());
						// add arrows between vertices except the last vertex
						if (i != history.size() - 1) {
							System.out.print(" -> ");
						}
					}
					// print shortest length to console
					System.out.print("\n\tShortest path length: ");
					// initialize total
					int total = 0;
					for (int i = 0; i < history.size(); i++) {
						if (i != 0) {
							// get dd(v) + w(n,v) and print
							int weight = history.get(i - 1).getSumOfWeights(history.get(i));
							total += weight;
							System.out.print(weight);
						}
						// add "+" between each vertex's dd(v) + w(n,v)
						if (i != history.size() - 1 && i != 0) {
							System.out.print(" + ");
						}
					}
					// print total dd(v) + w(n,v) for all edges in path
					System.out.println(" = " + total + "\n");
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Please enter a starting vertex between (uppercase) A and T.");
		}
	}

	// method to set the weights of the edges through the inputted graph and map
	// arguments
	private static void setEdgeWeights(AdjacencyList list, Map<String, Integer> distances) {
		// check that the number of vertices in the adjacency list equals the
		// number of vertices in the map
		if (list.numberOfVertices() != distances.size()) {
			throw new IllegalArgumentException("Incorrect number of vertices in input.");
		}

		// iterate over the keys in distances
		for (String name : distances.keySet()) {
			// set the distance for each edge
			list.findVertex(name).setddv(distances.get(name));
			;
		}
	}

	// selects vertex whose dd(v) + weight is the smallest
	// if no vertex exists, returns null
	public static Vertex selectSmallestSumOfDdvAndWeight(List<Vertex> list, List<Vertex> backtrack,
			List<Vertex> history, Vertex currentVertex) {
		Vertex lowestCombinedWeightVertex = null;

		for (int i = 0; i < list.size(); i++) {
			if (!vertexInList(backtrack, list.get(i)) && !vertexInList(history, list.get(i))) {

				int currentWeight = list.get(i).getSumOfWeights(currentVertex);

				if (lowestCombinedWeightVertex == null) {
					lowestCombinedWeightVertex = list.get(i);
				} else if (currentWeight < lowestCombinedWeightVertex.getSumOfWeights(currentVertex)) {
					lowestCombinedWeightVertex = list.get(i);
				}
			}
		}
		return lowestCombinedWeightVertex;
	}

	/**************************Method to read in a text file and from this, create an adjacency list***************************
	 * 
	 */

	private static AdjacencyList createAdjacencyListFromInputGraph() {

		// use array list to hold file data
		List<String[]> arrayListGraph = new ArrayList<>();

		// create temporary adjacency list
		AdjacencyList temp = new AdjacencyList();

		// utilities to read in file
		BufferedReader br = null;
		FileReader fr = null;

		// read graph input file
		try {
			fr = new FileReader("data.txt");
			br = new BufferedReader(fr);
			String currentLine;

			// while there is a next line, read it into an array list and split
			// element by whitespace
			while ((currentLine = br.readLine()) != null) {
				arrayListGraph.add(currentLine.split("\\s+"));
			}

			// catch io exception and print if there is an exception
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close buffered reader and file reader
			closeResources(br, fr);
		}

		// Iterate through elements and create adjacency list
		for (int i = 1; i < arrayListGraph.size(); i++) {
			Vertex v = null;

			for (int j = 0; j < arrayListGraph.get(i).length; j++) {
				// name the first column as a vertex
				if (j == 0) {
					v = new Vertex(arrayListGraph.get(i)[0]);
					continue;
				}

				// name the second column as the edge as assign to appropriate
				// vertex
				if (Integer.parseInt(arrayListGraph.get(i)[j]) != 0) {
					Edge newEdge = new Edge(v.getName(), arrayListGraph.get(0)[j],
							Integer.parseInt(arrayListGraph.get(i)[j]));
					v.addStartEdge(newEdge);
				}
			}
			// add each vertex to adjacency list
			temp.addVertex(v);
		}

		// return adjacency list
		return temp;
	}

	/*****************************Method to read in a text file and from this, create a map******************************
	 * 
	 */
	private static Map<String, Integer> readdirectDistanceToZFromFile() {
		// create map
		Map<String, Integer> distances = new HashMap<>();
		// utilities to read in file
		BufferedReader br = null;
		FileReader fr = null;

		// read direct distance to Z input file
		try {
			fr = new FileReader("direct_distance.txt");
			br = new BufferedReader(fr);
			String currentLine;

			// while there is a next line, read it into a map and split
			// element by whitespace
			while ((currentLine = br.readLine()) != null) {
				String[] keyValuePair = currentLine.split(" ");
				distances.put(keyValuePair[0], Integer.parseInt(keyValuePair[1]));
			}
			// catch io exception and print if there is an exception
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close buffered reader and file reader
			closeResources(br, fr);
		}
		// return map
		return distances;
	}

	// method to close buffered reader and file reader
	public static void closeResources(BufferedReader br, FileReader fr) {
		try {
			if (br != null) {
				br.close();
			}
			if (fr != null) {
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
