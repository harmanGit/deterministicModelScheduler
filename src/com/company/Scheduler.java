package com.company;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Abstract class, intended to used to create CPU scheduling algorithms(with sudo simulations).
 * <p>
 * Abstract Methods:
 * simulation()
 * comparator()
 *
 * @author Harman Dhillon (4/18/2019)
 */
public abstract class Scheduler {
    private PriorityQueue<Process> readyQueue;//queue contain all ready processes (to be executed)
    private Queue<String> userInputQueue;//user input representing the arrival and cpu burst time of processes
    private int totalQueueSize;//size of the userInputQueue

    /**
     * Explicit Constructor.
     *
     * @param parsedUserInput <code>String[]</code> represents the parsed user input, which itself should contain
     *                        the arrival and cpu burst time of each process.
     */
    protected Scheduler(String[] parsedUserInput) {
        this.userInputQueue = deepCopy(parsedUserInput);
        this.totalQueueSize = userInputQueue.size();
        this.readyQueue = new PriorityQueue<>(totalQueueSize, comparator());
    }

    /**
     * Methods returns a <code>String</code>, but does not remove, the head of the <code>userInputQueue</code>
     *
     * @return <code>String</code> representing a single data point from the user input.
     */
    protected String peekUserInputQueue() {
        return this.userInputQueue.peek();
    }

    /**
     * Method returns a <code>String</code> and removes the head of the <code>userInputQueue</code>,
     * or returns null if this queue is empty.
     *
     * @return <code>String</code> representing a single data point from the user input.
     */
    protected String pollUserInputQueue() {
        return this.userInputQueue.poll();
    }

    /**
     * Method returns true if the <code>userInputQueue</code> contains no elements.
     *
     * @return <code>boolean</code> true if the <code>userInputQueue</code> is empty, else false.
     */
    protected boolean isEmptyUserInputQueue() {
        return this.userInputQueue.isEmpty();
    }

    /**
     * Method returns the entire <code>readyQueue</code> containing all ready processes to be executed.
     *
     * @return <code>PriorityQueue<Process></code> containing all ready processes to be executed.
     */
    protected PriorityQueue<Process> getReadyQueue() {
        return this.readyQueue;
    }

    /**
     * Method returns the max potential size of the <code>readyQueue</code>. Calculated by taking
     * the <code>totalQueueSize</code> and dividing it by 2.
     *
     * @return <code>int</code> representing the max potential size of the <code>readyQueue</code>.
     */
    protected int getReadyQueueSize() {
        return this.totalQueueSize / 2;
    }

    /**
     * Method returns true if the <code>readyQueue</code> contains no elements.
     *
     * @return <code>boolean</code> true if the <code>readyQueue</code> is empty, else false.
     */
    protected boolean isEmptyReadyQueue() {
        return this.readyQueue.isEmpty();
    }

    /**
     * Method adds a <code>Process</code> to the proper location in the <code>readyQueue</code>.
     *
     * @param process <code>Process</code> representing a single process object.
     */
    protected void addToReadyQueue(Process process) {
        this.readyQueue.add(process);
    }

    /**
     * Method returns a <code>Process</code> and removes the head of the <code>readyQueue</code>,
     * or returns null if this queue is empty.
     *
     * @return <code>Process</code> representing a single process object.
     */
    protected Process pollReadyQueue() {
        return this.readyQueue.poll();
    }

    /**
     * Methods returns a <code>Process</code>, but does not remove, the head of this <code>readyQueue</code>
     *
     * @return <code>Process</code> representing a single process object.
     */
    protected Process peekReadyQueue() {
        return this.readyQueue.peek();
    }

    /**
     * Method creates and returns <code>DecimalFormat</code> using the default pattern of symbols {#.00}
     *
     * @return <code>DecimalFormat</code> using the default pattern of symbols {#.00}
     */
    protected DecimalFormat getDecimalFormat() {
        return new DecimalFormat("#.00");
    }

    /**
     * Method is used to (sudo)simulate a basic CPU scheduler. Simulation consists of a loop that repeats once for
     * each unit of time (single <code>int</code>). If a process is arriving at the current time, it is put into
     * the ready queue. When a process finishes executing, it is removed from the system and another is taken from
     * the queue (if not empty). Each time a process is being "executed", the CPU time of the process simply gets
     * decremented by one. Method will also keep track of the total waiting time for all processes (in order to
     * compute the average when the simulation ends and displays it).
     *
     * @param typeOfScheduler <code>String</code> represents the name of the CPU scheduler.
     */
    protected void defaultSimulation(String typeOfScheduler) {
        Process process = null;
        int currentTime = 0;
        int letterCounter = 0;//counter user to increment through the letters for the process id
        int numberOfProcessIterations = 0;//total number of times a process is "executing" on the CPU
        double totalWaitTime = 0;
        double averageWaitTime;
        boolean canCompute = false;//boolean is used to check if the CPU has a process to execute
        System.out.print(typeOfScheduler + ": ");

        while (true) {//loop that repeats once for each unit of time.
            //if current time matches arrival time, then creating and adding a process to the ready queue
            if (!userInputQueue.isEmpty() && Integer.parseInt(userInputQueue.peek()) == currentTime)
                readyQueue.add(new Process(Character.toString((char) ('A' + letterCounter++)),
                        Double.valueOf(userInputQueue.poll()), Double.valueOf(userInputQueue.poll())));

            if (canCompute) {//checking if the "CPU" has a process to execute
                numberOfProcessIterations++;
                process.setCpuTime(process.getCpuTime() - 1);//decrementing the current processes cpu time("executing")

                for (Process p : readyQueue)//increasing the wait time for all processes not executing
                    p.setWaitingTime(p.getWaitingTime() + 1);

                if (process.getCpuTime() == 0) {
                    //if the process was complete, then the total time is increased with its wait time
                    totalWaitTime += process.getWaitingTime();
                    displayProcess(process.getProcessID(), numberOfProcessIterations);
                    //setting process iterations to 0, as the process has been completed
                    numberOfProcessIterations = 0;//setting process iterations to 0, as the process has been completed
                    canCompute = false;
                    if (userInputQueue.isEmpty() && readyQueue.isEmpty())//end condition
                        break;
                }
            } else if (!readyQueue.isEmpty()) {
                process = readyQueue.poll();//getting the "CPU" a process to execute
                canCompute = true;
            }

            currentTime++;
        }

        System.out.println();
        averageWaitTime = (totalWaitTime - (getReadyQueueSize() - 1)) / getReadyQueueSize();
        System.out.println("Average Waiting Time: " + getDecimalFormat().format(averageWaitTime));
    }

    /**
     * Method is used to print out the process ID and the number of times the process executed
     *
     * @param processID <code>String</code> represents the process ID.
     * @param iteration <code>int</code> represents number of times a process executed so far.
     */
    protected void displayProcess(String processID, int iteration) {
        System.out.print(" " + processID + iteration);
    }

    /**
     * Method is used to deep copy the user input <code>String[]</code> into a <code>Queue<String></code>.
     *
     * @param parsedUserInput <code>String[]</code> represents the parsed user input, which itself should contain
     *                        the arrival and cpu burst time of each process.
     * @return <code>Queue<String></code> representing the user input as a queue
     */
    private Queue<String> deepCopy(String[] parsedUserInput) {
        Queue<String> tempQueue = new LinkedList<>();
        tempQueue.addAll(Arrays.asList(parsedUserInput));
        return tempQueue;
    }

    /**
     * Abstract method is used to (sudo)simulate a basic CPU scheduler. Simulation consists of a loop that repeats once
     * for each unit of time (single <code>int</code>). If a process is arriving at the current time, it is put into
     * the ready queue. When a process finishes executing, it is removed from the system and another is taken from
     * the queue (if not empty). Each time a process is being "executed", the CPU time of the process simply gets
     * decremented by one. Method will also keep track of the total waiting time for all processes (in order to compute
     * the average when the simulation ends and displays it).
     */
    abstract void simulation();

    /**
     * Abstract method returns a comparator for the process object.
     *
     * @return <code>Comparator<Process></code>
     */
    abstract Comparator<Process> comparator();
}