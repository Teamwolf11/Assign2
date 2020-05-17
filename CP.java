package week11;

import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Deal with It – COSC241 ASSIGNMENT.
 * @author Mike Cui
 * @author Riya Alagh
 * Purpose of this assignment is to create a sorting program to sort cards given a respective specification.
 */
public class CP implements CardPile {

    /** initialise the (copy) array pile used to transform throughtout the program.*/
    public int[] pile;

    /** initialise size, the num of cards given.*/
    public int size;

    /** initialise the length of the row.*/
    public int rowLength;

    /** initialise the 2D array for "placing" the cards in their initial row/column.*/
    public int[][] placedCards;

    /** initialise string array of possible input specifications.*/
    public String[] specification;

    /** initialise original array of cards.*/
    public int[] originalCards;

    /** initialise number of rows.*/
    public int numRows;

    /** initialise number of columns.*/
    public int numCols;

    /** initialise error exceptions message.*/
    public String message;

    /** initialise pileCopy as a copy of our originalCards array.*/
    public int[] pileCopy;

    /** initialise string of possible input specifications.*/
    public String spec;

    /** initialise magic number to avoid error.*/
    private static final int magicNumber = 50;
    
    public int [] [] placedCardscopy;

    /**
     * Creates new CP class.
     * Operates input (accepts from both stdin and command-line inputs).
     * Runs through switch case and try..catch args.
     * Takes command-line input arguments of either exactly two, or, three or more and outputs the respective answer from given specifications.
     * Reads any stdin inputs and deals with respective arguments given.
     * @param args - main method
     */
    public static void main(String[] args) {
        CP card = new CP();
        switch (args.length) {
            case 0:
//this is for stdin
                Scanner scan = new Scanner(System.in);

                //ArrayList <String> input= new ArrayList<String>();

                while (scan.hasNextLine()) {

                        String input = scan.nextLine();
                        Scanner sc = new Scanner(input);
                        switch (sc.next()) {
                            case "c":
                                card.rowLength = Integer.parseInt(sc.next());
                                card.spec = sc.next();
                                System.out.println(card.count(card.rowLength, card.spec));
                                break;
                            case "l":
                                card.size = Integer.parseInt(sc.next());
                                card.load(card.size);
//                                Arrays.toString(card.pile);
                                break;
                            case "L":

                                int counter = 0;
                                int [] orgCards1=new int [input.length()];
                                while (sc.hasNextInt()) {
                                    orgCards1[counter++]=(sc.nextInt());
                                }//end while
                                int countOfInt=0;

                               for (int u =0;u<orgCards1.length;u++) {

                                   if(orgCards1[u]!=0){

                                       countOfInt++;
                               }//end while

                               }//end for

                                card.originalCards=new int [countOfInt];

                                for(int u=0;u<countOfInt;u++){
                                    card.originalCards[u]=orgCards1[u];
                                }//end for
//-------------------------------------------------------------------------------------------------

                                for (int num :card.originalCards) { //this will print out the array in one line
                                    System.out.print(num + " ");
                                }//end for
                                System.out.println();
 //------------------------------------------------------------------------------



                                card.size=card.originalCards.length;
                                card.load(card.originalCards);
                                break;
                            case "p":

                                card.pileCopy = Arrays.copyOf(card.pile, card.size);
                                for (int num : card.pileCopy) { //this will print out the array in one line
                                    System.out.print(num + " ");
                                }//end for
                                System.out.println();

                                break;

                            case "P":
                                card.rowLength=sc.nextInt();
                                card.putDown(card.rowLength);
                                card.placedCardscopy=Arrays.copyOf(card.placedCards,card.placedCards.length);
//                                card.numRows = card.size / card.rowLength;
//                                card.numCols = card.rowLength;
                                for(int i = 0; i<card.numRows; i++)
                                {
                                    for(int j = 0; j<card.numCols; j++)
                                    {
                                        System.out.print(card.placedCardscopy[i][j]+" ");
                                    }
                                    System.out.println();
                                }


                                break;


                            default:

                        }//end switchh

                }



                break;
            case 1: //part(c)
//                card.size=Integer.parseInt(args[0]);
//                card.load(card.size);
//                //this is for 3c
//            card.transform(3,);
//            Arrays.equals(card.pile, card.originalCards);
//                    }
//                }
                break;
                
            case 2://2 command line args
                card.size = Integer.parseInt(args[0]);
                card.rowLength = Integer.parseInt(args[1]);
                card.load(card.size);
                try { //to throw the exception if the row length and size are incorrect input (bad numbers)
                    if (card.size % card.rowLength != 0) {
                        card.message = "Number given for pile size is not multiple of the rowLength.";
                        throw new CardPileException(card.message);
                    }
                }catch (CardPileException e) {
                    System.err.println(e);
                    break;
                }//end catch
                if(card.message==null){
                    card.specification = new String[]{"TL", "BL", "TR", "BR", "LT", "LB", "RT", "RB"};
                    for (int counter =0; counter < 8; counter++) {
                        System.out.println(card.specification[counter] + " " + card.count(card.rowLength, card.specification[counter]));
                    }//end for
                }//end if card.message==null
                break;

            default: //runs if there are more than 3 arguments in command-line input
                card.size = Integer.parseInt(args[0]);
                card.specification = new String[args.length - 2]; //e.g: if there is 3 arguments, 3-2=1 so will add TL to the array.
                System.arraycopy(args, 2, card.specification, 0, args.length - 2);
                card.rowLength = Integer.parseInt(args[1]);
                card.load(card.size);
                for (int i = 0; i < args.length - 2; i++) { //all code in between these lines are for exceptions of case 2
                    try { //to throw the exception if the row length and size are incorrect input (bad numbers)
                        if (card.size % card.rowLength != 0) {
                            card.message = "Number given for pile size is not multiple of the rowLength.";
                            throw new CardPileException(card.message);
                        }
                    } catch (CardPileException e) {
                        System.err.println(e);
                        break;
                    }//end catch
                    try { //exception code for if specification input is incorrect (not one of the specific 8)
                        if (!(card.specification[i].equals("TL") || card.specification[i].equals("TR") ||
                                card.specification[i].equals("BL") || card.specification[i].equals("BR") ||
                                card.specification[i].equals("LT") || card.specification[i].equals("LB")
                                || card.specification[i].equals("RT") || card.specification[i].equals("RB"))) {
                            card.message = "Please enter one of the correct 8 specifications. They are TL, BL, TR, BR, LT, LB, RT, RB.";
                            throw new CardPileException(card.message);
                        }
                    } catch (CardPileException e) {
                        System.err.println(e);
                        break;
                    }//end catch
                }//end for
                if (card.message==null) {
                    //Prints out array before any transformations
                    card.pileCopy = card.getPile();
                    for (int num : card.pileCopy) { //this will print out the array in one line
                        System.out.print(num + " ");
                    }//end for
                    System.out.println();

//---------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------
//Testing output/code for part 3, question (a):
//                card.putDown(card.rowLength);
//                System.out.println(Arrays.deepToString(card.placedCards));
//---------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------
                    for (int i = 0; i < args.length - 2; i++) {
                        card.transform(card.rowLength, card.specification[i]);
                        //System.out.println("SIZE IS: "+card.size+"\t ROW LENGTH IS: " + card.rowLength + "\t SPEC IS: " + card.specification[i]);
                        card.pileCopy = card.getPile();
                        for (int num : card.pileCopy) {//this will print out the array in one line
                            System.out.print(num + " ");
                        }//end for
                        System.out.println();
//---------------------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------------
//Testing output/code for part 3, question (a):
//                   card.putDown(card.rowLength);
//                   System.out.println(Arrays.deepToString(card.placedCards));
//---------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------
                    }//end for
                }//end if to check if there are exceptions or not, whcih controls if you run the print statements
        }//end switch
    }//end main method

    /**
     * Loads a copy of the given array as the pile of cards.
     * @param originalCards is the int array for original card pile
     */
    public void load(int[] originalCards) {
        this.originalCards = originalCards;
        pile = Arrays.copyOf(originalCards, originalCards.length);
        size = pile.length;
    }//end load

    /**
     * Creates a pile of n cards numbered (top to bottom) from 1 to n.
     * @param n
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

    /**
     * Takes 1D array and makes it a 2D array, "placing" the cards out.
     * additional method makes testing simplier
     * @param rowLength of array
     */
    public void putDown(int rowLength) {
        int index = 0;
        numRows = size / rowLength;
        numCols = rowLength;
        placedCards = new int[numRows][numCols];
        for (int row = 0; row < numRows; row++) {//the number of rows is the size of whole array/rowLength
            for (int cols = 0; cols < numCols; cols++) {//Don't change this Mike. Draw the 2D array to prove it.
                //System.out.println(pile[index]); //testing
                //System.out.println(row+" "+cols); //testing
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
     * @param rowLength of array
     * @param spec of wanted card pile transformation
     */
    public void transform(int rowLength, String spec) {
        putDown(this.rowLength);
        numRows = size / rowLength;
        numCols = rowLength;
        int index = 0;

        if (spec.equals("TL")) {
            for (int cols=0; cols<numCols; cols++) {
              //System.out.println("Col: "+ cols+"index: "+index);
                for (int row = 0; row < numRows; row++) {
                   //System.out.println("Row: "+row+"Col: "+ cols+"index: "+index);
                    pile[index] = placedCards[row][cols];
                    index++;
                   //System.out.println("Row: "+row+"Col: "+ cols+"index: "+index+"It has inputed pile");
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("TR")) { //top right transformation
            for (int cols=numCols-1; cols>=0; cols--) {
                for (int row = 0; row < numRows; row++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("BL")) { //bottom left transformation
            for (int cols=0; cols<numCols; cols++) {
                for (int row =numRows-1; row >=0; row--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("BR")) { //bottom right transformation
            for (int cols=numCols-1; cols>=0; cols--) {
                for (int row =numRows-1; row >=0; row--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("LT")) { //left top transformation
            for (int row = 0; row < numRows; row++) {
                for (int cols = 0; cols < numCols; cols++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("RT")) { //right top transformation
            for (int row = 0; row < numRows; row++) {
                for (int cols=numCols-1; cols>=0; cols--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
 //--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("LB")) { //left bottom transformation
            for (int row =numRows-1; row >=0; row--) {
                for (int cols = 0; cols < numCols; cols++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
//--------------------------------------------------------------------------------------------------------------------------
        } else if (spec.equals("RB")) { //right bottom transformation
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
     * @param rowLength of array
     * @param spec of wanted card pile transformation
     * @return count of min amout of transformations to return to original state/order of cards
     */
    public int count(int rowLength, String spec) {
        int counter = 0;
        do {
            transform(rowLength,spec);//spec is from the method arguments, basically what got passed to the method.
            counter++;
        }while (!Arrays.equals(originalCards, pile));
        return counter;
    }//end method count

    /**
     */
    public int accessible(){
        return 6;
    }//end method

    /**
     * Throwable exception class
     */
    private static class CardPileException extends Throwable {
        public static final long serialVersionUID = 1234545;
        public CardPileException(String s){
            System.out.print(s);
        }//end constructor
    }//end inner class
    
}//end class
