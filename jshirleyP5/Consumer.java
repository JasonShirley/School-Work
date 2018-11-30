import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Consumer{
  BoundedBuffer boundedBuff;
  boolean terminate = false;
  int id;
  public Consumer(){
    boundedBuff = null;
  }
  public Consumer(BoundedBuffer bb, int x){
    boundedBuff = bb;
    id = x;
  }
  public Thread runConsumer() {
    Runnable consumeRun = () -> {
        try{
          while(!(terminate)){
            Message message = new Message(boundedBuff.take(id).toString());
            System.out.println("consumer: " + id + " reads: " + message);
            int random = ThreadLocalRandom.current().nextInt(5500, 10500);
            System.out.println("Consumer " + id + " is sleeping for " + random);
            Thread.currentThread().sleep(random);
            if(message.isTerminate()){
              System.out.println("terminate");
              terminate = true;
            }
          }
        }
        catch(Exception e){
          System.err.println(e);
        }
    };
    Thread consumeThread = new Thread(consumeRun);
    return consumeThread;
  }
}
