/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CPUUtilizationModel;
import Model.Database;
import View.View;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author tana_
 */
public class Controller implements ActionListener {

    private View v;
    private CPUUtilizationModel m;
    private Database db;
    private ArrayList<CPUUtilizationModel> CPUUtiList;

    public Controller(View v, CPUUtilizationModel m) {
        this.v = v;
        this.m = m;
        this.db = new Database();
        v.setVisible(true);

        v.getjButton1().setActionCommand("submit");
        v.getjButton1().addActionListener(this);
        
        v.getjButton2().setActionCommand("show");
        v.getjButton2().addActionListener(this);
        
        v.getjTextField1().addActionListener(this);
        v.getjTextField2().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("submit")) {
            CPUUtiList = db.selectAllCPUUTilization();
            double time = Double.parseDouble(v.getjTextField1().getText());
            int cpu = Integer.parseInt(v.getjTextField2().getText());
            int lastTime;
            if (time % 5 != 0) {
                time = time + (5 - time % 5);
            }
            
           
            boolean du = false;
            if (!CPUUtiList.isEmpty()) {
                lastTime = CPUUtiList.get(CPUUtiList.size() - 1).getTime();
                for (CPUUtilizationModel c : CPUUtiList) {
                    if (c.getTime() == time && c.getNumberOfCPU() < cpu) {
                        du = true;
                        break;
                    }
                }
            }else{
                lastTime = 0;
            }
            
            if (time >= lastTime) {
                m.setNumberOfCPU(cpu);
                m.setTime((int) time);
                String status;
                if (du) {
                    status = db.update(m);
                } else {
                    status = db.insert(m);
                }
                v.setjTextArea1(status);
            } else {
                JOptionPane.showMessageDialog(v, "New Time must more Old Time", "ERROR", 2);
            }
//        
        }
        
        if(command.equals("show")){
             CPUUtiList = db.selectTOP15CPUUTilization();
             if(!CPUUtiList.isEmpty()){
                 if(CPUUtiList.size() > 15){
                        v.show15(new ArrayList( CPUUtiList.subList(0, 15)));
                 }
                 v.show15(CPUUtiList);
             }else{
                v.setjTextArea1("It's Empty");
             }
        
        }

    }

}
