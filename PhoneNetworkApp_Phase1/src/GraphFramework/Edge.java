
package GraphFramework;

/**
 *
 * @author Raghad
 */

public class Edge implements Comparable<Edge> {

	// Data Fields
    int weight;
    Vertex source;
    Vertex target;
    Vertex parent;


    public Edge() {
        source = new Vertex(); //create new source
        target = new Vertex(); //create new target
        parent = new Vertex(); //create new parent
    }
    
   
    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        parent= new Vertex();
    }   
    
    // Methods
    
    /**
     * Compare the edges weight
     * To support Priority-queue
     * @param the other edge to compare 
     * @return 
     */
    @Override
    public int compareTo(Edge o) {
        if(this.weight > o.weight)
            return 1;
        else if (this.weight == o.weight)
            return 0;
        else return -1;
      
    }
    
    /**
     *  display edges info
     */
	public void displayInfo() {
            
	}
	
} // End of Class


