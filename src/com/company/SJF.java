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
        System.err.println("asdfasdf: " + peekUserInputQueue());
        PriorityQueue<Process> tempProcessQueue = new PriorityQueue<>(getTotalQueueSize(), comparator());
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
                    tempProcessQueue.add(process);
                } else {
                    addToReadyQueue(process);
                    waitTime = process.getCpuTime();
                }
            }

            if (!isEmptyReadyQueue() && peekReadyQueue().getCpuTime() != 0) {//computing
                compute();
                if(!tempProcessQueue.isEmpty()){//wait time increase
                    for (Process p : tempProcessQueue)
                        p.setWaitingTime(p.getWaitingTime() + 1);
                }
            } else if (!isEmptyReadyQueue() && peekReadyQueue().getCpuTime() == 0) {
                //System.err.println(pollReadyQueue().toString());//remove to normal poll
                process = pollReadyQueue();
                totalWaitTime += process.getWaitingTime();
                // set total wait time
            }
            if (isEmptyReadyQueue() && !tempProcessQueue.isEmpty()) {//moving temp q to ready q
                addToReadyQueue(tempProcessQueue.poll());
            }

            if(isEmptyUserInputQueue() && isEmptyReadyQueue() && tempProcessQueue.isEmpty())
                break;

            currentTime++;
        }

        System.out.println("SJF: ");
        averageWaitTime = totalWaitTime / getTotalQueueSize();
        System.out.println("Average Waiting Time: " + averageWaitTime);
    }

    @Override
    void execute() {
        simulation();
    }

    Comparator<Process> comparator() {
        return Comparator.comparingDouble(Process::getCpuTime);
    }
}