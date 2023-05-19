
package GraphFramework;



import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Raghad
 */
// This Class Support Kruskal Algorithm Using Quick Find implementations

public class KruskalAlg extends MSTAlgorithm {
		// Data fields
		private int cost = 0;
		
	
	public KruskalAlg(Graph graph) {
                this.graph=graph;
		MSTresultList = new Edge[graph.verticesNO]; // MST List
	}
	
	
	
	public void kruskal() {
		
		MSTresultList = new Edge[graph.verticesNO]; // MST List
                Vertex vv; // Vertex source
		Vertex vu; // Vertex target
		Edge edge; // Vertex edge
		ArrayList<Edge> edges = new ArrayList<Edge>(); //PriorityQueue to store edges weights                
		// Loop through ALL vertices
		for(int i=0; i< graph.verticesNO; i++) {
			vv = graph.vertices[i];
			// Loop through adjacent list of this vertex
			for(int j=0; j<vv.adjList.size() ; j++) {
				edges.add(vv.adjList.get(j));	
			} // end of inner for-loop
		} // end of outer for-loop
		
                // Sort All Edges in non-decreasing Order 
                Collections.sort(edges);

		// Make Set for Each Vertex
		Vertex[] quickFindDS = new Vertex[graph.verticesNO]; // Set the DS as the number of vertices 
		makeSet(quickFindDS); // Make set for each vertex
		int encounter = 0; 
		
		// Loop through ALL edges
		while(encounter < MSTresultList.length-1) {
			
			// Get Minimum-weight Edge & its source & target
			edge = edges.remove(0);
			vv = edge.source;
			vu = edge.target;
			
			// Find Representative Subset from the QuickFind Disjoint Sets
			if(!findSet(quickFindDS[vv.label].label, quickFindDS[vu.label].label)) {

				// Append VT to VU & and update their representative value; 
				union(quickFindDS, vv, vu);			
				
				MSTresultList[encounter] = edge; // Add the target edge to the MST list	 
				cost += MSTresultList[encounter].weight; // Get cost of minimum-weight edges (MST)

				encounter++; // increment number of edges encountered
			} // End of if-statement
		} // End of while-loop
		
	} // End of Method
	
	/**
     * this method used to create one-element set{x} for all the V in the graph 
     * @param edges all edges of graph 
     */

    /**
     * this method used to create one-element set{x} for all the V in the graph
     * @param quickFindDS
     * @param edges all edges of graph
     */
    public void makeSet(Vertex[] quickFindDS) {
    	
    	/* loop through # of vertex
    	   create vertex of each vertex in the array
    	   making sets means making A alone in set and so on each index hold its own value vertex    
    	 */
    	for(int i=0; i < quickFindDS.length; i++) {
    		Vertex vn = new Vertex(i);
    		quickFindDS[i] = vn;
    	}
    } // End of makeSet Method
    
    /**
     * 
     * @param quickFindDS
     * @param v1
     * @param v2
     * @return
     */
    public boolean findSet(int v1, int v2){
    	return v1 == v2;
    } // End of FindSet Method
  
    
    
    public void union(Vertex[] quickFindDS, Vertex vv, Vertex vu) {	
    	int vvRepresentative = quickFindDS[vv.label].label; // get VV representative 
    	int vuRepresentative = quickFindDS[vu.label].label; // get VU representative
    	
    	boolean vvNoRepresentative = findSet(vv.label, vvRepresentative); // Find if VV have representative or not
    	boolean vuNoRepresentative = findSet(vu.label, vuRepresentative); // Find if VU have representative or not
    		
    	
    	
    	// Check if current VV & VU are representative of set 
    	for(int i=0; i<quickFindDS.length; i++) {
    		
    		// Check the (quickFindDs array if VV is representative of other vertex) && (excluding their own)
    		if(vvRepresentative == quickFindDS[i].label && (i != vv.label)) {
    			vvNoRepresentative = false; // false when VV have itself is other vertex representative
    		} // End of if-statement
    		
    		// Check the (quickFindDs array if VU is representative of other vertex) && (excluding their own)
    		if(vuRepresentative == quickFindDS[i].label && (i != vu.label)) {
    			vuNoRepresentative = false; // false when VV have itself is other vertex representative
    			
    		} // End of if-statement
    		
    	} // End of for-loop
    	
    	
    	// if VV have -a- representative and VU have -no- representative OR VV & VU (both) have -no- representative
    	if( ((!vvNoRepresentative) && (vuNoRepresentative)) || (vvNoRepresentative && vuNoRepresentative)) {
    		
    		// Make VV is the new representative
    		quickFindDS[vv.label] = quickFindDS[vv.label];
    		quickFindDS[vu.label] = quickFindDS[vv.label];
    	} // End of if-statement
    	
    	
    	// if VV have -no- representative and VU have -a- representative
    	else if (vvNoRepresentative && (!vuNoRepresentative)) {
    		quickFindDS[vv.label] = quickFindDS[vu.label];
    	} // End of else-if
    	
    	
    	// VV & VU (both) have -a- representative
    	else {
    		
       	    int maxRepresentative = Math.max(vvRepresentative, vuRepresentative); // Get max representative to overwrite its children
    		int minRepresentative = Math.min(vvRepresentative, vuRepresentative); // Get minimum to set it as the new representative
    		
    		// Loop through the QuickFind Disjoint Subset
	    	 for(int i=0; i<quickFindDS.length; i++) {
	    		 
	    		 // Find all the children of the max representative
	    		 if(quickFindDS[i].label == maxRepresentative) {
	    			 quickFindDS[i] = quickFindDS[minRepresentative]; // Update all representatives to the minimum Representative
	    			 
	    		 } // End of if-statement
	    	 } // End of for-loop
    	} // End of else
    } // End of Union method
    
	   public void displayResultingMST() {
		   // Office No. A – Office No. A B :
		  
		   for(int i=0; i<MSTresultList.length-1; i++) {
			  Vertex vf =  MSTresultList[i].source;
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
		}
} // End of Class