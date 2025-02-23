/**
Date: 03/24/2024

Course: CSCI 2073

Description:
On my honor, I have neither given nor received unauthorized help while
completing this assignment.

Name and CWID: Sirjan Pyakurel(30161456)
*/


import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Scanner;
import java.util.Queue;



/**
 * Represents a parking system which has 7 places for the cars to park
 * and 5 places in the waiting line
 *This class manages the parking and waiting areas, handles arrivals and departures
 *and provides methods to check whether the same number plate car is already in 
 *the parking and waiting area
*/
public class ParkingSystem{
   private StackInt<String> parkingArea; // Parking area implemented using a stack
   private Queue<String> waitingArea; // Waiting area implemented using a queue
   private int stkSize;  //Number of cars in the parking area
   private int waitingAreaSize; //Number of cars in the waiting area
   
   
   /**
   * Constructs a ParkingSystem object with initial parking and waiting areas
   */
   public ParkingSystem() {
      parkingArea = new LinkedStack<>(); // Initializing parking area stack
      waitingArea = new LinkedList<>(); // Initializing waiting area queue
      stkSize = 0;       // Initializing parking area size
      waitingAreaSize = 0; // Initializing waiting area size
     
   }
   
   
   
   /**
   * Processes the data from a file and performs parking or departure operations accordingly
   *
   * @param fileName the name of the file containing the parking and departure data
   * @return a string containing the messages generated during the processing
   */
   public String processData(String fileName){
   
      StringBuilder message = new StringBuilder(); // Message generated during processing
   
      try{
      
         //Creating a File object with the given file name
         File file = new File(fileName);
         
         //Creating a Scanner object to read from the file
         Scanner in = new Scanner(file);
         
         //Processing each line in the file
         while(in.hasNextLine()) {
            String line = in.nextLine();    //Reading a line from the file
            String[] sp = line.split(" "); //Splitting the line into parts
            String first = sp[0];         //First part of the line
            String second = sp[1];       //Second part of the line
         
            // Handling arrival or departure based on the first part of the line
            if(first.equals("a"))
               handleArrival(second,message); // Handling arrival
         
            else 
               handleDeparture(second, message);  // Handling departure
         }
         in.close(); // Closing the Scanner object
      }
      
      catch(FileNotFoundException e) {
          // File not found exception
      }
               
      return message.toString();  // Returning the generated messages
   }
     
      
      
   /**
   *Returns a string representation of the parking and waiting areas,
   *including the number of cars in parking and waiting areas
   *
   *@return a string representation of the parking and waiting areas
   */   
   @Override   
   public String toString() {
      
      //String representation of the parking and waiting areas
      StringBuilder sb = new StringBuilder();
     sb.append("PARKING LOT has ").append(stkSize).append(" cars:    Exit --> ").append(parkingArea);
     sb.append("\nWAITING LINE has ").append(waitingAreaSize).append(" cars:       Front --> ").append(waitingArea);
      return sb.toString(); //Returning the string representation
   }
      
      
   
   
   /**
   * Handles the arrival of a car by adding it to the parking or waiting area
   *
   * @param licensePlate the license plate of the arriving car
   * @param output the StringBuilder to append output messages
   */  
   public void handleArrival(String licensePlate, StringBuilder output) {
      //Check if there is space in the parking lot and license plate is not duplicate
      if(stkSize < 7 && !checkNumberPlate(licensePlate)){
         output.append(parkingArea.push(licensePlate) + " ADDED TO PARKING LOT\n");
         stkSize++;
      }
      //Check if there is space in the waiting line and the license plate is not duplicate in both areas
      else if(waitingAreaSize < 5 && (!checkNumberPlate(licensePlate) && !checkPlate(licensePlate))) {
        
         String added =  licensePlate + " ADDED TO WAITING LINE\n";
         waitingArea.add(licensePlate);
         output.append(added);
         waitingAreaSize++;
          
      }
      
      //if neither condition is met, the car is not added
      else
         output.append(licensePlate + " NOT ADDED TO PARKING SYSTEM\n");
      
   } 
   
   
   
   /**
   * Handles the departure of a car by removing it from the parking area
   *
   * @param lPlate the license plate of the departing car
   * @param output the StringBuilder to append output messages
   */
   public void handleDeparture(String lPlate, StringBuilder output) {
      StackInt<String> tempStack = new LinkedStack<>(); // Temporary stack for moving cars
      boolean found = false; //Flag to indicate if the departing car is found
      int removedCars = 0; //Number of cars moved while searching for the departing car
      
      // Searching for the departing car in the parking area
      while(!parkingArea.empty() && !found) {
         String text = parkingArea.pop(); // Removing a car from the parking area
         
         // Checking if the removed car is the departing car
         if(text.equals(lPlate)) {
            output.append(lPlate + " REMOVED AFTER MOVING " + removedCars + " CARS OUT\n");
            
            found = true; // Setting the flag to indicate the departing car is found
            stkSize--;   // Decreasing the number of cars in the parking area
            removedCars = 0;
            
            // Moving back the cars from the temporary stack to the parking area
            while(!tempStack.empty()) {
               parkingArea.push(tempStack.pop());
            }
            
            // Adding a waiting car to the parking area if the waiting area is not empty
            if(!waitingArea.isEmpty()){
               parkingArea.push(waitingArea.remove());
               stkSize++;          //Increasing the number of cars in the parking area
               waitingAreaSize--;  //Decreasing the number of cars in the waiting area
            }
         }
         else{
            tempStack.push(text);   // Adding the car to the temporary stack
            removedCars++;         // Increasing the number of cars moved
         }
     }
     
     //Moving back the cars from the temporary stack to the parking area
     while(!tempStack.empty()) {
         parkingArea.push(tempStack.pop());
     }
     
     //Concatinating message if the departing car is not found in the parking area
     if(!found) 
      output.append(lPlate + " NOT FOUND IN PARKING LOT\n");
     }
     
   
   
   
   /**
   * Checks if a car with the given license pate is in the parking area
   *
   * @param plate the license plate of the car to check
   * @return true if the car is found in the parking area, false otherwise
   */       
   public boolean checkNumberPlate(String plate) {
      StackInt<String> temp = new LinkedStack<>(); //Temporary stack for moving cars
      boolean found = false; //Flag to indicate if the car is found
      
      //Searching for the car in the parking area
      while(!parkingArea.empty()) {
         String text = parkingArea.pop();
         temp.push(text);
         if(plate.equals(text)){
            found = true;
            break;
         }           
      }
      
      //Moving cars back to the parking area
      while(!temp.empty()) {
         parkingArea.push(temp.pop());
      }
      return found;
   }
   
   
   
   
  /**
  *Checks if a car with the given license plate is in the waiting area
  *
  *@param plate the license plate of the car to check
  *@return ture if the car is found in the waiting are, false otherwise
  */
   public boolean checkPlate(String plate) {
      Queue<String> temp = new LinkedList<>();
      boolean found = false;
      
      //Searching for the car in the waiting area
      while(!waitingArea.isEmpty()) {
         String text = waitingArea.remove();
         temp.add(text);
         if(plate.equals(text)){
            found = true;
            break;
         }
      
      }
      
      //Moving cars back to the waiting area
      while(!temp.isEmpty()) {
         waitingArea.add(temp.remove());
      }
      return found;
   }
           
}