/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhoneNetworkApp;

import GraphFramework.Edge;
import GraphFramework.Vertex;

/**
 *
 * @author Raghad
 */
public class Line extends Edge{
   // Data Fields
    private int lLength;
		
    public Line(Vertex source, Vertex target, int weight) {
	super(source, target, weight);
	this.lLength = weight*5;
    } // End of Method
		
    // Methods
    @Override
    public void displayInfo() {
        // Output as required: road name: road x1 road size: 20
	System.out.print(" : line length: " + lLength);
    } // End of Method
	
} // End of Class


