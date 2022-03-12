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

public class Writer extends Thread { //Producer
    private Semaphore semaphore;  //Shared Semaphore to control access to the array
    int values[]; //Shared array
    int numElements; //number of elements to produce
    Random generator; //generates random values
  

    /* The constructor gets a reference to a Semaphore and a shared array. It also
     * gets a number, n, which indicates the number of values it should produce.
     *
     * s - the Semaphore
     * v - the shared array
     * n - the number of values to be produced
    */
    public Writer(Semaphore s, int v[], int n) {
        semaphore = s;
        values = v;
        numElements = n;
        generator = new Random();
    }//end of constructor
		

    /****************************************************************************/
	
    /* This thread generates the specified number of messages
     * and places then in the shared array, sleeping for 0-5 seconds each time.
     *
     * parameters - none
     * returns - none
    */  
    public void run() {
        int producedValue;
    
        for(int i=0; i<numElements; i++) {
            try {
    
                /* The "Producer" will generate a random number and assign it to the shared
                 * array. It then prints out a message for the user. 
                */
                Thread.sleep(generator.nextInt(5000));        
        
                producedValue = generator.nextInt(150);
                values[i] = producedValue;
                System.out.println(Thread.currentThread().getName() + ": Produced element "+ i + ": " + producedValue);        
            }//end of try
            catch(InterruptedException e) {
                //nothing to see here
            }
            finally {
                //increment the semaphore so the reader knows there's a value available
                semaphore.release(); 
		System.out.println(Thread.currentThread().getName() + ": \t\t\tpermits available: " + semaphore.availablePermits());
            }
        }//end of for    
    }//end of run
  
}//end of class
