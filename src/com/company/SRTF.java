package com.company;

import java.util.Comparator;
import java.util.Queue;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class SRTF extends Scheduler {

    public SRTF(Queue<String> userInputQueue) {
        super(userInputQueue);
    }

    @Override
    void simulation() {
    }

    @Override
    void execute() {

//        printReadyQueue();
    }

    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(process -> process.getCpuTime());//cahnage this to wait time
    }
}