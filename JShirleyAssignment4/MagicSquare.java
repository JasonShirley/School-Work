import java.io.*;

public class MagicSquare{

  public static void main(String[] args){
    // try{
    //     File magicFile = new File("square.txt");
    //     BufferedReader magicBuff = new BufferedReader(new FileReader(magicFile));
    //     int numRowAndCol;
    //     numRowAndCol = Integer.parseInt(magicBuff.readLine());
    //     int magicArray[][];
    //     magicArray = new int[numRowAndCol][numRowAndCol];
    //     for(int i = 0; i < magicArray.length; i++){
    //       String intStrArr[] = magicBuff.readLine().split(" ");
    //       System.out.println(intStrArr[0]);
    //       for(int j = 0; j < magicArray.length; j++){
    //         System.out.print(magicArray[i][j]);
    //       }
    //       System.out.println();
    //     }
    //     magicBuff.close();
    // }
    // catch(Exception e){
    //   System.out.println("Exception Thrown: File Not Found");
    // }
    int magicArray[][] = new int[0][0];
    magicArray = MagicFileReader(magicArray);
    for(int i = 0; i < magicArray.length; i++){
      for(int j = 0; j < magicArray.length; j++){
        System.out.print(magicArray[i][j]);
      }
      System.out.println();
    }
  }

  public static int[][] MagicFileReader(int[][] magicArray){
    try{
        File magicFile = new File("square.txt");
        BufferedReader magicBuff = new BufferedReader(new FileReader(magicFile));
        int numRowAndCol;
        numRowAndCol = Integer.parseInt(magicBuff.readLine());
        magicArray = new int[numRowAndCol][numRowAndCol];
        for(int i = 0; i < magicArray.length; i++){
          magicArray[i][0] = i;
          for(int j = 0; j < magicArray.length; j++){
            magicArray[0][j] = j;
          }
        }
        magicBuff.close();
        return magicArray;
    }
    catch(Exception e){
      System.out.println("Exception Thrown: File Not Found");
    }
    return magicArray;
  }
}
