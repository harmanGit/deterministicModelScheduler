package com.company;

import java.util.Comparator;
import java.util.Queue;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class FCFS extends Scheduler {
    private final String SCHEDULERTYPE = "FCFS";

    public FCFS(Queue<String> userInputQueue) {
        super(userInputQueue);
    }

    @Override
    void simulation() {
        defaultSimulation(SCHEDULERTYPE);
    }

    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(Process::getArrivalTime);
    }
}