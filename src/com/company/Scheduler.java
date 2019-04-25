package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * Abstract class, intended to used to create CPU scheduling algorithms(with sudo simulations).
 *
 * Abstract Methods:
 *  simulation()
 *  comparator()
 *
 * @author Harman Dhillon (4/18/2019)
 */
public abstract class Scheduler {
    private PriorityQueue<Process> readyQueue;//
    private Queue<String> userInputQueue;
    private int totalQueueSize;

    protected Scheduler(String[] parsedUserInput) {
        this.userInputQueue = deepCopy(parsedUserInput);
        this.totalQueueSize = userInputQueue.size();
        this.readyQueue = new PriorityQueue<>(totalQueueSize, comparator());
    }

    protected String peekUserInputQueue() {
        return this.userInputQueue.peek();
    }

    protected String pollUserInputQueue() {
        return this.userInputQueue.poll();
    }

    protected boolean isEmptyUserInputQueue() {
        return this.userInputQueue.isEmpty();
    }

    protected PriorityQueue<Process> getReadyQueue() {
        return this.readyQueue;
    }

    protected int getTotalQueueSize() {
        return this.totalQueueSize / 2;//pairs of two (BUG: EXPLAIN)
    }

    protected boolean isEmptyReadyQueue() {
        return this.readyQueue.isEmpty();
    }

    protected void addToReadyQueue(Process process) {
        this.readyQueue.add(process);
    }

    protected Process pollReadyQueue() {
        return this.readyQueue.poll();
    }

    protected Process peekReadyQueue() {
        return this.readyQueue.peek();
    }

    protected DecimalFormat getDecimalFormat() {
        return new DecimalFormat("#.00");
    }

    protected void defaultSimulation(String typeOfScheduler) {
        PriorityQueue<Process> tempProcessQueue = new PriorityQueue<>(getTotalQueueSize(), comparator());//remove this
        Process process;
        int currentTime = 0;
        int letterCounter = 0;
        int numberOfProcessIterations = 0;
        double totalWaitTime = 0;
        double averageWaitTime;
        double waitTime = 0;
        System.out.print(typeOfScheduler + ": ");

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

            if (!readyQueue.isEmpty()) {
                compute();
                numberOfProcessIterations++;
                if (!tempProcessQueue.isEmpty()) {//wait time increase
                    for (Process p : tempProcessQueue)
                        p.setWaitingTime(p.getWaitingTime() + 1);
                }

                if (readyQueue.peek().getCpuTime() == 0) {
                    process = readyQueue.poll();
                    totalWaitTime += process.getWaitingTime();// set total wait time
                    displayProcess(process,numberOfProcessIterations);
                    numberOfProcessIterations = 0;
                }
            } else if (!tempProcessQueue.isEmpty())
                readyQueue.add(tempProcessQueue.poll());//moving from temp q to ready q

            if (userInputQueue.isEmpty() && readyQueue.isEmpty() && tempProcessQueue.isEmpty())//ending condition
                break;

            currentTime++;
        }

        System.out.println("");
        averageWaitTime = totalWaitTime / getTotalQueueSize();
        System.out.println("Average Waiting Time: " + getDecimalFormat().format(averageWaitTime));
    }

    protected void displayProcess(Process process, int iteration) {
        System.out.print(" " + process.getProcessID() + iteration);
    }

    private void compute() {
        this.readyQueue.peek().setCpuTime(this.readyQueue.peek().getCpuTime() - 1);
    }

    private Queue<String> deepCopy(String[] parsedUserInput){
        Queue<String> tempQueue = new LinkedList<>();
        tempQueue.addAll(Arrays.asList(parsedUserInput));
        return tempQueue;
    }

    abstract void simulation();

    abstract Comparator<Process> comparator();
}