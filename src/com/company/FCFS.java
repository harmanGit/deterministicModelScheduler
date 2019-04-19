package com.company;

import java.util.Comparator;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class FCFS extends Scheduler {

    public FCFS(String[] parsedUserInput) {
        super(parsedUserInput);
    }

    @Override
    void execute() {
        populateReadyQueue();
//        printReadyQueue();
    }

    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(process -> process.arrivalTime);
    }
}