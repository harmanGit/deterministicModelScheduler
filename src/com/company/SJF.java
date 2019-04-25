package com.company;

import java.util.Comparator;

/**
 * Concrete subclass extending the abstract Scheduler class for the SJF(CPU scheduling algorithm).
 * Using the abstract scheduler classes default simulation method.
 *
 * @author Harman Dhillon (4/18/2019)
 */
public class SJF extends Scheduler {
    private final String SCHEDULERTYPE = "SJF";//Represents the scheduling algorithms name

    /**
     * Explict Constructor.
     *
     * @param parsedUserInput <code>String[]</code> represents the user input containing
     *                        the arrival times and cpu burst lengths for all the processes
     */
    public SJF(String[] parsedUserInput) {
        super(parsedUserInput);
    }

    /**
     * Method calls the abstract scheduler classes default simulation method.
     */
    @Override
    void simulation() {
        defaultSimulation(SCHEDULERTYPE);
    }

    /**
     * Method returns a comparator for the process object(compares cpu times).
     *
     * @return <code>Comparator<Process></code> compares cpu times of a process object
     */
    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(Process::getCpuTime);
    }
}