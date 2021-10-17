/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author tana_
 */
public class CPUUtilizationModel {
    private int time;
    private int numberOfCPU;

    public CPUUtilizationModel(int time, int numberOfCPU) {
        this.time = time;
        this.numberOfCPU = numberOfCPU;
    }

    public CPUUtilizationModel() {
    }

    public int getTime() {
        return time;
    }

    public int getNumberOfCPU() {
        return numberOfCPU;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setNumberOfCPU(int numberOfCPU) {
        this.numberOfCPU = numberOfCPU;
    }

    @Override
    public String toString() {
        return "CPUUtilizationModel{" + "time=" + time + ", numberOfCPU=" + numberOfCPU + '}';
    }
    
    
}
