/*
* Jason Shirley
* CS 311 Winter 2018
* The consumer class will build consumer objects. The objects contain a bounded
* buffer that is passed into the class. The consumer class will take care of
* running consumer threads. The thread will print out the String stored in the
* message object on the bounded buffer. The thread will also check to see if the
* value is a terminate value according to the Message object.
 */
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Consumer{
  private Message message;
  private BoundedBuffer boundedBuff;
  public Consumer(){
    boundedBuff = null;
  }
  public Consumer(BoundedBuffer bb){
    boundedBuff = bb;
  }
  public Thread runConsumer() {
    Runnable consumeRun = () -> {
      boolean terminate = false;
      try{
          while(terminate == false){
            message = (Message) boundedBuff.take();
            System.out.println(Thread.currentThread().getName() + " reads: " + message);
            int random = ThreadLocalRandom.current().nextInt(5500, 10500);
            System.out.println(Thread.currentThread().getName() + " is sleeping for " + random);
            Thread.currentThread().sleep(random);
            if(message.isTerminate()){
              terminate = true;
            }
          }
          System.out.println(Thread.currentThread().getName() + ": got terminate message. Thread exiting.");
        }
        catch(Exception e){
          System.err.println("Exception caught HERE! I'm in consumer! " + e);
        }
    };

    Thread consumeThread = new Thread(consumeRun);
    return consumeThread;
  }
}
