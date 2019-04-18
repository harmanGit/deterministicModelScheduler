package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Extends concrete subclasses SRTF, SJF, and FCFS responsible
 * for managing the respective simulations.
 *
 * Created by hsd77849 on 4/18/2019.
 */
abstract class Scheduler {
    private PriorityQueue<Process> readyQueue;
    private String[] parsedUserInput;
    private int initialCapacity;

    public Scheduler(String[] parsedUserInput, Comparator<Process> comparator) {
        this.parsedUserInput = parsedUserInput;
        this.initialCapacity = parsedUserInput.length;
        this.readyQueue = new PriorityQueue<>(initialCapacity, comparator);
    }

    abstract void populateReadyQueue();

    abstract  Comparator<Process> comparator();

}

        class SRTF extends Scheduler {
            public static Comparator<Process> comparatorSRTF = Comparator.comparingDouble(process -> process.cpuTime);

        /*   public static Comparator<Process> comparatorSRTF = (Process process1, Process process2) -> {
            if (process1.cpuTime > process2.cpuTime) {
                return 1;
            }
            if (process1.cpuTime < process2.cpuTime) {
                return -1;
            } else {
                return 0;
            }
        };*/

            public SRTF(String[] parsedUserInput) {
                super(parsedUserInput, comparatorSRTF);
            }

            void populateReadyQueue(){

            }
    }


    class SJF extends Scheduler {

        public static Comparator<Process> comparatorSJF = Comparator.comparingDouble(process -> process.cpuTime);

     /*   public static Comparator<Process> comparatorSJF = (Process process1, Process process2) -> {
            if (process1.cpuTime > process2.cpuTime) {
                return 1;
            }
            if (process1.cpuTime < process2.cpuTime) {
                return -1;
            } else {
                return 0;
            }
        };*/

        public SJF(String[] parsedUserInput) {
            super(parsedUserInput, comparatorSJF);

            //parsing user input
            for (int i = 0; i < initialCapacity; i++){

            }
        }

        @Override
        void populateReadyQueue() {

        }
    }

    class FCFS extends Scheduler {
        public static Comparator<Process> comparatorSRTF = (Process process1, Process process2) -> {
            if (process1.arrivalTime > process2.arrivalTime) {
                return 1;
            }
            if (process1.arrivalTime < process2.arrivalTime) {
                return -1;
            } else {
                return 0;
            }
        };

        public FCFS(String[] parsedUserInput) {
            super(parsedUserInput, comparatorSRTF);
        }

        @Override
        void populateReadyQueue() {
            //parsing user input
            for (int i = 0; i < initialCapacity; i++){
                readyQueue.add(new Process(Double.valueOf(parsedUserInput[i]), Double.valueOf(parsedUserInput[i++])));
            }
        }
    }