/*******************************************************************************
 *
 * Synchronization Example 1 - Producer/Consumer Problem
 * CS 311 Fall 2016
 * Author: Aaron Boudreaux, updated by Steve Sheehy
 * File: Writer.java
 * Last Edit: 11/16/16
 *
 ******************************************************************************/
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Reader extends Thread { //Consumer

    private Semaphore semaphore;  //Shared Semaphore to control access to the array
    int values[]; //Shared array
    Random generator; 
    int numElements;  //number of elements to consume
  
    /* The constructor gets a reference to a Semaphore and a shared array. It also
     * gets a number, n, which indicates the number of values it should expect to receive.
     *
     * s - the Semaphore
     * v - the shared array
     * n - the number of values to receive
    */
    public Reader(Semaphore s, int v[], int n) {
        semaphore = s;
        values = v;
        numElements = n;
        generator = new Random();
    }//end of constructor
		
    /****************************************************************************/
  
    /* This function runs in a thread. It consumes the specified number of messages
     * from the shared array, sleeping for 0-5 seconds each time.
     *
     * parameters - none
     * returns - none
    */  
    public void run() {
        int consumedValue;

        for(int i=0; i<numElements; i++) {
            try {
                /*
                 * The "Consumer" will sleep between 0-5 seconds then attempt to acquire
                 * the semaphore so it can read an element from the shared array. If the
                 * semaphore == 0, then it waits until the semaphore becomes positive.
                */
                Thread.sleep(generator.nextInt(5000));
                System.out.println(Thread.currentThread().getName() + ": attempting to acquire element " + i);
                semaphore.acquire(); //wait until I can get the semaphore
                System.out.println(Thread.currentThread().getName() + ": returned from aquire");
		        System.out.println(Thread.currentThread().getName() + ": \t\t\tpermits available: " + semaphore.availablePermits());
                consumedValue = values[i];
                System.out.println(Thread.currentThread().getName() + ": Read element "+ i + ": " + consumedValue);        
            }//end of try
            catch(InterruptedException e) {
                //nothing to see here
            }
        }//end of for    
    }//end of run
}//end of class
