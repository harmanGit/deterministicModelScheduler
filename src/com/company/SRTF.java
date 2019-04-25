package com.company;

import java.util.Comparator;

/**
 * Concrete subclass extending the abstract Scheduler class for the SRTF(CPU scheduling algorithm).
 *
 * @author Harman Dhillon (4/18/2019)
 */
public class SRTF extends Scheduler {
    private final String SCHEDULERTYPE = "SRTF";//Represents the scheduling algorithms name

    /**
     * Explict Constructor.
     *
     * @param parsedUserInput <code>String[]</code> represents the user input containing
     *                        the arrival times and cpu burst lengths for all the processes
     */
    public SRTF(String[] parsedUserInput) {
        super(parsedUserInput);
    }

    @Override
    void simulation() {
        Process process;
        String previousProcess = "A";
        int currentTime = 0;
        int letterCounter = 0;
        int numberOfProcessIterations = -1;
        double totalWaitTime = 0;
        double averageWaitTime;

        System.out.print(SCHEDULERTYPE + ": ");

        while (true) {
            if (!isEmptyUserInputQueue() && Integer.parseInt(peekUserInputQueue()) == currentTime) {
                addToReadyQueue(new Process(Character.toString((char) ('A' + letterCounter++)),
                        Double.valueOf(pollUserInputQueue()), Double.valueOf(pollUserInputQueue())));
            }

            if (!isEmptyReadyQueue()) {
                process = pollReadyQueue();
                process.setCpuTime(process.getCpuTime() - 1);

                numberOfProcessIterations++;

                if (!previousProcess.equals(process.getProcessID())) {
                    System.out.print(previousProcess + numberOfProcessIterations + " ");
                    numberOfProcessIterations = 0;
                }

                previousProcess = process.getProcessID();

                for (Process p : getReadyQueue())
                    p.setWaitingTime(p.getWaitingTime() + 1);

                if (process.getCpuTime() != 0) {
                    addToReadyQueue(process);
                } else {
                    totalWaitTime += process.getWaitingTime();
                }

                if (isEmptyUserInputQueue() && isEmptyReadyQueue()) { //ending condition
                    numberOfProcessIterations++;
                    System.out.println(previousProcess + numberOfProcessIterations);
                    break;
                }
            }
            currentTime++;
        }

        averageWaitTime = totalWaitTime / getReadyQueueSize();
        System.out.println("Average Waiting Time: " + getDecimalFormat().format(averageWaitTime));
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