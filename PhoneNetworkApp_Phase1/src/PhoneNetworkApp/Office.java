
package PhoneNetworkApp;
import GraphFramework.Vertex;
/**
 *
 * @author Raghad
 */
public class Office extends Vertex{
    
    
	 // Data Fields
    private String officeName;

    public Office(int label) {
	super(label);
	this.officeName = String.valueOf((char)(label+65));
    }
	// Methods
	

    @Override
    public void displayInfo() {
		
	System.out.print("Office No. " + officeName);
    } // End of method
    
 
    
}
