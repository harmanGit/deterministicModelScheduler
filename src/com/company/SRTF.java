package com.company;

import java.util.Comparator;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class SRTF extends Scheduler {

    public SRTF(String[] parsedUserInput) {
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