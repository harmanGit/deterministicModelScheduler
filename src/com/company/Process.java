package com.company;

import java.util.Comparator;

/**
 * Class encapsulates the arrival time, waiting time, CPU time remaining, and any
 * other relevant attributes of a process.
 *
 * @author Harman Dhillon (4/18/2019)
 */
public class Process{
    String processID;
    double arrivalTime;
    double waitingTime;
    double cpuTime;

    public Process() {
        this.processID = "";
        this.arrivalTime = 0;
        this.waitingTime = 0;
        this.cpuTime = 0;
    }

    public Process(String processID, double arrivalTime, double cpuTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.waitingTime = 0;
    }

    public Process(String processID, double arrivalTime, double waitingTime, double cpuTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.waitingTime = waitingTime;
        this.cpuTime = cpuTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(double cpuTime) {
        this.cpuTime = cpuTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "processID='" + processID + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", waitingTime=" + waitingTime +
                ", cpuTime=" + cpuTime +
                '}';
    }
}
