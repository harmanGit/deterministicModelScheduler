package com.company;

import java.util.Comparator;
import java.util.Queue;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class SJF extends Scheduler {
    private final String SCHEDULERTYPE = "SJF";

    public SJF(Queue<String> userInputQueue) {
        super(userInputQueue);
    }

    @Override
    void simulation() {
        defaultSimulation(SCHEDULERTYPE);
    }

    Comparator<Process> comparator() {
        return Comparator.comparingDouble(Process::getCpuTime);
    }
}