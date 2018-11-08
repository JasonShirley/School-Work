import java.io.*;
import java.util.regex.Pattern;
import java.util.*;

interface ThreadInfo{
  String threadStatus(String id);
}

public class MagicSquare{

  public static void main(String[] args){
    ThreadInfo running = (id) -> "Thread " + id + ": HELLO";

    int magicArray[][] = new int[0][0];                         // hold magic square information
    magicArray = MagicFileReader(magicArray);                   // populate the array with MagicFileReader function
    System.out.println("Is this a Magic Square?");
    for(int i = 0; i < magicArray.length; i++){
      for(int j = 0; j < magicArray.length; j++){
        System.out.print(magicArray[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("\nLet's find out!");

    int arrSize = magicArray.length;
    Thread[] rowThreadCheckArray = new Thread [arrSize];
    RowChecker rowCheck = new RowChecker(magicArray, 0, 0);
    ColumnChecker colCheck = new ColumnChecker(magicArray, 0, 0);
    rowCheck.checkRow();
    colCheck.checkCol();
    // I bypassed all of the Runnable mumbo jumbo with lambdas. I know how much you love
    // lambdas sheehy which is why I did it. Should work in Java 8, if not talk to me.
  //   for (int i = 0; i < arrSize; i++){
  //     rowThreadCheckArray[i] = new Thread(() -> {
  //       for(int j = 0; j < arrSize; j++){
  //         String threadId = Long.toString(Thread.currentThread().getId());
  //         System.out.println(running.threadStatus(threadId));
  //           try{
  //             System.out.println("thread " + threadId + " is going to sleep.");
  //             Thread.sleep(1000);
  //           }
  //           catch(Exception e){
  //             System.err.println();
  //           }
  //           System.out.println("thread " + Thread.currentThread().getId() + " woke up" );
  //         }
  //         System.out.println("thread " + Thread.currentThread().getId() + ": goodbye!");
  //       });
  //       rowThreadCheckArray[i].start();
  //       System.out.println("main thread: started thread: " + rowThreadCheckArray[i].getId());
  //   for (int j = 0; j < arrSize; j++){
  //     try{
  //       System.out.println("main thread: waiting on thread: " + rowThreadCheckArray[j].getId());
  //       rowThreadCheckArray[j].join();
  //       System.out.println("main thread: done waiting on thread: " + rowThreadCheckArray[j].getId());
  //     }
  //     catch (Exception e){
  //     }
  //   }
  // }
}


  /*
  MagicFileReader is a class that is built to read from a file to build the array for the program.
  The function makes ues of a BufferedReader in order to input from a file. this is done mostly because
  I wanted to experiment with different funcanalities of Java. Reading on BufferedReader, it is a
  synchronous function, making it efficent to use with threads. The BufferedReader won't work with threads in this
  program other than to fill the array, but it is still worth noting. BufferedReader is an older function, and
  Scaner is a simpler function, I just wanted to see what BufferedReader was all about.
  I also dinked around with java regex functions, in particular I used Pattern.
  Pattern will parse information using the compile function. The compile function
  will compile the regex into a patten given the flags that are passed.
  When you use the split(String) function with pattern, it will parse the information
  with the information given to the compile function and the String you pass into the function.
  */
  public static int[][] MagicFileReader(int[][] magicArray){
    try{
        File magicFile = new File("square.txt");
        BufferedReader magicBuff = new BufferedReader(new FileReader(magicFile));
        int numRowAndCol;                                      // determines the size of the 2d array
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
        magicBuff.close();  // don't leave the buffer open, CLOSE IT!!!!!!!!!!!!!!!!!!!!!!!! AHAHAHAHAAAHAHAHAHHAHAHAHA
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
