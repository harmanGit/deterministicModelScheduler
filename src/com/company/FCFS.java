package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class FCFS extends Scheduler {

    public FCFS(String[] parsedUserInput) {
        super(parsedUserInput);
    }

    @Override
    void simulation() {
        PriorityQueue<Process> readyQueue = getReadyQueue();
        Process currentProcess;
        Process previousProcess;
        int totalProcess = readyQueue.size();
        double waitTime = 0;
        double totalWaitTime = 0;
        double averageWaitTime = 0;

        while (!readyQueue.isEmpty()) {
            previousProcess = readyQueue.poll();//looking at previous process
            currentProcess = readyQueue.peek();//getting the first process

            if (currentProcess != null) {
                waitTime = waitTime + (previousProcess.cpuTime +
                        (previousProcess.arrivalTime - currentProcess.arrivalTime));

                //set wait time for the process using a setter : currentProcess.setWaitingTime()

                totalWaitTime += waitTime;
            }
        }
        System.out.println("FCFS: ");
        averageWaitTime = totalWaitTime / totalProcess;
        System.out.println("Average Waiting Time: " + averageWaitTime);
    }


    @Override
    void execute() {
        populateReadyQueue();
        //printReadyQueue();
        simulation();
    }

    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(process -> process.arrivalTime);
    }
}