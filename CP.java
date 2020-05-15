package week11;

/**
* Deal with It – COSC241 ASSIGNMENT
* @author Mike Cui
* @author Riya Alagh
* Purpose of this assignment is to create a sorting program to sort cards given a respective specification
*/

import java.lang.*;
import java.util.*;

public class CP implements CardPile{
  
  public static int [] pile;
  public static int size;
  public static int rowL;
  public static int [] array1;

  public static void main (String  [] args){
    
     switch (args.length) {
      case 0: //0 command line args
       // inputHellos(); break;
      case 1://1 command line args
        //randomHellos(args[0], 1); break;
      case 2://2 command line args
        this.size=args[0];
         this.rowL=args[1];
         load(this.size);
         System.out.println(getPile());
       // String all="all";
        // transform(this.rowL,all);
          
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
    this.size=n;
    array1=new int [size];
    for (int counter=0,counter<size;counter++){
      array1[counter] = counter+1;
    }//end for loop //this initialises the array with size n
    
    pile= Arrays.copyOf(array1,size);    
  }//end load
  
  /**
     Returns a copy of the pile of cards.
  */
  public int[] getPile(){
    
    int [] badPile= Arrays.copyOf(pile,size);
    return badPile;
    
  }//end getPile
  
  /**
     Transforms the pile of cards given a row length and a specification
     for picking them up. See assignment details for required behaviour
     if the number of cards is not a multiple of the given row length or
     the specification is invalid.
  */   
  public void transform(int rowLength, String spec){
        if (spec.equals("TL")){                                                                                    
            topLeft();                                                                                                                                                                       
        }else if (spec.equals("TR"){ 
            topRight();                                                                         
        }else if (spec.equals("BL"){
            bottomLeft();                                                                
        }else if (spec.equals("BR"){
            bottomRight();                                                         
        }else if (spec.equals("LT"){
            leftTop();                                                  
        }else if (spec.equals("RT"){
            rightTop();                                          
        }else if (spec.equals("LB"){
            leftBottom();                                
        }else if (spec.equals("RB"){ 
            rightBottom();
        }
    
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


