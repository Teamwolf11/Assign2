package week11;

import java.io.*;
//import java.lang.*; //redundant import??
import java.util.Arrays;
import java.util.Scanner;

/**
 * Deal with It – COSC241 ASSIGNMENT.
 * @author Mike Cui
 * @author Riya Alagh
 * Sorting program that transforms cards given a respective specification.
 */
public class CP implements CardPile {

    /**initialise (copy) array pile used to transform throughtout program.*/
    public int[] pile;

    /**initialise size, the num of cards given.*/
    public int size;

    /**initialise the length of the row.*/
    public int rowLength;

    /**initialise 2D array for "placing" cards in initial row/column.*/
    public int[][] placedCards;

    /**initialise string array of possible input specifications.*/
    public String[] specification;

    /**initialise original array of cards.*/
    public int[] originalCards;

    /**initialise number of rows.*/
    public int numRows;

    /**initialise number of columns.*/
    public int numCols;

    /**initialise error exceptions message.*/
    public String message;

    /**initialise pileCopy as a copy of our originalCards array.*/
    public int[] pileCopy;

    /**initialise string of possible input specifications.*/
    public String spec;
    
    /**initialise specification magic number to avoid error.*/
    private static final int SPECNUMBER = 8;

    /**initialise COPY of placedCards 2D array.*/
    public int [] [] placedCardscopy;

    /**
     * Creates new CP class.
     * Operates input (accepts from both stdin and command-line inputs).
     * Runs through switch case and try..catch args.
     * Command-line arguments of either exactly two, or, three/more.
     * Reads any stdin inputs and deals with respective arguments given.
     * @param args - main method
     */
    public static void main(String[] args) { //method length 162 lines, needs to be 70
        CP card = new CP();
        switch (args.length){
            case 0: Scanner scan = new Scanner(System.in); //stdin 
                while (scan.hasNextLine()) {
                    String input = scan.nextLine();
                    Scanner sc = new Scanner(input);
                    switch (sc.next()){
                        case "c": //call and print count
                            card.rowLength = Integer.parseInt(sc.next());
                            card.spec = sc.next();
                            System.out.println(card.count
                               (card.rowLength,card.spec));
                            break;
                        case "l": //load pile from 1 to n
                            card.size = Integer.parseInt(sc.next());
                            card.load(card.size);
                            break;
                        case "L": //load pile with stdin input numbers
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
                            card.size=card.originalCards.length;
                            card.load(card.originalCards);
                            break;
                        case "p": //print cards as white spaced seperated list
                            card.pileCopy = Arrays.copyOf(card.pile, card.size);
                            for (int num : card.pileCopy) {
                                System.out.print(num + " ");
                            }//end for
                            System.out.println();
                            break;
                        case "P": //print cards in rows of length n
                            card.rowLength=sc.nextInt();
                            card.putDown(card.rowLength);
                            card.placedCardscopy=Arrays.copyOf
                                (card.placedCards,card.placedCards.length);
                            for(int i = 0; i<card.numRows; i++){
                                for(int j = 0; j<card.numCols; j++){
                                  System.out.print
                                  (card.placedCardscopy[i][j]+" ");
                                }//end inner for
                                System.out.println();
                            }//end outer for
                            break;
                        case "t": //call transform with given sepcifications
                            card.rowLength=sc.nextInt();
                            card.spec=sc.next();
                            card.transform(card.rowLength,card.spec);
                        default:
                    }//end switchh
                }//end while
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
                try{//throw exception if row length/size are incorrect input
                    if(card.size % card.rowLength != 0) {
                        card.message="Pile size is not multiple of rowLength.";
                        throw new CardPileException(card.message);
                    }//end if
                }catch (CardPileException e) {
                    System.err.println(e);
                    break;
                }//end catch
                if(card.message==null){
                    card.specification=new String[]
                            {"TL","BL","TR","BR","LT","LB","RT","RB"};
                    for (int counter =0;counter<SPECNUMBER;counter++){
                       System.out.println(card.specification[counter] 
                           + " " + card.count
                          (card.rowLength,card.specification[counter]));
                    }//end for
                }//end if
                break;
            default: //runs if more than 3 arguments in command-line input
                card.size = Integer.parseInt(args[0]);
                card.specification = new String[args.length-2];
                System.arraycopy(args,2,card.specification,0,args.length-2);
                card.rowLength = Integer.parseInt(args[1]);
                card.load(card.size);
                for(int i = 0; i < args.length - 2; i++){
                    //throw exception if row length/size are incorrect input
                    try{
                        if (card.size % card.rowLength != 0) {
                            card.message = "Pile size is not multiple of rowLength.";
                            throw new CardPileException(card.message);
                        }//end if
                    } catch (CardPileException e) {
                        System.err.println(e);
                        break;
                    }//end catch
                    try { //exception code incorrect specification
                        if (!(card.specification[i].equals("TL") || 
                              card.specification[i].equals("TR") ||
                              card.specification[i].equals("BL") || 
                              card.specification[i].equals("BR") ||
                              card.specification[i].equals("LT") || 
                              card.specification[i].equals("LB") || 
                              card.specification[i].equals("RT") || 
                              card.specification[i].equals("RB"))) {
                            card.message = 
                                "Please enter TL, BL, TR, BR, LT, LB, RT, RB.";
                            throw new CardPileException(card.message);
                        }//end if
                    } catch (CardPileException e) {
                        System.err.println(e);
                        break;
                    }//end catch
                }//end for
                if (card.message==null) {//Prints array before transformations
                    card.pileCopy = card.getPile();
                    for(int num : card.pileCopy) { //print array in one line
                        System.out.print(num + " ");
                    }//end for
                    System.out.println();
                    for (int i = 0; i < args.length - 2; i++) {
                        card.transform(card.rowLength, card.specification[i]);
                        card.pileCopy = card.getPile();
                        for(int num : card.pileCopy){//print array in one line
                            System.out.print(num + " ");
                        }//end for
                        System.out.println();
                    }//end for
                }//end if to check exceptions
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
     * @param n - int number of cards
     */
    public void load(int n) {
        size = n;
        originalCards = new int[size];
        for(int counter=0;counter<size;counter++){ //initialise array with n
            originalCards[counter] = counter + 1;
        } //end for loop 
        pile = Arrays.copyOf(originalCards, size);
    }//end load

    /**
     * Returns a copy of the pile of cards.
     * @return copy of the original array
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
        for(int row = 0; row < numRows; row++){
            for(int cols = 0; cols < numCols; cols++){
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
                for(int row = 0;row < numRows;row++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } else if (spec.equals("TR")) { //top right transformation
            for (int cols=numCols-1; cols>=0; cols--) {
                for (int row = 0; row < numRows; row++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } else if (spec.equals("BL")) { //bottom left transformation
            for (int cols=0; cols<numCols; cols++) {
                for (int row =numRows-1; row >=0; row--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } else if (spec.equals("BR")) { //bottom right transformation
            for (int cols=numCols-1; cols>=0; cols--) {
                for (int row =numRows-1; row >=0; row--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } else if (spec.equals("LT")) { //left top transformation
            for (int row = 0; row < numRows; row++) {
                for (int cols = 0; cols < numCols; cols++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } else if (spec.equals("RT")) { //right top transformation
            for (int row = 0; row < numRows; row++) {
                for (int cols=numCols-1; cols>=0; cols--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } else if (spec.equals("LB")) { //left bottom transformation
            for (int row =numRows-1; row >=0; row--) {
                for (int cols = 0; cols < numCols; cols++) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } else if (spec.equals("RB")) { //right bottom transformation
            for (int row =numRows-1; row >=0; row--) {
                for (int cols=numCols-1; cols>=0; cols--) {
                    pile[index] = placedCards[row][cols];
                    index++;
                }//inner for
            }//outer for
        } //end else if for ALL specifications
    }//end transform

    /**
     * Returns the minimum positive number of times we would need to call
     * transform(rowLength, spec) on the current pile and return it to its
     * original order.
     * @param rowLength of array
     * @param spec of wanted card pile transformation
     * @return minimum count of transformations
     */
    public int count(int rowLength, String spec) {
        int counter = 0;
        do{
            transform(rowLength,spec);
            counter++;
        }while (!Arrays.equals(originalCards, pile));
        return counter;
    }//end method count

    /**
     * method for question (c).
     * consists of all possible sequences of accessible card piles from 1-6
     * @return number of accessible piles
     */
    public int accessible(){
        return 6;
    }//end method

    /**
     * Throwable exception class.
     */
    private static class CardPileException extends Throwable {
        /**Declare serial version uid.*/
        public static final long serialVersionUID = 1234545;
         /**
         * CardPileException construstor.
         * @param s - string
         */
        public CardPileException(String s){
            System.out.print(s);
        }//end constructor
    }//end inner class

}//end class
