import java.util.LinkedList;
//import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Scanner;
import java.util.Queue;


public class ParSystem{
   private int parkedCarNum;
   private int waitingCarNum;
   private StackInt<String> stack;
   private StackInt<String> temp;
   private Queue<String> q;
   private int stkSize;
  private int qSize;
   private int removedCars;

   public ParSystem() {
      stack = new LinkedStack<>();
      temp = new LinkedStack<>();
      q = new LinkedList<>();
      stkSize = 0;
      qSize = 0;
      removedCars = 0;
   }
   
   
   
   public String processData(String fileName){
   
      StringBuilder message = new StringBuilder();
   
      try{
         File file = new File(fileName);
         Scanner in = new Scanner(file);
      
         while(in.hasNext()) {
            String line = in.nextLine();
            String[] sp = line.split(" ");
            String first = sp[0];
            String second = sp[1];
         
            if(first.equals("a"))
               handleArrival(second,message);
         
            if(first.equals("d")) 
               handleDeparture(second, message);
         }
      
         //restore();
         //removedCars = 0;
      in.close();
      }
      
      
      catch(FileNotFoundException e) {
         //System.out.println("File not found.");
      }
      return message.toString();
   }
      
      
      
      
   @Override   
   public String toString() {
      StringBuilder sb = new StringBuilder();
     
      while(!stack.empty()){
      
         if(sb.length() > 0)
            sb.append(", ");
      
         sb.append(stack.pop());
      
      }
      restore();
      
      
            
      return "PARKING LOT has " + this.stkSize + " cars:       Exit  --> [ " + sb + " ]" +
              "\nWAITING LINE has " + this.qSize + " cars:       Front --> " + q;
      
   }
      
      
      
      
   public void restore() {
      while(!temp.empty()) {
         stack.push(temp.pop());
      }
   }
      
      
      
      
   public void handleArrival(String licensePlate, StringBuilder output) {
        
      if(stkSize < 7) {
         if(!checkNumberPlate(licensePlate)){
            output.append(stack.push(licensePlate) + " ADDED TO PARKING LOT\n");
            stkSize++;
         }
         else{
         output.append(licensePlate + " NOT ADDED TO PARKING SYSTEM\n");
         }
      }
      else{
         if(qSize < 5) {
            String added =  licensePlate + " ADDED TO WAITING LINE\n";
            q.add(licensePlate);
            output.append(added);
            qSize++;
          
         }
         else{
            output.append(licensePlate + " NOT ADDED TO PARKING SYSTEM\n");
         }
         
      }
     
   }
      
      
      
   public void handleDeparture(String lPlate, StringBuilder output)
   {
   boolean found = false;
   
   
             
      while(!stack.empty()){
         String car = stack.pop();
            
         if(car.equals(lPlate)){
            output.append(lPlate + " REMOVED AFTER MOVING " + this.removedCars + " CARS OUT\n");
            
            stkSize--;
            found = true;
            break;
            
            
            
            
         }
                  
         else{
            
            temp.push(car);
            removedCars++;
            
            
         }
      }
      
      if(found && !q.isEmpty()) {
            String waitingCar = q.remove();
            stack.push(waitingCar);
            qSize--;
            }
     
      
      restore();
 
      // if(!found) {
//       output.append(lPlate + " NOT FOUND IN PARKING LOT\n");
//       }
                
          
           if(!found){
            output.append(lPlate + " NOT FOUND IN PARKINGs LOT\n");
          }

      removedCars = 0;
   }
      
   
      
   public boolean checkNumberPlate(String plate) {
      StackInt<String> temp = new LinkedStack<>();
      boolean found = false;
      while(!stack.empty()) {
         String text = stack.pop();
         temp.push(text);
         if(plate.equals(text)){
            found = true;
            break;
         }           
      }
      while(!temp.empty()) {
         stack.push(temp.pop());
      }
      return found;
   }
           
}