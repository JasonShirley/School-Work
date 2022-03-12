import java.util.Random;


public class RowChecker{
  private Random random = new Random();
  private int [][] magicSquare;
  private int rowToCheck;
  private int magicSquareNumber;

  public  RowChecker(){
    magicSquare = new int[0][0];
    rowToCheck = 0;
    magicSquareNumber = 0;
  }

  public  RowChecker(int[][] magicSq, int rowToCh, int magicSquareNumber){
    magicSquare = magicSq;
    rowToCheck = rowToCh;
    magicSquareNumber = magicSquareNumber;
  }

  public Boolean checkRow(){
    int numElemsInArray = magicSquare.length;
    Thread[] checkRowThreadArray = new Thread[numElemsInArray];
    for (int i = 0; i < numElemsInArray; i++){

      checkRowThreadArray[i] = new Thread(() -> {
          //for (int j = 0; j < numElemsInArray; j++){
            String threadId = Long.toString(Thread.currentThread().getId());

              try{

                  System.out.println("rowChecker " + threadId + " is going to sleep");
                  Thread.currentThread().sleep(3000);
              }
              catch(Exception e){
                System.err.println("Fialed");
            }
            System.out.println("rowChecker" + threadId + " is waking up!");
            System.out.println("this is testing stuff " + threadId + " What is the order of stuff?");

          //}
        });
        checkRowThreadArray[i].start();
    }

    return false;
  }
}
