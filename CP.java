package week11;

import java.lang.*;
import java.util.*;

public class CP implements CardPile{
  
  int [] pile= new int [50];
  
  public static void main (String  [] args){
    
     switch (args.length) {
      case 0:
        inputHellos(); break;
      case 1:
        randomHellos(args[0], 1); break;
      case 2:
        if (args[1].equals("all")) {  
          allHello(args[0]);        
        } else {
          randomHellos(args[0], Integer.parseInt(args[1]));     
        }
        break;
      default:
        System.out.println("Incorrect number of arguments");    
    }
    
    
  }//end main
  
   /**
     Loads a copy of the given array as the pile of cards.   
  */
  public void load(int[] cards){
    
  }//end load 
  
  /**
     Creates a pile of n cards numbered (top to bottom) from 1 to n.    
  */
  public void load(int n){
    
    
    
  }//end load
  
  /**
     Returns a copy of the pile of cards.
  */
  public int[] getPile(){
    
    
  }//end getPile
  
  /**
     Transforms the pile of cards given a row length and a specification
     for picking them up. See assignment details for required behaviour
     if the number of cards is not a multiple of the given row length or
     the specification is invalid.
  */   
  public void transform(int rowLength, String spec){
    
    
  }//end transform
  
  /**
     Returns the minimum positive number of times we would need to call
     transform(rowLength, spec) on the current pile and return it to its
     original order.
     
     @return 
  */
  public int count(int rowLength, String spec){
    
    
  }//count


}//end class


