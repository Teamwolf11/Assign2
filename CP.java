package week11;


import java.lang.*;
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
    public int rowLength;
    public int[][] placedCards;
    public String[] specification;
    public int[] originalCards;
    public int numRows;
    public int numCols;


    public static void main(String[] args) {
        CP card = new CP();
        switch (args.length) {
            case 0:
                System.out.println("This is for stdin, We just haven't codedd it yet. But once we do it will be put in here.");
                break;
            case 1:
                System.out.println("You need to enter the args as size of row then row length then (optional) more spec, you have entered just one number");
            case 2://2 command line args

                card.size = Integer.parseInt(args[0]);

                card.rowLength = Integer.parseInt(args[1]);
                card.load(card.size);

                if (card.size % card.rowLength != 0) {

                    try {

                        throw new CardPileException("Number given for size is not multiple of rowLength");
                    } catch (CardPileException e) {
                        System.err.println(e+": Number given for size is not multiple of rowLength");
                    }//end catch
                    break;
                }else {
                    card.specification = new String[]{"TL", "BL", "TR", "BR", "LT", "LB", "RT", "RB"};
                    for (int counter =0; counter < 8; counter++) {
                        System.out.println(card.specification[counter] + " " + card.count(card.rowLength, card.specification[counter]));
                    }//end for
                    
                }//end else if

                break;
            default://this will run when there is more than 3 arguments
                card.size = Integer.parseInt(args[0]);
                card.specification = new String[args.length - 2];//if there is 3 arguements, 3-2=1 so will for example add TL to the array.
                System.arraycopy(args, 2, card.specification, 0, args.length - 2);

                card.rowLength = Integer.parseInt(args[1]);
                card.load(card.size);

                /**THIS WILL WRITE OUT ALL THE ARRAY THAT WAS ENTERED. We have to do this as it's what they did*/
                int[] pileCopy = card.getPile();
                for (int num : pileCopy) {//this will print out the array in one line
                    System.out.print(num + " ");

                }//end for
                System.out.println();
//                //---------------------------------------------------------------------------------------------------------------------------------
//                // ---------------------------------------------------------------------------------------------------------------------------------
//                card.putDown(card.rowLength);
//
//                System.out.println(Arrays.deepToString(card.placedCards));
//                //---------------------------------------------------------------------------------------------------------------------------------
//                //---------------------------------------------------------------------------------------------------------------------------------
//                    This is for testing Q3 part a

                for (int i = 0; i < args.length - 2; i++) {

//--------------------------------------------------------------------------------------------------------------------------
                    if (card.size % card.rowLength != 0) {
                        //all this stuff in between these lines are for exceptions for case 2
                        try {/** THIS IS TO THROW THE EXCEPTION IF THE ROW LENGTH AND SIZE ARE BAD NUMBERS*/

                            throw new CardPileException("Number given for size is not multiple of rowLength");
                        } catch (CardPileException e) {
                            System.err.println(e+": Number given for size is not multiple of rowLength");

                        }//end catch
                        break;

                        /** THIS IS FOR IF THE SPEC IS WRONG*/
                    }else if(!(card.specification[i].equals("TL")||card.specification[i].equals("TR")||
                            card.specification[i].equals("BL")||card.specification[i].equals("BR")||
                            card.specification[i].equals("LT")||card.specification[i].equals("LB")
                    ||card.specification[i].equals("RT")||card.specification[i].equals("RB"))){
                        try {
                            throw new CardPileException("You have not given one of the 8 specifications");
                        } catch (CardPileException e) {
                            System.err.println(e+": You have not given one of the 8 specifications");

                        }//end catch
                        break;
                    }//end else if
 //--------------------------------------------------------------------------------------------------------------------------
                    card.transform(card.rowLength, card.specification[i]);
                    //System.out.println("SIZE IS: "+card.size+"\t ROW LENGTH IS: " + card.rowLength + "\t SPEC IS: " + card.specification[i]);

                    pileCopy = card.getPile();
                    for (int num : pileCopy) {//this will print out the array in one line
                        System.out.print(num + " ");

                    }//end for
                    System.out.println();
//                    //---------------------------------------------------------------------------------------------------------------------------------
//                    // ---------------------------------------------------------------------------------------------------------------------------------
//                   card.putDown(card.rowLength);
//
//                    System.out.println(Arrays.deepToString(card.placedCards));
//                    //---------------------------------------------------------------------------------------------------------------------------------
//                    //---------------------------------------------------------------------------------------------------------------------------------
//                    This is for testing Q3 part a



                }//end for

        }//end switch


    }//end main

    /**
     * Loads a copy of the given array as the pile of cards.
     */
    public void load(int[] originalCards) {
        this.originalCards = originalCards;
        pile = Arrays.copyOf(originalCards, originalCards.length);
        size = pile.length;
    }//end load

    /**
     * Creates a pile of n cards numbered (top to bottom) from 1 to n.
     */
    public void load(int n) {
        size = n;
        originalCards = new int[size];
        for (int counter = 0; counter < size; counter++) {
            originalCards[counter] = counter + 1;
        }//end for loop //this initialises the array with size n

        pile = Arrays.copyOf(originalCards, size);
    }//end load

    /**
     * Returns a copy of the pile of cards.
     */
    public int[] getPile() {

        int[] copyArray = Arrays.copyOf(pile, size);
        return copyArray;

    }//end getPile



    public void putDown(int rowLength) {
        int index = 0;

        numRows = size / rowLength;
        numCols = rowLength;
        placedCards = new int[numRows][numCols];
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

        putDown(this.rowLength);
        numRows = size / rowLength;
        numCols = rowLength;
        int index = 0;

        if (spec.equals("TL")) {

            for (int cols=0; cols<numCols; cols++) {

//                System.out.println("Col: "+ cols+"index: "+index);
                for (int row = 0; row < numRows; row++) {

//                    System.out.println("Row: "+row+"Col: "+ cols+"index: "+index);
                    pile[index] = placedCards[row][cols];
                    index++;
//                    System.out.println("Row: "+row+"Col: "+ cols+"index: "+index+"It has inputed pile");
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("TR")) {

            for (int cols=numCols-1; cols>=0; cols--) {
                for (int row = 0; row < numRows; row++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("BL")) {

            for (int cols=0; cols<numCols; cols++) {
                for (int row =numRows-1; row >=0; row--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("BR")) {

            for (int cols=numCols-1; cols>=0; cols--) {
                for (int row =numRows-1; row >=0; row--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("LT")) {

            for (int row = 0; row < numRows; row++) {
                for (int cols = 0; cols < numCols; cols++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for

//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("RT")) {

            for (int row = 0; row < numRows; row++) {
                for (int cols=numCols-1; cols>=0; cols--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
            //--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("LB")) {

            for (int row =numRows-1; row >=0; row--) {
                for (int cols = 0; cols < numCols; cols++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
            //--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("RB")) {

            for (int row =numRows-1; row >=0; row--) {
                for (int cols=numCols-1; cols>=0; cols--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } //end else if FOR ALL THE SPECS

    }//end transform


    /**
     * Returns the minimum positive number of times we would need to call
     * transform(rowLength, spec) on the current pile and return it to its
     * original order.
     *
     * @return
     */
    public int count(int rowLength, String spec) {
        int counter = 0;
        do {
        transform(rowLength,spec);//spec is from the method arguments, basically what got passed to the method.
        counter++;
        }while (!Arrays.equals(originalCards, pile));
        return counter;
    }//end method count



    private static class CardPileException extends Throwable {
        public CardPileException(String s) {
        }
    }//end inner class
}//end class
