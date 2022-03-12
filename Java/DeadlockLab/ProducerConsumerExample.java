/*******************************************************************************
 *
 * Synchronization Example 1 - Producer/Consumer Problem
 * CS 311 Fall 2016
 * Author: Aaron Boudreaux, updated by Steve Sheehy
 * File: ProducerConsumerExample.java
 * Last Edit: 11/16/16
 *
 ******************************************************************************/

import java.util.concurrent.Semaphore;

public class ProducerConsumerExample {
    public static final int MAX_ELEMENTS = 50;

    public static void main(String[] args) {
        int[] nums = new int[MAX_ELEMENTS];

        Semaphore s = new Semaphore(0); //This is the initial value of the Semaphore
        Thread producer = new Thread(new Writer(s, nums, nums.length));
        Thread consumer = new Thread(new Reader(s, nums, nums.length));

        producer.setName("Producer");
        producer.start();
        consumer.setName("Consumer");
        consumer.start();

        try {
            producer.join();
            consumer.join();
        }//end of try	
        catch (InterruptedException e) {
            //shrug
        }//end of catch

    }//end of main  
}//end of class
