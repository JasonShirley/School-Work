/*
* Jason Shirley
* CS 311 Winter 2018
* BufferedTest is used for testing a bounded buffer using threads.
* BufferedTest will take in parameters from a terminal and parse that information
* to drive the program. It will initiate a number of threads according to what the user enters in
* terminal. The buffer will be the size of what the user enters in the terminal.
*
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferTest{
  // usageStatement will print a usage statement if the user doesn't enter the proper
  // information in terminal.
  public static void usageStatement(){
    System.out.println("Usage: Enter integer values to test bounded buffer");
    System.out.println("args[0] = the bounded buffer size");
    System.out.println("args[1] = the number of threads to run");
  }

  public static void main(String[] args) {
    if (args.length == 2){
      try{
        final int boundedBuffLength = Integer.parseInt(args[0]);    // size of bounded buffer
        final int numberOfConsumers = Integer.parseInt(args[1]);    // number of consumers to start
        BoundedBuffer boundedBuff = new BoundedBuffer(boundedBuffLength);
        Consumer consumerArray[] = new Consumer[numberOfConsumers];
        Thread threadArray[] = new Thread[numberOfConsumers];
        // runMainThread will run the producer thread. It will read what the user
        // enters in the terminal and save it as a Message object type.
        Runnable runMainThread = () -> {
          try{
            boolean terminate = false;
            Message readMessage;
            int count = 0;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // user input variable
            // end when the user has input "terminate" in terminal equal to the number of consumers
            while(count != numberOfConsumers){
              System.out.println("Producer: Input a message, press enter to send:");
              String message = reader.readLine();   // read user input from terminal
              if (message.equalsIgnoreCase("terminate")) {
                readMessage = new Message("", true);
                boundedBuff.put(readMessage);
                count++;
              }
              else {
                terminate = false;
                readMessage = new Message(message, terminate);
                boundedBuff.put(readMessage);
              }
            }
            reader.close();
          }
          // catch any exception that is thrown from the runnable
          catch (Exception e){
            System.out.println("Exception caught in runnable lambda. I'm in Buffered Test! " + e);
          }
        };
        Thread producer = new Thread(runMainThread);
        producer.start();
        // build and run the consumer threads
        for (int i = 0; i < numberOfConsumers ; i++) {
          consumerArray[i] = new Consumer(boundedBuff);
          threadArray[i] = consumerArray[i].runConsumer();
          threadArray[i].setName("Consumer: " + Integer.toString(i));
          threadArray[i].start();
        }
        try {
          for (int i = 0; i < consumerArray.length; i++) {
            threadArray[i].join();
          }
          System.out.println("Thread " + Thread.currentThread().getName() + " is exiting");
        }
        catch (Exception e){
            System.err.println("Exception caught in BufferTest joining threads");
            System.err.println(e);
        }
        System.out.println("goodbye! thanks for buffering.");
      }
      // the user has entered information wrong when initializing the program
      catch(Exception e){
        System.err.println("Non numerical entry found! " + e);
        usageStatement();
      }
    }
    // the user hasn't entered the proper number of arguments
    else{
      System.out.println("Please ensure that you enter exactly two arguments");
      usageStatement();
    }
  }
}
