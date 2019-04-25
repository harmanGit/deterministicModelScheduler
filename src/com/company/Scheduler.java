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
     * decremented by one. Method will also keep track of the total waiting time for all processes (in order to compute
     * the average when the simulation ends and displays it).
     *
     * @param typeOfScheduler <code>String</code> represents the name of the CPU scheduler.
     */
    protected void defaultSimulation(String typeOfScheduler) {
        //Temp PriorityQueue used to store sorted processes that are not yet ready to be executed, wait time is
        //is incremented only in this queue
        PriorityQueue<Process> tempProcessQueue = new PriorityQueue<>(getReadyQueueSize(), comparator());
        Process process;
        int currentTime = 0;
        int letterCounter = 0;//counter user to increment through the letters for the process id
        int numberOfProcessIterations = 0;//total number of times a process is "executing" on the CPU
        double totalWaitTime = 0;
        double averageWaitTime;
        boolean firstProcessExecuted = false;//meant to add the first process to the ready queue(only ever used once)
        System.out.print(typeOfScheduler + ": ");

        while (true) {//loop that repeats once for each unit of time.
             //If a process is arriving at the current time, it is put into the ready queue or temp queue.
            if (!userInputQueue.isEmpty() && Integer.parseInt(userInputQueue.peek()) == currentTime) {
                //creating process object, for either the ready queue or temp queue
                process = new Process(Character.toString((char) ('A' + letterCounter++)),
                        Double.valueOf(userInputQueue.poll()), Double.valueOf(userInputQueue.poll()));
                if (firstProcessExecuted)//if its the process process ever then its added directly in the ready queue
                    tempProcessQueue.add(process);//process added to the temp queue as the "CPU" busy
                else {
                    readyQueue.add(process);
                    firstProcessExecuted = true;//insuring nothing else is added directly to the ready queue again
                }
            }

            if (!readyQueue.isEmpty()) {
                compute();//decrementing the current processes cpu time
                numberOfProcessIterations++;
                    for (Process p : tempProcessQueue)//increasing the wait time for all process not executing
                        p.setWaitingTime(p.getWaitingTime() + 1);
                if (readyQueue.peek().getCpuTime() == 0) {//checking if a process has completed its task (cpuTime == 0)
                    process = readyQueue.poll();//process thats completed being removed
                    totalWaitTime += process.getWaitingTime();//setting the new total wait time
                    displayProcess(process,numberOfProcessIterations);
                    numberOfProcessIterations = 0;//setting process iterations to 0, as the process has been completed
                }
            } else if (!tempProcessQueue.isEmpty())//if ready queue is empty than another process can now be executed
                readyQueue.add(tempProcessQueue.poll());//moving process from temp queue to the ready queue

            if (userInputQueue.isEmpty() && readyQueue.isEmpty() && tempProcessQueue.isEmpty())//end condition
                break;

            currentTime++;
        }

        System.out.println("");//formatting
        averageWaitTime = totalWaitTime / getReadyQueueSize();
        System.out.println("Average Waiting Time: " + getDecimalFormat().format(averageWaitTime));
    }

    /**
     * 
     * @param process
     * @param iteration
     */
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