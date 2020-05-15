package week11;



import java.lang.*;
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;


/**
* Deal with It – COSC241 ASSIGNMENT
* @author Mike Cui
* @author Riya Alagh
* Purpose of this assignment is to create a sorting program to sort cards given a respective specification
*/

public class CP implements CardPile{ //class CP.java
  
  public int [] pile;
  public int size;
  public int rowL;
  public int [] array1;
  public int [][] placedCards;

  public static void main (String  [] args){
    CP card= new CP();
     switch (args.length) {
      case 0: //0 command line args
       // inputHellos(); break;
      case 1://1 command line args
        //randomHellos(args[0], 1); break;
      case 2://2 command line args
        card.size=Integer.parseInt(args[0]);
         card.rowL=Integer.parseInt(args[1]);
         card.load(card.size);
         System.out.println(card.getPile());
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
    pile= Arrays.copyOf(cards,cards.length);
    size=pile.length;
  }//end load 
  
  /**
     Creates a pile of n cards numbered (top to bottom) from 1 to n.    
  */
  public void load(int n){
    size=n;
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
  
  public void putDown (int rowLength){
    int index=0;
    for (int row=0;row<size/rowLength;row++){//the number of rows is the size of whole array/rowLength
      for(int cols=0;cols<rowLength;cols++){//Don't change this Mike. Draw the 2D array to prove it.
     placedCards[row][cols]=pile[index];
        index++;
      }
    }
    
    
    
  }//end putDown
  /**
     Transforms the pile of cards given a row length and a specification
     for picking them up. See assignment details for required behaviour
     if the number of cards is not a multiple of the given row length or
     the specification is invalid.
  */   
  public void transform(int rowLength, String spec){
        if (spec.equals("TL")){                                                                                    
                                                                                                                                                                                   
        }else if (spec.equals("TR"){ 
                                                                                     
        }else if (spec.equals("BL"){
                                                                          
        }else if (spec.equals("BR"){
                                                                   
        }else if (spec.equals("LT"){
                                                             
        }else if (spec.equals("RT"){
                                                  
        }else if (spec.equals("LB"){
                                         
        }else if (spec.equals("RB"){ 
            
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


