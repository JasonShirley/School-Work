//   Thread[] t = new Thread[magicArray.length * 2 + 2];
//   for(int i = 0; i < t.length; i++){
//     t[i] = new Thread(new ThreadTest());
//     t[i].start();
//   }
//   try{
//     for(int j = 0; j < t.length; j++){
//       t[j].join();
//     }
//   }
//   catch(Exception e){
//     System.err.println();
//   }
// }

// class ThreadTest implements Runnable{
//   public void run(){
//     ThreadInfo running = (id) -> "Thread " + id + " is running";
//     try{
//       System.out.println(running.threadStatus(Long.toString(Thread.currentThread().getId())));
//     }
//     catch(Exception e){
//       System.out.println("Exception Thrown: " + e);
//     }
//   }
// }
