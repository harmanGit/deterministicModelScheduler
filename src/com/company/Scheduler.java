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

    public double compute() {
        this.readyQueue.peek().setCpuTime( this.readyQueue.peek().getCpuTime() - 1);
        return  this.readyQueue.peek().getCpuTime();
    }

    abstract void simulation();

    abstract Comparator<Process> comparator();
}