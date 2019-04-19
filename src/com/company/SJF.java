package com.company;

import java.util.Comparator;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class SJF extends Scheduler {

    public SJF(String[] parsedUserInput) {
        super(parsedUserInput);
    }

    @Override
    void execute() {
        populateReadyQueue();
//        printReadyQueue();
    }

    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(process -> process.cpuTime);
    }
}