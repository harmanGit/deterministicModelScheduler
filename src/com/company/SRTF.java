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

    /**
     * Method is used to (sudo)simulate the SRTF CPU scheduler. Simulation consists of a loop that repeats once for
     * each unit of time (single <code>int</code>). If a process is arriving at the current time, it is put into
     * the ready queue. When a process finishes executing, it is removed from the system and another is taken from
     * the queue (if not empty). Each time a process is being "executed", the CPU time of the process simply gets
     * decremented by one. Method will also keep track of the total waiting time for all processes (in order to
     * compute the average when the simulation ends and displays it).
     */
    @Override
    void simulation() {
        Process process = null;
        String previousProcess = "A";//always starts with this process
        int currentTime = 0;
        int letterCounter = 0;//counter user to increment through the letters for the process id
        int numberOfProcessIterations = 0;
        double totalWaitTime = 0;
        double averageWaitTime;

        System.out.print(SCHEDULERTYPE + ": ");

        while (true) {//loop that repeats once for each unit of time.
            if (!isEmptyUserInputQueue() && Integer.parseInt(peekUserInputQueue()) == currentTime)
                //if current time matches arrival time, then creating and adding a process to the ready queue
                addToReadyQueue(new Process(Character.toString((char) ('A' + letterCounter++)),
                        Double.valueOf(pollUserInputQueue()), Double.valueOf(pollUserInputQueue())));

            if (!isEmptyReadyQueue()) {
                process = pollReadyQueue();//getting the shortest job remaining (shortest CPU) process
                process.setCpuTime(process.getCpuTime() - 1);//decrementing the current processes cpu time("executing")

                //checking to see if its a new process, and not the old one
                if (!previousProcess.equals(process.getProcessID())) {
                    displayProcess(previousProcess, numberOfProcessIterations);
                    numberOfProcessIterations = 0;//setting process iterations to 0, as the process has been completed
                }

                previousProcess = process.getProcessID();//setting the current process as the previous one

                for (Process p : getReadyQueue())//increasing the wait time for all processes not executing
                    p.setWaitingTime(p.getWaitingTime() + 1);

                //adding the process back to the ready queue if its not complete (CPU time = 0)
                if (process.getCpuTime() != 0)
                    addToReadyQueue(process);
                else //if the process was complete, then the total time is increased with its wait time
                    totalWaitTime += process.getWaitingTime();
            }

            if (process != null)//only starting to increment the process counter if process ever exists
                numberOfProcessIterations++;//process was execute, so iteration must be incremented

            if (isEmptyUserInputQueue() && isEmptyReadyQueue()) { //end condition
                displayProcess(previousProcess, numberOfProcessIterations);//display the last completed process
                break;
            }
            currentTime++;
        }

        System.out.println();//formatting
        averageWaitTime = totalWaitTime / getReadyQueueSize();//calculating the average wait time
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