package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class FCFS extends Scheduler {
    public FCFS(Queue<String> userInputQueue) {
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

        while(!isEmptyUserInputQueue()){
            if(Integer.parseInt(peekUserInputQueue()) == currentTime) {

                if(waitTime != 0) {
                    process = new Process(Character.toString((char) ('A' + letterCounter++)), Double.valueOf(pollUserInputQueue()), Double.valueOf(pollUserInputQueue()));
                    process.setWaitingTime(waitTime);
                    totalWaitTime = totalWaitTime + waitTime;
                    waitTime = waitTime + process.getCpuTime();

                }else {
                    process = new Process(Character.toString((char) ('A' + letterCounter++)), Double.valueOf(pollUserInputQueue()),waitTime,Double.valueOf(pollUserInputQueue()));
                    waitTime = process.getCpuTime();
                }

                System.err.println(process.toString());
                System.err.println(totalWaitTime);
                addToReadyQueue(process);

                if(!isEmptyReadyQueue()){
                    if(currentTime >= peekReadyQueue().getWaitingTime())
                        pollReadyQueue();
                }

                waitTime--;
            }

            currentTime++;
        }


        System.out.println("FCFS: ");
        averageWaitTime = totalWaitTime / (getInitialCapacity()/2);//pairs of two
        System.out.println("Average Waiting Time: " + averageWaitTime);
    }

    @Override
    void execute() {
        //printReadyQueue();
        simulation();
    }

    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(process -> process.getArrivalTime());
    }
}