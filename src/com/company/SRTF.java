package com.company;

import java.util.Comparator;
import java.util.Queue;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class SRTF extends Scheduler {
    private final String SCHEDULERTYPE = "SRTF";

    public SRTF(Queue<String> userInputQueue) {
        super(userInputQueue);
    }

    @Override
    void simulation() {
        Process process;
        int currentTime = 0;
        int letterCounter = 0;
        double totalWaitTime = 0;
        double averageWaitTime;
        double waitTime = 0;

        while (true) {
            if (!isEmptyUserInputQueue() && Integer.parseInt(peekUserInputQueue()) == currentTime) {
                process = new Process(Character.toString((char) ('A' + letterCounter++)),
                        Double.valueOf(pollUserInputQueue()), Double.valueOf(pollUserInputQueue()));
                if (waitTime != 0) {
                    addToReadyQueue(process);
                } else {
                    addToReadyQueue(process);
                    waitTime = process.getCpuTime();
                }
            }

            if (!isEmptyReadyQueue()) {
                process = pollReadyQueue();
//                System.err.println(process.toString());
                process.setCpuTime(process.getCpuTime() -1);

                if(!isEmptyReadyQueue()){//wait time increase
                    for (Process p : getReadyQueue())
                        p.setWaitingTime(p.getWaitingTime() + 1);
                }

                if (process.getCpuTime() != 0)
                    addToReadyQueue(process);
                else
                    totalWaitTime += process.getWaitingTime();
            }


            if(isEmptyUserInputQueue() && isEmptyReadyQueue())
                break;
            currentTime++;
        }

        System.err.println("total wait time: " + totalWaitTime);
        System.out.println(SCHEDULERTYPE + ": ");
        averageWaitTime = totalWaitTime / getTotalQueueSize();
        System.out.println("Average Waiting Time: " + averageWaitTime);
    }


    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(Process::getCpuTime);
    }
}