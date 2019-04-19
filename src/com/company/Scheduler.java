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
    private String[] parsedUserInput;
    private int initialCapacity;

    public Scheduler(String[] parsedUserInput) {
        this.parsedUserInput = parsedUserInput;
        this.initialCapacity = parsedUserInput.length;

    }

    public void populateReadyQueue(){
        this.readyQueue = new PriorityQueue<>(initialCapacity, comparator());
        for (int i = 0; i < parsedUserInput.length; i++)
            readyQueue.add(new Process(Character.toString((char) ('A' + i)), Double.valueOf(parsedUserInput[i]), Double.valueOf(parsedUserInput[i++])));
    }

    public void printReadyQueue(){
        for (Process singleProcess : readyQueue){
            System.out.println(singleProcess.toString() + ", ");
        }
    }

    public void simulation(){
        Process tempProcess = new Process();
        double totalWaitTime = 0;
        double averageWaitTime = 0;
        while(!readyQueue.isEmpty()){
            tempProcess = readyQueue.poll();
            //totalWaitTime+=tempProcess.cpuTime
            for (int i = 0; i < tempProcess.cpuTime; i++){}
        }
    }

    abstract void execute();

    abstract Comparator<Process> comparator();
}