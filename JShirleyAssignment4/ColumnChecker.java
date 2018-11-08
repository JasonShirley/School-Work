import java.util.Random;


public class ColumnChecker{
  private Random random = new Random();
  private int [][] magicSquare;
  private int colToCheck;
  private int magicSquareNumber;
  public  ColumnChecker(){
    magicSquare = new int[0][0];
    colToCheck = 0;
    magicSquareNumber = 0;
  }
  public  ColumnChecker(int[][] magicSq, int colToCh, int magicSquareNumber){
    magicSquare = magicSq;
    colToCheck = colToCh;
    magicSquareNumber = magicSquareNumber;
  }
  public Boolean checkCol(){
    int numElemsInArray = magicSquare.length;
    Thread[] checkRowThreadArray = new Thread[numElemsInArray];
    for (int i = 0; i < numElemsInArray; i++){
      checkRowThreadArray[i] = new Thread(() -> {
          //for (int j = 0; j < numElemsInArray; j++){
            String threadId = Long.toString(Thread.currentThread().getId());
            try{
              System.out.println("columnChecker " + threadId + " is going to sleep");
              Thread.currentThread().sleep(3000);
            }
            catch(Exception e){
              System.err.println("Fialed");
            }
          //}
        });
        checkRowThreadArray[i].start();
    }
    return false;
  }

}
