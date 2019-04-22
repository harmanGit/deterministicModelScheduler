package com.company;

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
    private int totalQueueSize;

    public Scheduler(Queue<String> userInputQueue) {
        this.userInputQueue = userInputQueue;
        this.totalQueueSize = userInputQueue.size();
        this.readyQueue = new PriorityQueue<>(totalQueueSize, comparator());
    }

    public String peekUserInputQueue() {
        return  this.userInputQueue.peek();
    }

    public String pollUserInputQueue() {
        return  this.userInputQueue.poll();
    }

    public boolean isEmptyUserInputQueue() {
        return  this.userInputQueue.isEmpty();
    }

    public PriorityQueue<Process> getReadyQueue() {
        return  this.readyQueue;
    }

    public int getTotalQueueSize() {
        return  this.totalQueueSize / 2;//pairs of two (BUG: EXPLAIN)
    }

    public boolean isEmptyReadyQueue() {
        return  this.readyQueue.isEmpty();
    }

    public void addToReadyQueue(Process process) {
        this.readyQueue.add(process);
    }

    public Process pollReadyQueue() {
        return  this.readyQueue.poll();
    }

    public Process peekReadyQueue() {
        return  this.readyQueue.peek();
    }

    public void defaultSimulation(String typeOfScheduler){
        PriorityQueue<Process> tempProcessQueue = new PriorityQueue<>(getTotalQueueSize(), comparator());
        Process process;
        int currentTime = 0;
        int letterCounter = 0;
        double totalWaitTime = 0;
        double averageWaitTime;
        double waitTime = 0;

        while (true) {
            //going through the user input
            if (!userInputQueue.isEmpty() && Integer.parseInt(userInputQueue.peek()) == currentTime) {
                process = new Process(Character.toString((char) ('A' + letterCounter++)),
                        Double.valueOf(userInputQueue.poll()), Double.valueOf(userInputQueue.poll()));
                if (waitTime != 0) {
                    tempProcessQueue.add(process);
                } else {
                    readyQueue.add(process);
                    waitTime = process.getCpuTime();
                }
            }

//            if(!readyQueue.isEmpty()){
//
//            }


            if (!readyQueue.isEmpty() && readyQueue.peek().getCpuTime() != 0) {//computing
                compute();
                if (!tempProcessQueue.isEmpty()) {//wait time increase
                    for (Process p : tempProcessQueue)
                        p.setWaitingTime(p.getWaitingTime() + 1);
                }
            } else if (!readyQueue.isEmpty() && readyQueue.peek().getCpuTime() == 0) {
                totalWaitTime += readyQueue.poll().getWaitingTime();// set total wait time
            }
            if (readyQueue.isEmpty() && !tempProcessQueue.isEmpty()) {//moving temp q to ready q
                readyQueue.add(tempProcessQueue.poll());
            }





            if (userInputQueue.isEmpty() && readyQueue.isEmpty() && tempProcessQueue.isEmpty())
                break;

            currentTime++;
        }

        System.out.println(typeOfScheduler + ": ");
        averageWaitTime = totalWaitTime / getTotalQueueSize();
        System.out.println("Average Waiting Time: " + averageWaitTime);
    }

    private double compute() {
        this.readyQueue.peek().setCpuTime( this.readyQueue.peek().getCpuTime() - 1);
        return  this.readyQueue.peek().getCpuTime();
    }

    abstract void simulation();

    abstract Comparator<Process> comparator();
}