package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Extends concrete subclasses SRTF, SJF, and FCFS responsible
 * for managing the respective simulations.
 *
 * @author Harman Dhillon (4/18/2019)
 */
public abstract class Scheduler {
    private PriorityQueue<Process> readyQueue;
    private Queue<String> userInputQueue;
    private int initialCapacity;

    public Scheduler(Queue<String> userInputQueue) {
        this.userInputQueue = userInputQueue;
        this.initialCapacity = userInputQueue.size();
        this.readyQueue = new PriorityQueue<>(initialCapacity, comparator());
    }

//    public PriorityQueue<Process> getReadyQueue() {
//    return readyQueue;
//}
//
//    public void printReadyQueue(){
//        for (Process singleProcess : readyQueue){
//            System.out.println(singleProcess.toString() + ", ");
//        }
//    }

    public String peekUserInputQueue(){return  userInputQueue.peek();}


    public String pollUserInputQueue(){return userInputQueue.poll();}

    public boolean isEmptyUserInputQueue(){return userInputQueue.isEmpty();}

    public PriorityQueue<Process> getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(PriorityQueue<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public boolean isEmptyReadyQueue(){return readyQueue.isEmpty();}

    public void addToReadyQueue(Process process){readyQueue.add(process);}

    public Process pollReadyQueue(){return readyQueue.poll();}

    public Process peekReadyQueue(){return readyQueue.peek();}

    private Comparator<Process> defaultComparator(){return Comparator.comparingDouble(process -> process.getArrivalTime());}

    abstract void simulation();

    abstract void execute();

    abstract Comparator<Process> comparator();
}