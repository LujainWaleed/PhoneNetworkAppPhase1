
package PhoneNetworkApp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import GraphFramework.*;
/**
 *
 * @author Raghad
 */
public class PhoneNWDesignApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        // Intilize variables
        int nVertices = 0; // number of vertices
        int nEdges = 0; // Number of edges
        
        //Create Scanner object to read from user
        Scanner input = new Scanner(System.in);
        
        //Create Objects from the two algorithms
        KruskalAlg mstK;
        MHPrimAlg mstP;
        
        // display header
        System.out.println("\t\t--------------------------------------------------------------------\n"
        		+ "\t\t|  Minimum Spanning Tree Algorithms Run Time Comparison|\n"
        		+ "\t\t|This program will compare between Kruskal & Prim Algorithms  |\n"
        		+ "\t\t-------------------------------------------------------------------\n");

        System.out.println("\t\t  1. Requirement 1: Reading gragh from file   \n"
        		  + "\t\t  2. Requirement 2: Make gragh  \n");
        
        // read the option from user
        System.out.print(">Enter your option: ");
        int userInput= input.nextInt();
        
        // if the user didn't enter a number of the range
        while ( userInput != 1 && userInput != 2) {
       	  System.out.println("Wrong Option!");
              System.out.print(">Enter your option: ");
              userInput = input.nextInt(); 
          }
        
        
        
        if(userInput==1){
         // do requirement 1   
            System.out.println("\t\t  ***** Requirement 1: Reading gragh from file *****  ");
            
            File graphFile= new File("graphFile.txt");
           
            //Check if the file exist
           if(!graphFile.exists()){
                     System.out.println("The file does not exist!");
                     System.exit(0);
                 }
           
           Graph phNetwork= new BluePrintGraph();  // create an object of BluePrintGraph
           phNetwork.readGraphFromFile(graphFile); // Access readGraphFromFile Method in Graph Class
           
           
           //======find the minimum spanning tree using kruskal algorithm=======================================
           
            System.out.println("\nThe phone network (minimum spanning tree) generated by Kruskal algorithm is as follows: ");
            
            //read the graph in kruskal Algorithm
            mstK=new KruskalAlg(phNetwork);
            
            // read graph in kruskal 
            mstK.kruskal();
            //print information of MST list
            mstK.displayResultingMST(); 
            //print cost of MST Kruskal
            mstK.displayMSTcost(); 
            
           //======find the minimum spanning tree using Prim algorithm======================================= 
            System.out.println("\n-------------------------------------------------------------------------");
            System.out.println("\nThe phone network (minimum spanning tree) generated by min-heap based Prim algorithm is as follows:");
            
            //read the graph in MHPrim Algorithm
            mstP= new MHPrimAlg(phNetwork);
           
            // read fraph in prim
            mstP.MHPrim();

            //print information of MST list
            mstP.displayResultingMST(); 
            //print cost of MST Prim
            mstP.displayMSTcost(); 
            
        }// End of requirement 1
        
        else if(userInput==2){
         // do requirement 2   
         
            System.out.println("\t\t ***** Requirement 2: Make gragh  *****\n");
            
            System.out.println("\t\tChoice one of the Cases to test (Edges(m) and Vertcies(n)) :");
            System.out.println("\t\t1. n= 1000, m= 10000\n\t\t2. n= 1000, m= 15000\n\t\t3. n= 1000, m= 25000"
            +"\n\t\t4. n= 5000, m= 15000\n\t\t5. n= 1000, m= 25000"
            +"\n\t\t6. n= 10000, m= 15000\n\t\t7. n= 10000, m= 25000");
          
            // the user input 
            System.out.print("\n>Enter your choice: ");
            userInput = input.nextInt(); 
            
            do {
	      if (userInput==1) {
	         
	              nVertices = 1000;
	              nEdges = 10000;
	          }
	
              else if(userInput==2){
	              nVertices = 1000;
	              nEdges = 15000;
	          }
	              
	          else if(userInput== 3) {
	              nVertices = 1000;
	              nEdges = 25000;
	          }
	              
	          else if(userInput==4) {
	              nVertices = 5000;
	              nEdges = 15000;
	          }
	              
	          else if(userInput==5) {
	              nVertices = 5000;
	              nEdges = 25000;
	          }
	             
	          else if(userInput==6) {
	              nVertices = 10000;
	              nEdges = 15000;
	          }
	              
	          else if(userInput==7) {
	              nVertices = 10000;
	              nEdges = 25000;
	          }
	              
	
                  else{
	              System.out.println("incorrecet choice!");
	              System.out.print(">Enter your choice: ");
	  
	             
	      }
            } while ((1<1 || 7>7));
            
            // Create An Object of BluePrintGraph as phNetwork
            BluePrintGraph phNetwork = new BluePrintGraph(nVertices, nEdges, false);  
            phNetwork.makeGraph(nVertices, nEdges); 
            
          
            System.out.println("\n***\n All results based on generated undirected graphs. \n***\n");
          
            //======find the minimum spanning tree using kruskal algorithm=======================================
           
            System.out.println("The phone network (minimum spanning tree) generated by Kruskal algorithm is as follows: ");
            
            mstK = new KruskalAlg(phNetwork); // Chose Kruskal as the MST Object now
            long kruskalStartT = System.nanoTime(); // Start time of Kruskal  
            // Calculate the MST result    
            mstK.kruskal();  

            long kruskalFinishT = System.nanoTime();// End time of Kruskal  
            long kruskalFinalT = kruskalFinishT - kruskalStartT; // Final time  
            mstK.displayMSTcost(); // Get the MST-Cost of Kruskal
            System.out.println("Running time for Kruskal Algorithm: "+ kruskalFinalT);


            //======find the minimum spanning tree using Prim algorithm======================================= 
            System.out.println("\n-------------------------------------------------------------------------");
            System.out.println("\nThe phone network (minimum spanning tree) generated by min-heap based Prim algorithm is as follows:");

            //read the graph in MHPrim Algorithm
            mstP= new MHPrimAlg(phNetwork);
            long MHPrimStartT = System.nanoTime();//start time of MHPrim 
            // Calculate the MST result  
            mstP.MHPrim();
            long MHPrimFinishT = System.nanoTime();//finish time of MHPrim 
            long MHPrimlFinalT = MHPrimFinishT - MHPrimStartT; // Final time 
            mstP.displayMSTcost();
            System.out.println("Running time for min-heap Prim Algorithm: "+ MHPrimlFinalT);
        
        }//End of requirement 2
        
        input.close();
        System.out.println("\n\nThank you!");
        
        
    }
    
}