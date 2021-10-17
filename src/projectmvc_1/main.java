/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmvc_1;

import Controller.Controller;
import Model.CPUUtilizationModel;
import View.View;

/**
 *
 * @author tana_
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        View v = new View();
        CPUUtilizationModel m = new CPUUtilizationModel();
        Controller c =  new Controller(v, m);
    }
    
}
