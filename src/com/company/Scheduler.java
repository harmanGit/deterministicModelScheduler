package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Extends concrete subclasses SRTF, SJF, and FCFS responsible
 * for managing the respective simulations.
 *
 * @author Harman Dhillon (4/18/2019)
 */
public abstract class Scheduler {
    private PriorityQueue<Process> readyQueue;

    public String[] getParsedUserInput() {
        return parsedUserInput;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    private String[] parsedUserInput;
    private int initialCapacity;

    public Scheduler(String[] parsedUserInput) {
        this.parsedUserInput = parsedUserInput;
        this.initialCapacity = parsedUserInput.length;
        this.readyQueue = new PriorityQueue<>(initialCapacity, defaultComparator());
    }

    public Scheduler(String[] parsedUserInput,  Comparator<Process> comparator) {
        this.parsedUserInput = parsedUserInput;
        this.initialCapacity = parsedUserInput.length;
        this.readyQueue = new PriorityQueue<>(initialCapacity, comparator);
    }

    public PriorityQueue<Process> getReadyQueue() {
    return readyQueue;
}

    public void printReadyQueue(){
        for (Process singleProcess : readyQueue){
            System.out.println(singleProcess.toString() + ", ");
        }
    }
    public boolean isEmptyReadyQueue(){return readyQueue.isEmpty();}

    public void addToReadyQueue(Process process){readyQueue.add(process);}

    public Process pollReadyQueue(){return readyQueue.poll();}

    public Process peekReadyQueue(){return readyQueue.peek();}

    private Comparator<Process> defaultComparator(){return Comparator.comparingDouble(process -> process.arrivalTime);}

    abstract void simulation();

    abstract void execute();

    abstract Comparator<Process> comparator();
}