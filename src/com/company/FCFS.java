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
        double waitTime = 0;

        while(!isEmptyUserInputQueue()){

            if(waitTime != 0) {
                process = new Process(Character.toString((char) ('A' + currentTime)), Double.valueOf(pollUserInputQueue()), Double.valueOf(pollUserInputQueue()));
                process.setWaitingTime(waitTime);
                waitTime = waitTime + process.getCpuTime();

            }else {
                process = new Process(Character.toString((char) ('A' + currentTime)), Double.valueOf(pollUserInputQueue()),waitTime,Double.valueOf(pollUserInputQueue()));
                waitTime = process.getCpuTime();
            }

            System.err.println(process.toString());

//            System.err.println("Process ArrivalTime: " + process.getArrivalTime());
//            System.err.println("Process currentTime: " + currentTime);
            if(process.getArrivalTime() == currentTime) {
                System.err.println("Current Time: " + currentTime);

                addToReadyQueue(process);
            }

            if(!isEmptyReadyQueue()){
                if(currentTime >= peekReadyQueue().getWaitingTime())
                    pollReadyQueue();
            }

            currentTime++;
            waitTime--;
        }


//        Process previousProcess;
//        Process process;
//        int totalProcess = getInitialCapacity()/2;
//        double waitTime = 0;
//        double totalWaitTime = 0;
        double averageWaitTime = 0.0;
//        System.err.println(getInitialCapacity());
//
//
//        for (int i = 0; i < getInitialCapacity(); i++) {
//
//            if(i != 0)
//            {
//                process = new Process(Character.toString((char) ('A' + i)), Double.valueOf(getParsedUserInput()[i]), 0.0, Double.valueOf(getParsedUserInput()[++i]));
//                if(!isEmptyReadyQueue()) {
//                    previousProcess = pollReadyQueue();
//
//                    waitTime = previousProcess.waitingTime + (previousProcess.cpuTime -
//                            (process.arrivalTime - previousProcess.arrivalTime));
//
//                    process.setWaitingTime(waitTime);
//                    addToReadyQueue(process);
//                }
//            } else
//                addToReadyQueue(new Process(Character.toString((char) ('A' + i)), Double.valueOf(getParsedUserInput()[i]), 0.0, Double.valueOf(getParsedUserInput()[++i])));
//
//            totalWaitTime += waitTime;
//
//        }
        System.out.println("FCFS: ");
        averageWaitTime = currentTime / 4.0;
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