
package GraphFramework;
import PhoneNetworkApp.BluePrintGraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Raghad
 */
public class Graph {
    	// Data Fields
    int verticesNO; // # of Vertices
    int edgeNO; // # of Edges
    Boolean isDigraph; // is Graph directed or not
    Vertex [] vertices; // Array of Vertices
    
    
   
    
    public Graph(int verticesNO, int edgeNO, boolean isDigraph) {
        this.verticesNO = verticesNO;
        this.edgeNO = edgeNO;
        this.isDigraph= isDigraph;
        this.vertices = new Vertex[verticesNO]; 
    }
    
    // ---Constructor to use it for Read Graph from File Function.---///
    
    public Graph() {
    }
    
    // Create Vertex
    public Vertex createVertex(int label) {
    	return new Vertex(label);
    }
    
    // Create Edge
    
    public Edge createEdge(Vertex source, Vertex target, int weight) {
    	return new Edge(source, target, weight);
    }
   
   	
    public Edge addEdge(int v, int u, int weight){
    	
		Graph phNetwork = new BluePrintGraph();

    	// This Eliminate Duplicate 
    	if(vertices[v] == null){ 			
    		verticesNO++; // Increment# of Vertices First to Avoid Size problem
    		vertices[v] = phNetwork.createVertex(v); // Create new Source Vertex in the Array of Vertices
    		 }
        
    	// This Eliminate Duplicate 
        if(vertices[u] == null){
            verticesNO++;  // Increment# of Vertices First to Avoid Size problem
            vertices[u] = phNetwork.createVertex(u); // Create new Target Vertex in the Array of Vertices        		
        }
        
    	// Create new Edge
        
    	Edge newEdge = phNetwork.createEdge(vertices[v], vertices[u], weight);
    	edgeNO++; // Increase # of Edges
        
    		// Access the Vertex Adj_List and add the Edge with its weight.    		 
    		vertices[v].adjList.add(newEdge);
    		
    	 // If the Graph is undirected 
    	 if(!isDigraph){	
    		 
         	 // Add the OPPOSITE Edge (Undirected).   
    		 newEdge = phNetwork.createEdge(vertices[u], vertices[v], weight);    		 
             edgeNO+= 2; // Increase # of Edges
             
             // Access the Vertex Adj_List and add the Edge with its weight.
             vertices[u].adjList.add(newEdge);
         }
         else if(isDigraph){
             edgeNO++;
         }
    	 
        return newEdge;
        
    } // End of addEdge METHOD. 
    
    	
    	public void readGraphFromFile(File fileName) throws FileNotFoundException {
    		
            Scanner input = new Scanner(fileName); // Scanner to read from file
            
            String typeofGraph = input.nextLine(); // Is direct graph or not?
            if(typeofGraph.equalsIgnoreCase("digraph 0")) {
                isDigraph= false; // 0 == undirected graph
            }
            else if (typeofGraph.equalsIgnoreCase("digraph 1")) {
            	isDigraph = true; // 1 == directed graph
            }
            
    		int totalNumberOfVertices= input.nextInt(); // Read  (Second Line) # Vertices 
    		int totalNumberOfEdge= input.nextInt(); // Read (Still Second Line) # Edges 
    		
    		if(!isDigraph) {
    			totalNumberOfEdge *= 2;
    		}
              		
            vertices = new Vertex[totalNumberOfVertices]; // Initialize Array of Vertices
            
            while(edgeNO < totalNumberOfEdge) {
                char source = input.next().charAt(0);
                char destination = input.next().charAt(0);
                int weight = input.nextInt();
                
                /** This approach will leads immediately to the Vertex position in the array without using String.
                	Also, -AddEdge- method here uses integer input.
                	P.S. Minus 65 as Character value A is 65 which will leads to 0, B to 1 and so on.
                 */
                addEdge(source-65, destination-65, weight); 
                                
            } // End of while-loop
            input.close();
           
    	} // End of Method
    	
    	
    	public void makeGraph(int verticesNO, int edgeNO) {
    		    		
    		// Store All Vertices Accordingly 
    		for(int i=0; i < verticesNO; i++) {
    			vertices[i] = new Vertex(i);
    		}
    		
    		// Store Edges respectively to make sure ALL VERTICES ARE CONNCTED ( (|V|-1)= # E )
    		for(int i=0; i < verticesNO-1; i++) {
    			
    			
    			// Vertex u, Vertex v, RANDOM Edge Weight
    			addEdge(vertices[i].label, vertices[i+1].label, (int) (1 + Math.random() * 10)); 
    		}

    		int i = 0;
    		// Add Remaining Edges RANDOMLY -> edgeNO-(|V|-1)
    		while(i < (edgeNO-(verticesNO-1))) {
    			 int vertexU = (int) (Math.random() * verticesNO);
    			 int vertexV = (int) (Math.random() * verticesNO);
    			
    			// Avoid vertex if its self-looped
    			 if(vertexU == vertexV) {
    				 continue; // Skip the loop ( increment is skipped also) & get new random vertices.
    			 }
    			 
    			// Avoid duplicate edge with the same source and target
    			for(int j=0; j < vertices[vertexU].adjList.size(); j++) {
    				if(vertices[vertexU].adjList.get(j).target.label != vertexV) {
    					
    					break; // break out of loop if vertexU and vertexV are not an edge.
    					
    				} // end of if statement
    			} 	
    			
    			// if there was no self-loop vertex & none existing edge, add new edge & increment
    			addEdge(vertices[vertexU].label, vertices[vertexV].label, (int) (1 + Math.random() * 10));
				i++;
				
    		} // end of while-loop			
    	} // end of method
        
        
        
        
        
    } // end of Class
    	
 

