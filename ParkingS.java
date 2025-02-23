import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;


public class ParkingS{
      private int parkedCarNum;
      private int waitingCarNum;
      private StackInt<String> stack;
      private StackInt<String> temp;
      private Queue<String> q;
      int stkSize = 0;
      int qSize = 0;

   public ParkingS() {
    stack = new LinkedStack<>();
    temp = new LinkedStack<>();
    q = new LinkedList<>();
   }
   
   public String processData(String fileName){
   
      StringBuilder message = new StringBuilder();
       
       
       
       int removedCars = 0;
      try{
      File file = new File(fileName);
      Scanner in = new Scanner(file);
      
      while(in.hasNext()) {
      String line = in.nextLine();
      String[] sp = line.split(" ");
      String first = sp[0];
      String second = sp[1];
     
     
     if(first.equals("a")) {
         if(stkSize < 7) {
         message.append(stack.push(second) + " ADDED TO PARKING LOT\n");
         stkSize++;
         }
         else{
          if(qSize < 4) {
          String added =  second + " ADDED TO WAITING LINE\n";
          q.add(second);
          message.append(added);
          qSize++;
          
         }
         else{
         message.append(second + " NOT ADDED TO PARKING SYSTEM");
         }
         
         }
     
      }
      if(first.equals("d")) {
            
            while(!stack.empty()){
            String car = stack.pop();
            
            if(car.equals(second)){
            message.append(second + " REMOVED AFTER MOVING " + removedCars + " CARS OUT\n");
            
            stkSize--;
            break;
            }
             else{
               temp.push(car);
               removedCars++;
             }
             if(stack.empty()){
      message.append(second + " NOT FOUND IN PARKING LOT\n");
      }
      }
      
      }
      restore();
      removedCars = 0;
      }
      }
      
      
      catch(FileNotFoundException e) {
      System.out.println("File not found.");
      }
      return message.toString();
      }
      
      
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
      
      
      
   }