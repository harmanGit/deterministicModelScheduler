package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class SJF extends Scheduler {

    public SJF(Queue<String> userInputQueue) {
        super(userInputQueue);
    }

    @Override
    void simulation() {
//        Process previousProcess = new Process();
//        Process currentProcess;
//        int totalProcess = getInitialCapacity()/2;
//        double waitTime = 0;
//        double totalWaitTime = 0;
//        double averageWaitTime;
//
//        for (int i = 0; i < getInitialCapacity(); i++) {
//            if(i != 0)
//                addToReadyQueue(new Process(Character.toString((char) ('A' + i)), Double.valueOf(getParsedUserInput()[i]), 0.0, Double.valueOf(getParsedUserInput()[++i])));
//            else
//                previousProcess = new Process(Character.toString((char) ('A' + i)), Double.valueOf(getParsedUserInput()[i]), 0.0, Double.valueOf(getParsedUserInput()[++i]));
//        }
//
//        while (!isEmptyReadyQueue()) {
//            if(waitTime != 0){
//                previousProcess = pollReadyQueue();
//            }
//            currentProcess = peekReadyQueue();//getting the first process
//
//            if (currentProcess != null) {
//                waitTime = waitTime + (previousProcess.cpuTime +
//                        (previousProcess.arrivalTime - currentProcess.arrivalTime));
//
//                //set wait time for the process using a setter : currentProcess.setWaitingTime()
//
//                totalWaitTime += waitTime;
//            }
//        }
//        System.out.println("SJF: ");
//        averageWaitTime = totalWaitTime / totalProcess;
//        System.out.println("Average Waiting Time: " + averageWaitTime);
    }

    @Override
    void execute() {
        simulation();
    }

    Comparator<Process> comparator() {
        return Comparator.comparingDouble(process -> process.getCpuTime());
    }
}