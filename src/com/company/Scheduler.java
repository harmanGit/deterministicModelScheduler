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
        return userInputQueue.peek();
    }

    public String pollUserInputQueue() {
        return userInputQueue.poll();
    }

    public boolean isEmptyUserInputQueue() {
        return userInputQueue.isEmpty();
    }

    public PriorityQueue<Process> getReadyQueue() {
        return readyQueue;
    }

    public int getTotalQueueSize() {
        return totalQueueSize / 2;//pairs of two (BUG: EXPLAIN)
    }

    public boolean isEmptyReadyQueue() {
        return readyQueue.isEmpty();
    }

    public void addToReadyQueue(Process process) {
        readyQueue.add(process);
    }

    public Process pollReadyQueue() {
        return readyQueue.poll();
    }

    public Process peekReadyQueue() {
        return readyQueue.peek();
    }

    public double compute() {
        readyQueue.peek().setCpuTime(readyQueue.peek().getCpuTime() - 1);
        return readyQueue.peek().getCpuTime();
    }

    abstract void simulation();

    abstract Comparator<Process> comparator();
}