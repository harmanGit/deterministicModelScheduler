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
        Process previousProcess;
        Process currentProcess;
        int totalProcess = getInitialCapacity();
        double waitTime = 0;
        double totalWaitTime = 0;
        double averageWaitTime;
        System.err.println(getInitialCapacity());


        for (int i = 0; i < getInitialCapacity(); i++) {

            if(i != 0)
            {
                currentProcess = new Process(Character.toString((char) ('A' + i)), Double.valueOf(getParsedUserInput()[i]), 0.0, Double.valueOf(getParsedUserInput()[++i]));
                if(!isEmptyReadyQueue()) {
                    previousProcess = pollReadyQueue();

                    waitTime = previousProcess.waitingTime + (previousProcess.cpuTime -
                            (currentProcess.arrivalTime - previousProcess.arrivalTime));

                    currentProcess.setWaitingTime(waitTime);
                    addToReadyQueue(currentProcess);
//                    System.err.println("p: " + previousProcess.toString());
//                    System.err.println("c: " + currentProcess.toString());
//                    System.err.println("");
                }
            } else
                addToReadyQueue(new Process(Character.toString((char) ('A' + i)), Double.valueOf(getParsedUserInput()[i]), 0.0, Double.valueOf(getParsedUserInput()[++i])));

//            System.err.println("BEFORE: " + totalWaitTime);
            totalWaitTime += waitTime;
//            System.err.println("After: " + totalWaitTime);

        }
        System.out.println("FCFS: ");
        averageWaitTime = totalWaitTime / 4.0;
        System.out.println("Average Waiting Time: " + averageWaitTime);
    }
    
    @Override
    void execute() {
        //printReadyQueue();
        simulation();
    }

    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(process -> process.arrivalTime);
    }
}