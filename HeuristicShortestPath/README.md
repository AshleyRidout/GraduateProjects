# Heuristic Shortest Path
This program finds the shortest path from any named vertex to the end vertex Z using two different heuristic algorithms.The first 
algorithm finds the shortest path based off the adjacent vertex with the smallest distance from Z dd(v). The second algorithm finds 
the shortest path based off the sum of dd(v) and the weight of the edge between vertices w(n,v). This program prints the path of the 
start vertex to Z including any paths that were attempted but aborted because they resulted in a dead end due to repeating a vertex 
already in the path. When a dead end occurs, the next smallest dd(v) for Algorithm 1 and dd(v) + w(n,v) for Algorithm 2 is chosen.
