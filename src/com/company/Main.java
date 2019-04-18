package com.company;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * The purpose of this assignment is to investigate SRTF (Shortest Remaining Time First) scheduling
 * through deterministic modeling in Java. For simplicity we are ignoring dispatch latency and assuming
 * that each process consists of a single CPU burst.
 */
public class Main {

    public static void main(String[] args) {
        PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
        Process tempProcess = new Process();
        String rawUserInput = "nothin";
        String[] parsedUserInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter process arrival times and burst lengths.");

        rawUserInput = scanner.nextLine(); //test input: 0 10 3 5 4 2 7 8 8
        parsedUserInput = rawUserInput.split(" ");

        if(parsedUserInput.length % 2 == 0){
            System.out.println("Invalid Input: Missing Data!");
            return;
        }

        //parsing user input
        for (int i = 0; i < parsedUserInput.length; i++){

            if((i+1)%2 == 0)
                tempProcess.setArrivalTime(Double.valueOf(parsedUserInput[i]));//even
            else
                tempProcess.setArrivalTime(Double.valueOf(parsedUserInput[i]));//odd

            //priorityQueue.add(new Process(parsedUserInput[i], parsedUserInput[i++]));
            //new Process();
        }
        //while(){};
    }
}
