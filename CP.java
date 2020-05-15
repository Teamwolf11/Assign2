package week11;


import java.lang.*;
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;


/**
 * Deal with It – COSC241 ASSIGNMENT
 *
 * @author Mike Cui
 * @author Riya Alagh
 * Purpose of this assignment is to create a sorting program to sort cards given a respective specification
 */

public class CP implements CardPile { //class CP.java

    public int[] pile;
    public int size;
    public int rowL;
    public int[] array1;
    public int[][] placedCards;
    public String[] specification;


    public static void main(String[] args) {
        CP card = new CP();
        switch (args.length) {
            case 0: //0 command line args
                // inputHellos(); break;
            case 1://1 command line args
                //randomHellos(args[0], 1); break;
            case 2://2 command line args
                card.size = Integer.parseInt(args[0]);
                card.rowL = Integer.parseInt(args[1]);
                card.load(card.size);
                // System.out.println(card.getPile());
                // String all="all";
                // transform(this.rowL,all);
                card.specification = new String[]{"TL", "BL", "TR", "BR", "LT", "LB", "RT", "RB"};
                for (int counter = 0; counter < 8; counter++) {
                    System.out.println(card.specification[counter] + " " + card.count(card.rowL,card.specification[counter]));
                }//end for
                card.putDown(card.rowL);
                //for outputing 2d array
                for (int i = 0; i < card.placedCards.length; i++) {
                    for (int j = 0; j < card.placedCards[i].length; j++) {
                        System.out.print(card.placedCards[i][j] + " ");
                    }//end inner for
                    System.out.println();
                }//end outer for



                break;
            default://this will run when there is more than 3 arguments
                card.size = Integer.parseInt(args[0]);
                card.specification=new String [args.length-2];
                System.arraycopy(args, 2, card.specification, 0, args.length - 2);

                card.rowL=Integer.parseInt(args[1]);
                card.load(card.size);

                for(int i=0;i<args.length-2;i++){
                    card.transform(card.rowL,card.specification[i]);
                    System.out.println("It has done the transform for "+card.rowL+" and "+card.specification[i]);

                int []pileCopy=card.getPile();
                        for (int num:pileCopy){//this will print out the array in one line
                            System.out.print(num+" ");

                        }
                    System.out.println();
                }//end for
        }//end switch


    }//end main

    /**
     * Loads a copy of the given array as the pile of cards.
     */
    public void load(int[] cards) {
        pile = Arrays.copyOf(cards, cards.length);
        size = pile.length;
    }//end load

    /**
     * Creates a pile of n cards numbered (top to bottom) from 1 to n.
     */
    public void load(int n) {
        size = n;
        array1 = new int[size];
        for (int counter = 0; counter<size;counter++){
            array1[counter] = counter + 1;
        }//end for loop //this initialises the array with size n

        pile = Arrays.copyOf(array1, size);
    }//end load

    /**
     * Returns a copy of the pile of cards.
     */
    public int[] getPile() {

        int[] badPile = Arrays.copyOf(pile, size);
        return badPile;

    }//end getPile

    public void putDown(int rowLength) {
        int index = 0;

            int numRows=size/rowLength;
            int numCols=rowLength;
            placedCards=new int[numRows][numCols];
        for (int row = 0; row < numRows; row++) {//the number of rows is the size of whole array/rowLength
            for (int cols = 0; cols < numCols; cols++) {//Don't change this Mike. Draw the 2D array to prove it.
//                System.out.println(pile[index]);
//                System.out.println(row+" "+cols);
                placedCards[row][cols] = pile[index];


                index++;
            }//inner for
        }//outer for


    }//end putDown

    /**
     * Transforms the pile of cards given a row length and a specification
     * for picking them up. See assignment details for required behaviour
     * if the number of cards is not a multiple of the given row length or
     * the specification is invalid.
     */
    public void transform(int rowLength, String spec) {
        if (spec.equals("TL")) {
System.out.println("It executed TL");
        } else if (spec.equals("TR")) {

        }else if (spec.equals("BL")) {

        }else if (spec.equals("BR")) {
            System.out.println("It executed BR");

        }else if (spec.equals("LT")) {

        }else if (spec.equals("RT")) {

        }else if (spec.equals("LB")) {

        }else if (spec.equals("RB")) {

        }

    }//end transform

    /**
     * Returns the minimum positive number of times we would need to call
     * transform(rowLength, spec) on the current pile and return it to its
     * original order.
     *
     * @return
     */
    public int count(int rowLength, String spec) {

        System.out.println("It executed Count");
return 5;
    }//count


}//end class
