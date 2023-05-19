
package PhoneNetworkApp;

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;

/**
 *
 * @author lujai
 */
public class BluePrintGraph extends Graph {
    
    public BluePrintGraph() {
		// TODO Auto-generated constructor stub
    }
    
    public BluePrintGraph(int verticesNO, int edgeNO, boolean isDigraph) {
		super(verticesNO, edgeNO, isDigraph);
    }
    
    
	
    // Create Vertex
    @Override
    public Vertex createVertex(int label) {
    	return new Office(label);
    }
    
    // Create Edge
    @Override
    public Edge createEdge(Vertex source, Vertex target, int weight) {
    	return new Line(source, target, weight);
    }
    
}
