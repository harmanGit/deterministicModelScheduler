package com.company;

import java.util.Comparator;

/**
 * Concrete subclass extending the abstract Scheduler class for the FCFS(CPU scheduling algorithm).
 * Using the abstract scheduler classes default simulation method.
 *
 * @author Harman Dhillon (4/18/2019)
 */
public class FCFS extends Scheduler {
    private final String SCHEDULERTYPE = "FCFS";//Represents the scheduling algorithms name

    /**
     * Explict Constructor.
     *
     * @param parsedUserInput <code>String[]</code> represents the user input containing
     *                        the arrival times and cpu burst lengths for all the processes
     */
    public FCFS(String[] parsedUserInput) {
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
     * Method returns a comparator for the process object(compares arrival times).
     *
     * @return <code>Comparator<Process></code> compares arrival times of a process object
     */
    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(Process::getArrivalTime);
    }
}