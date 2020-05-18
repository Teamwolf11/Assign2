package week11;

import java.io.*;
import java.util.ArrayList;
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

    public String [] specs1;

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

    public int[][] pileCopy1;

    /**initialise string of possible input specifications.*/
    public String spec;

    /**initialise specification magic number to avoid error.*/
    private static final int SPECNUMBER = 8;

    /**initialise COPY of placedCards 2D array.*/
    public int [] [] placedCardscopy;
//this is f is the position of the last array in the accessible arrrays
    int f;

    public boolean old;

    /**
     * Creates new CP class.
     * Operates input (accepts from both stdin and command-line inputs).
     * Runs through switch case and try..catch args.
     * Command-line arguments of either exactly two, or, three/more.
     * Reads any stdin inputs and deals with respective arguments given.
     * @param args - main method
     */
    public static void main(String[] args){
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
                            card.loader(orgCards1);
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
                            card.print2D(card.placedCardscopy);
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
             card.size=Integer.parseInt(args[0]);
             card.Q3c(card.size);

                break;
            case 2://2 command line args
                card.input2Args(args);
                break;
            default://runs if more than 3 arguments in command-line input
                card.specification = new String[args.length-2];
                System.arraycopy(args,2,card.specification,0,args.length-2);
                card.input3Args(args);
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
     * command-line specification with 2 input args, case 2.
     * @param args String array
     */
    public void input2Args(String [] args){
        size = Integer.parseInt(args[0]);
        rowLength = Integer.parseInt(args[1]);
        load(size);
        try{//throw exception if row length/size are incorrect input
            if(size % rowLength != 0) {
                message="Pile size is not multiple of rowLength.";
                throw new CardPileException(message);
            }//end if
        }catch (CardPileException e) {
            System.err.println(e);
        }//end catch
        if(message==null){
            specification=new String[]
                    {"TL","BL","TR","BR","LT","LB","RT","RB"};
            for (int counter =0;counter<SPECNUMBER;counter++){
                System.out.println(specification[counter]+ " " + count
                        (rowLength,specification[counter]));
            }//end for
        }//end if
    }//end method input2args

    /**
     * command-line specification with 3 input args (the default switch case).
     * @param args String array
     */
    public void input3Args(String []args) {
        size = Integer.parseInt(args[0]);
        rowLength = Integer.parseInt(args[1]);
        load(size);
        for(int i = 0; i < args.length - 2; i++){
            try{ //throw exception if row length/size are incorrect input
                if (size % rowLength != 0) {
                    message="Pile size not multiple of rowLength.";
                    throw new CardPileException(message);
                }//end if
            } catch (CardPileException e) {
                System.err.println(e);
                break;
            }//end catch
            try { //exception code incorrect specification
                if (!(specification[i].equals("TL") ||
                        specification[i].equals("TR") ||
                        specification[i].equals("BL") ||
                        specification[i].equals("BR") ||
                        specification[i].equals("LT") ||
                        specification[i].equals("LB") ||
                        specification[i].equals("RT") ||
                        specification[i].equals("RB"))) {
                    message = "Please enter TL, BL, TR, BR, LT, LB, RT, RB.";
                    throw new CardPileException(message);
                }//end if
            } catch (CardPileException e) {
                System.err.println(e);
                break;
            }//end catch
        }//end for
        if (message==null) {//Prints array before transformations
            pileCopy = getPile();
            for(int num : pileCopy) { //print array in one line
                System.out.print(num + " ");
            }//end for
            System.out.println();
            for (int i = 0; i < args.length - 2; i++) {
                transform(rowLength, specification[i]);
                pileCopy = getPile();
                for(int num : pileCopy){//print array in one line
                    System.out.print(num + " ");
                }//end for
                System.out.println();
            }//end for
        }//end if to check exceptions
    }//end method

    /**
     * stdin specification for p method.
     * @param placedCardscopy 2D array
     */
    public void print2D(int [] [] placedCardscopy){
        for(int i = 0; i<numRows; i++){
            for(int j = 0; j<numCols; j++){
                System.out.print(placedCardscopy[i][j]+" ");
            }//end inner for
            System.out.println();
        }//end outer for
    }//end method print2D

    /**
     * stdin specification for L method.
     * @param orgCards1 array
     */
    public void loader(int[] orgCards1){
        int countOfInt=0;
        for (int u =0;u<orgCards1.length;u++) {
            if(orgCards1[u]!=0){
                countOfInt++;
            }//end while
        }//end for
        originalCards=new int [countOfInt];
        for(int u=0;u<countOfInt;u++){
            originalCards[u]=orgCards1[u];
        }//end for
        size=originalCards.length;
        load(originalCards);
    }//end loader

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
public void Q3c(int size) {

    load(size);
f=0;
    pileCopy1= new int [(int) factorial(size)][size];
    specs1 = new String[]
            {"TL", "BL", "TR", "BR", "LT", "LB", "RT", "RB"};
    int [] orgCards=Arrays.copyOf(originalCards,originalCards.length);
    for(int u=0;u<size;u++){

        pileCopy1[0][u]=orgCards[u];//add will be at index 0
    }//end for
f++;
old=false;
runRows();

int count1=0;
do {
    for (int m = 1; m < f; m++) {
        count1=f;
        load(pileCopy1[m]);
        for(int i = 1; i <= size; i++) {//this is for all the possible row lengths ie 6-3 6-1 6-2 6-6
            if (size % i == 0) {
                rowLength = i;
                for (String oneSpec : specs1) {
                    // System.out.println(oneSpec + "hi");
                    transform(rowLength, oneSpec);
                    // System.out.println(oneSpec + "hi2");
                    checkFound( oneSpec);
                    ifOld(old,oneSpec);

                }//end for
                //that makes it do something, with the orginal array
            }//end if
        }//end for

    }//end for for all where you start with the unique array in pileCopy1
}while(!(f==count1));

System.out.println(f);
}//end method

    public boolean checkingF(int[] []pileCopy1,int[]pile) {
        boolean veryOld = false;
        for (int h = 0; h < f + 1; h++) {

//
//            for (int num : pileCopy1[h]) {
//                System.out.print(num + " ");
//            }//end for
//            System.out.println();
//
//            System.out.println("This is pile");
//            for (int num : pile) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
            if (Arrays.equals(pileCopy1[h], pile) ) {
                veryOld = true;
                //System.out.println("NIGHTMARE NIGHTMARE NIGHTMARE NIGHTMARE true" + h);
                break;
            } else {
                veryOld = false;
                //System.out.println("NIGHTMARE NIGHTMARE NIGHTMARE NIGHTMARE NIGHTMARE false");
            }
        }//eend foir
   return veryOld;
    }//end method


    public void runRows(){
        for(int i = 1; i <= size; i++) {//this is for all the possible row lengths ie 6-3 6-1 6-2 6-6
            if (size % i == 0) {
                rowLength = i;
                for (String oneSpec : specs1) {
                   // System.out.println(oneSpec + "hi");
                    transform(rowLength, oneSpec);
                   // System.out.println(oneSpec + "hi2");
                    checkFound( oneSpec);
                    ifOld(old,oneSpec);

                }//end for
                //that makes it do something, with the orginal array
            }//end if


        }//end for

    }//end method
    public void checkFound(String oneSpec) {
        for (int h = 0; h < f + 1; h++) {
            old = false;
//            System.out.println("NIGHTMARE NIGHTMARE NIGHTMARE");
//            System.out.println(oneSpec + "hi3 " + old + " " + rowLength);
//            System.out.println("this is crack " + h);
//            for (int num : pileCopy1[h]) {
//                System.out.print(num + " ");
//            }//end for
//            System.out.println();
//
//            System.out.println("This is pile");
//            for (int num : pile) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
            if (Arrays.equals(pileCopy1[h], pile)) {
                old = true;
 //               System.out.println("NIGHTMARE NIGHTMARE NIGHTMARE NIGHTMARE true" + h);
                break;
            } else {
                old = false;
 //               System.out.println("NIGHTMARE NIGHTMARE NIGHTMARE NIGHTMARE NIGHTMARE false");
            }

//            System.out.println(old + " old " + oneSpec + "rowL: " + rowLength);
//            System.out.println("this is crack " + h);
//            for (int num : pileCopy1[h]) {
//                System.out.print(num + " ");
//            }//end for
//            System.out.println();
//
//            System.out.println("This is pile");
//            for (int num : pile) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
        }//end for this for checks if pile is equal to any found array
    }


    public void ifOld(boolean old,String oneSpec){
        if (old == false) {


            for (int d = 0; d < size; d++) {
                pileCopy1[f][d] = pile[d];

            }//end for
            f++;
//
//            System.out.println("The size is" + f);
//            System.out.println("I have been added to pileCopy2 " + rowLength + oneSpec + (f)+pileCopy1[]);
//            System.out.println("this is crack");
//            for (int num : pileCopy1[f - 1]) {
//                System.out.print(num + " ");
//            }//end for
//            System.out.println();

        }//end if

    }
    public static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
    /**
     * method for question (c).
     * consists of all possible sequences of accessible card piles from 1-6
     * @return number of accessible piles
     */
    public int accessible(){
        return 6;
    }//end method

}//end class
