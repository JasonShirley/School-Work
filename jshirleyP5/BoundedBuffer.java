import java.util.concurrent.locks.*;

class BoundedBuffer {
  final Lock lock = new ReentrantLock();
  final Condition notFull  = lock.newCondition();
  final Condition notEmpty = lock.newCondition();

  Object[] items;
  int putptr, takeptr, count;

  public BoundedBuffer (){
    items = new Object[0];
  }

  public BoundedBuffer(int size){
    items = new Object[size];
  }

  public void put(Object x) throws InterruptedException {
    lock.lock();
    try {
      while (count == items.length){
        System.out.print(" producer: waiting: buffer full");
        notFull.await();
      }
      items[putptr] = x;
      if (++putptr == items.length) putptr = 0;
      ++count;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  public Object take() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0){
        System.out.println(Thread.currentThread().getName() + " is waiting");
        notEmpty.await();
      }
      Object x = items[takeptr];
      if (++takeptr == items.length) takeptr = 0;
      --count;
      notFull.signal();
      return x;
    } finally {
      lock.unlock();
    }
  }
}
