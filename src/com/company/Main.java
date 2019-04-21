package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * The purpose of this assignment is to investigate SRTF (Shortest Remaining Time First) scheduling
 * through deterministic modeling in Java. For simplicity we are ignoring dispatch latency and assuming
 * that each process consists of a single CPU burst.
 */
public class Main {

    public static void main(String[] args) {
        String rawUserInput;
        String[] parsedUserInput;
        Queue<String> inputQueue = new LinkedList<String>();
        SRTF SRTF;
        SJF SJF;
        FCFS FCFS;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter process arrival times and burst lengths.");

        rawUserInput = scanner.nextLine(); //test input: 1 8 2 5 3 5 4 2
        parsedUserInput = rawUserInput.split(" ");


        if (parsedUserInput.length % 2 == 0) {

            for (int i = 0; i < parsedUserInput.length; i++) {//move this to the scheduler
                if (parsedUserInput[i] != null)
                    inputQueue.add(parsedUserInput[i]);
            }

            SRTF = new SRTF(inputQueue);
            SJF = new SJF(inputQueue);
            FCFS = new FCFS(inputQueue);

//            SRTF.simulation();
            SJF.simulation();
            FCFS.simulation();
        } else
            System.out.println("Invalid Input: Missing Data!");


    }
}
