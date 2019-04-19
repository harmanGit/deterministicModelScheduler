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
        int totalProcess = getInitialCapacity()/2;
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
                }
            } else
                addToReadyQueue(new Process(Character.toString((char) ('A' + i)), Double.valueOf(getParsedUserInput()[i]), 0.0, Double.valueOf(getParsedUserInput()[++i])));

            totalWaitTime += waitTime;

        }
        System.out.println("FCFS: ");
        averageWaitTime = totalWaitTime / totalProcess;
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