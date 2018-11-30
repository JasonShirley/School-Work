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
            System.out.println("consumer: " + Thread.currentThread().getName() + " reads: " + message);
            int random = ThreadLocalRandom.current().nextInt(5500, 10500);
            System.out.println("Consumer " + Thread.currentThread().getName() + " is sleeping for " + random);
            System.out.println("I'M TESTING TO SEE IF I CAN PRINT THIS BOOLEAN: " + message.isTerminate());
            Thread.currentThread().sleep(1000);
            if(message.isTerminate()){
              terminate = true;
            }
          }
          System.out.println("Thread " + Thread.currentThread().getName() + " is exiting");
        }
        catch(Exception e){
          System.err.println("Exception caught HERE! I'm in consumer! " + e);
        }
    };

    Thread consumeThread = new Thread(consumeRun);
    return consumeThread;
  }
}
