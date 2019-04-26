package com.company;

/**
 * Class encapsulates the process ID, arrival time, waiting time, CPU time remaining, of a process.
 *
 * @author Harman Dhillon (4/18/2019)
 */
public class Process {
    private String processID;//represents the process ID.
    private double arrivalTime;//represents the process arrival time.
    private double waitingTime;//represents the process waiting time.
    private double cpuTime;//represents the process cpu time "cpu burst length".

    /**
     * Default Constructor.
     */
    public Process() {
        this.processID = "";
        this.arrivalTime = 0;
        this.waitingTime = 0;
        this.cpuTime = 0;
    }

    /**
     * Explicit Constructor.
     *
     * @param processID   <code>String</code> represents the process ID.
     * @param arrivalTime <code>double</code> represents the process arrival time.
     * @param waitingTime <code>double</code> represents the process waiting time.
     * @param cpuTime     <code>double</code> represents the process cpu time "cpu burst length".
     */
    public Process(String processID, double arrivalTime, double waitingTime, double cpuTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.waitingTime = waitingTime;
        this.cpuTime = cpuTime;
    }

    /**
     * Explicit Constructor, without the wait time parameter (default value is 0.0).
     *
     * @param processID   <code>String</code> represents the process ID.
     * @param arrivalTime <code>double</code> represents the process arrival time.
     * @param cpuTime     <code>double</code> represents the process cpu time "cpu burst length".
     */
    public Process(String processID, double arrivalTime, double cpuTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.waitingTime = 0.0;
    }

    /**
     * Method returns the process objects ID.
     *
     * @return <code>String</code> represents the process ID.
     */
    public String getProcessID() {
        return processID;
    }

    /**
     * Method is used to set the process objects ID.
     *
     * @param processID <code>String</code> represents the process ID.
     */
    public void setProcessID(String processID) {
        this.processID = processID;
    }

    /**
     * Method returns the process objects arrival time.
     *
     * @return <code>double</code> represents the process arrival time.
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Method is used to set the process objects arrival time.
     *
     * @param arrivalTime <code>double</code> represents the process arrival time.
     */
    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Method returns the process objects wait time.
     *
     * @return <code>double</code> represents the process waiting time.
     */
    public double getWaitingTime() {
        return waitingTime;
    }

    /**
     * Method is used to set the process objects wait time.
     *
     * @param waitingTime <code>double</code> represents the process waiting time.
     */
    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     * Method returns the process objects cpu time.
     *
     * @return <code>double</code> represents the process cpu time "cpu burst length".
     */
    public double getCpuTime() {
        return cpuTime;
    }

    /**
     * Method is used to set the process objects cpu time.
     *
     * @param cpuTime <code>double</code> represents the process cpu time "cpu burst length".
     */
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
