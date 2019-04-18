package com.company;

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
        SRTF SRTF;
        SJF SJF;
        FCFS FCFS;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter process arrival times and burst lengths.");

        rawUserInput = scanner.nextLine(); //test input: 0 10 3 5 4 2 7 8 8
        parsedUserInput = rawUserInput.split(" ");

        if (parsedUserInput.length % 2 != 0) {
            //parsing user input
            for (int i = 0; i < parsedUserInput.length; i++){

            }

        } else
            System.out.println("Invalid Input: Missing Data!");

    }
}
