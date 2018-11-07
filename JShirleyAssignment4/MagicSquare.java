import java.io.*;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class MagicSquare{

  public static void main(String[] args){

    int magicArray[][] = new int[0][0];
    magicArray = MagicFileReader(magicArray);
    System.out.println("Is this a Magic Square?");
    for(int i = 0; i < magicArray.length; i++){
      for(int j = 0; j < magicArray.length; j++){
        System.out.print(magicArray[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("\nLet's find out!");
  }
  /*
  MagicFileReader is a class that is built to read from a file to build the array for the program.
  The function makes ues of a BufferedReader in order to input from a file. this is done mostly because
  I wanted to experiment with different funcanalities of Java. Reading on BufferedReader, the behavior is
  synchronous, making it efficent to use with threads. The BufferedReader doesn't work with threads in this
  program, other than to fill the array, but it is still worth noting. I also dinked around with java regex
  functions, in particular I used Pattern. Pattern will parse information using the compile function. The compile
  will compile the regex into a patten given the flags that are passed. When you use the split(String) function with pattern, it will
  parse the information with the information given to the compile function and the String you pass into the function.
  */
  public static int[][] MagicFileReader(int[][] magicArray){
    try{
        File magicFile = new File("square.txt");
        BufferedReader magicBuff = new BufferedReader(new FileReader(magicFile));
        int numRowAndCol; // determines the size of the 2d array

        numRowAndCol = Integer.parseInt(magicBuff.readLine()); // read the first line to grab the size of the 2d array
        magicArray = new int[numRowAndCol][numRowAndCol];     //  create the 2d array according to the size of numRowAndCol
        String currentLine;
        Pattern pattern = Pattern.compile(" ");               // Construct a new Pattern, declare the regex to compile into the Pattern
        for(int i = 0; i < magicArray.length; i++){
            currentLine = magicBuff.readLine();              // read the next line of the bufferedReader
            String[] temp = pattern.split(currentLine);      // split the information so that it can be fed into the 2d array
          for(int j = 0; j < magicArray.length; j++){
            magicArray[i][j] = Integer.parseInt(temp[j]);
          }
        }
        magicBuff.close();
        return magicArray;
    }
    catch(FileNotFoundException e){
      System.out.println("Excepton Thrown: File Not Found");
    }
    catch(IOException e){
      System.out.println("Exception Thrown: Error Reading From File");
    }

    return magicArray;
  }
}
