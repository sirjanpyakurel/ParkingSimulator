
/* Program used the functionality of the ParkingSystem class.

   Sample expected output:
   
  A123TR ADDED TO PARKING LOT
  Z23YTU ADDED TO PARKING LOT
  R23EWQ ADDED TO PARKING LOT
  ERW345 ADDED TO PARKING LOT
  ERW345 REMOVED AFTER MOVING 0 CARS OUT
  B12GFT ADDED TO PARKING LOT
  A123TR REMOVED AFTER MOVING 3 CARS OUT
  X12345 ADDED TO PARKING LOT
  Y23456 ADDED TO PARKING LOT
  W321RE ADDED TO PARKING LOT
  R23EWQ REMOVED AFTER MOVING 4 CARS OUT
  CVBNMK ADDED TO PARKING LOT
  DFGHJK ADDED TO PARKING LOT
  ERTYUI ADDED TO WAITING LINE
  FGHJKL ADDED TO WAITING LINE
  GHJKL9 ADDED TO WAITING LINE
  HJKL98 ADDED TO WAITING LINE
  
  PARKING LOT has  7 cars:       Exit  --> [ DFGHJK, CVBNMK, W321RE, Y23456, X12345, B12GFT, Z23YTU ]
  WAITING LINE has 4 cars:       Front --> [ERTYUI, FGHJKL, GHJKL9, HJKL98] 
*/

public class ParkSysTest
{
	public static void main (String [] args) 
   {
		ParkingSystem park = new ParkingSystem();
		System.out.println(park.processData("parking.txt"));
      System.out.println(park);
	}
}