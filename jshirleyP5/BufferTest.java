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
        consumerArray[i] = new Consumer(test);
        threadArray[i] = consumerArray[i].runConsumer();
        threadArray[i].setName(Integer.toString(i));
        threadArray[i].start();
      }
      Runnable runMainThread = () -> {
        try{
          boolean terminate = false;
          Message readMessage;
          int count = 0;

          while(count != 4){
            System.out.println("Producer: Input a message, press enter to send:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message = reader.readLine();
            if (message.equals("terminate")) {
              terminate = true;
              message = "";
              readMessage = new Message(message, terminate);
              count++;
            }
            else {
              terminate = false;
              readMessage = new Message(message, terminate);
            }
            test.put(readMessage);
          }
        }
        catch (Exception e){
          System.out.println("Exception caught here! I'm in BUffered Test! " + e);
        }
      };
      Thread producer = new Thread(runMainThread);
      producer.start();
      try {
        for (int i = 0; i < consumerArray.length; i++) {
          threadArray[i].join();
        }
      }
      catch (Exception e){

      }
    }
  }
}
