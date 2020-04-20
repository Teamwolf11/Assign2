/* File: HelloWorld.java - April 2020 */

package a01;

/**
 * Say hello in various ways (illustrative program of dealing with variable
 * numbers of command line arguments or stdin in same main method).
 *
 * @author Michael Albert
 */

import java.util.Random;
import java.util.Scanner;

public class HelloWorld{

  private static final String[] greetings = {
    "Kia ora",
    "Hello",
    "Guten Tag",
    "Bonjour",
    "Jambo",
    "Molo",
    "Salam",
    "Ni hao",
    "Namaste",
    "Konnichiwa",
    "Hola",
    "Dobry den",
    "Aloha",
    "Bula",
    "Malo e lelei"
    };
    
  private static final Random R = new Random();
  
  /*
    Operate as follows:
      - If there is exactly one command line argument say hello randomly to
        that name.
      - If there are two command line arguments, the first is a name and the
        second is either the word "all" or a positive integer. In the first
        case say hello in all versions, in the second say it randomly that
        many times.
      - If there are no command line arguments read lines from
        stdin and say hello (randomly) to each one.  
  */
  public static void main(String[] args) {
  
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
        
  }  
  
  private static void allHello(String name) {
    for(String g : greetings) {
      System.out.println(g + " " + name);
    }
  }
  
  private static void randomHellos(String name, int count) {
    for(int i = 0; i < count; i++) {
      System.out.println(greetings[R.nextInt(greetings.length)] + " " + name);
    }
  }
  
  private static void inputHellos() {
    Scanner input = new Scanner(System.in);
    while (input.hasNextLine()) {
      randomHellos(input.nextLine(), 1);
    }
  }  
  


}

