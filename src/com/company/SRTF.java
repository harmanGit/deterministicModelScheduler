package com.company;

import java.util.Comparator;
import java.util.Queue;

/**
 * @author Harman Dhillon (4/18/2019)
 */
public class SRTF extends Scheduler {

    public SRTF(Queue<String> userInputQueue) {
        super(userInputQueue);
    }

    @Override
    void simulation() {
//        Process process;
//        int currentTime = 0;
//        int letterCounter = 0;
//        double totalWaitTime = 0;
//        double averageWaitTime;
//        double waitTime = 0;
//
//        while (true) {
//            if (!isEmptyUserInputQueue() && Integer.parseInt(peekUserInputQueue()) == currentTime) {
//                process = new Process(Character.toString((char) ('A' + letterCounter++)),
//                        Double.valueOf(pollUserInputQueue()), Double.valueOf(pollUserInputQueue()));
//                if (waitTime != 0) {
//                    addToReadyQueue(process);//tempProcessQueue.add(process);
//                } else {
//                    addToReadyQueue(process);//
//                    waitTime = process.getCpuTime();
//                }
//            }
//
//            if (!isEmptyReadyQueue() && peekReadyQueue().getCpuTime() != 0) {//computing
//                process = pollReadyQueue();
//                process.setCpuTime(process.getCpuTime() -1);
//                if (process.getCpuTime() == 0) {
//                    totalWaitTime += process.getWaitingTime();
//                }
//                if(!isEmptyReadyQueue()){//wait time increase
//                    for (Process p : getReadyQueue())
//                        p.setWaitingTime(p.getWaitingTime() + 1);
//                }
//                addToReadyQueue(process);
//                System.err.println(peekReadyQueue().toString());
//            }
//
//            if(isEmptyUserInputQueue() && isEmptyReadyQueue())
//                break;
//            currentTime++;
//        }
//        System.err.println("total wait time" + totalWaitTime);
//        System.out.println("SRTF: ");
//        averageWaitTime = totalWaitTime / getTotalQueueSize();
//        System.out.println("Average Waiting Time: " + averageWaitTime);
    }


    @Override
    Comparator<Process> comparator() {
        return Comparator.comparingDouble(Process::getCpuTime);
    }
}