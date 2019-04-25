package com.company;

import java.util.Scanner;

/**
 * The purpose of this assignment is to investigate SRTF (Shortest Remaining Time First) scheduling
 * through deterministic modeling in Java(comparing SRTF with SJF and FCFS). For simplicity we are
 * ignoring dispatch latency and assuming that each process consists of a single CPU burst.
 *
 * User Input - Expected input is arrival time and cpu burst time, separated by space
 *  Example Input:
 *   1 8 2 5 3 5 4 2
 *   0 9 3 5 4 2
 *   0 10 3 5 4 2 7 8 8 4
 */
public class Main {

    public static void main(String[] args) {
        String rawUserInput;
        String[] parsedUserInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter process arrival times and burst lengths.");

        rawUserInput = scanner.nextLine();
        parsedUserInput = rawUserInput.split(" ");//parsing the string by the space

        if (parsedUserInput.length % 2 == 0) {// insuring each arrival time has a CPU burst
            new SRTF(parsedUserInput).simulation();
            new SJF(parsedUserInput).simulation();
            new FCFS(parsedUserInput).simulation();
        } else
            System.out.println("Invalid Input: Missing Data!");
    }
}
