package GraphFramework;
import java.util.PriorityQueue;




public class MHPrimAlg extends MSTAlgorithm {
	
	// Data fields
	private int cost = 0;
	

	public MHPrimAlg (Graph graph) {
            this.graph=graph;
            MSTresultList = new Edge[graph.verticesNO]; // Array holds the edges of MST
	}
	

	
	public void MHPrim() {
                Vertex currentVertex = graph.vertices[0]; // Start with vertex 0
                
                /*
                here, we choose priority queue because the default PriorityQueue is implemented with Min-Heap
                */
                 PriorityQueue<Edge> minHeapP = new PriorityQueue<Edge>(); // Create a priority queue to store edges weights

                // Loop through all vertices except the first one
                for (int i = 0; i < MSTresultList.length - 1; i++) {
                    // Add all adjacent edges of the current vertex to the priority queue
                    
                     for (Edge edge : currentVertex.adjList) {
                            edge.source.isVisited = true; // Mark the source vertex as visited
                             if (!edge.target.isVisited) {
                                 minHeapP.add(edge); // Add the edge to the priority queue
                            }
                     }

                // Find the minimum-weight edge from the priority queue
                 while (!minHeapP.isEmpty()) {
                    Edge minEdge = minHeapP.remove();
                    if (!minEdge.target.isVisited) {
                        //++++++++++++++++++++++++++++
                        minEdge.target.isVisited = true; 
                        // Add the minimum-weight edge to the MST list
                        MSTresultList[i] = minEdge;

                        // Update the cost of the MST
                         cost += minEdge.weight;
  
                         // Mark the target vertex as visited and set it as the new current vertex
                        // minEdge.target.isVisited = true;
                        currentVertex = minEdge.target;

                    // Exit the loop after adding one result to the MST
                    break;
                    }
                }
            }
  

        }
	
	   public void displayResultingMST() {
		   
		   for(int i=0; i<MSTresultList.length-1; i++) {
			  Vertex vf = MSTresultList[i].source;
			  vf.displayInfo(); 
                          System.out.print(" - ");
			  Vertex vs = MSTresultList[i].target;
			  vs.displayInfo(); 
                          
			  Edge e = MSTresultList[i];
			  e.displayInfo(); System.out.println();  
		   }
                   
	   }

	/**
	 * Shows only the cost calculated during the displayResultingMST Method
	 */
	   
		public void displayMSTcost() {
			System.out.println("\nThe cost of designed phone network: " + this.cost);
			} // End of Method
	   

} // End of Class