import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferTest{
  boolean stopCondition = false;
  public static void main(String[] args) {
    if (args.length < 2){
      BoundedBuffer test = new BoundedBuffer(4);
      Consumer consumerArray[] = new Consumer[4];
      Thread threadArray[] = new Thread[4];
      for (int i = 0; i < consumerArray.length; i++) {
        consumerArray[i] = new Consumer(test,i);
        threadArray[i] = consumerArray[i].runConsumer();
        threadArray[i].start();
      }
      Runnable runMainThread = () -> {
        try{
          while(true){
            System.out.println("Producer: Input a message, press enter to send:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message = reader.readLine();
            Message readMessage = new Message(message);
            test.put(readMessage);
          }
        }
        catch (Exception e){

        }
      };
      Thread producer = new Thread(runMainThread);
      producer.start();
    }
  }
}
