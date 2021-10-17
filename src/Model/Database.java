/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author tana_
 */
public class Database {
     private String driver = "org.apache.derby.jdbc.ClientDriver";
    private String url = "jdbc:derby://localhost:1527/anything";
    private String username = "root";
    private String password = "toor";
    
    private Connection connect;

    public Database() {
        try{
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.err.println("make it");
            
        }catch(Exception e){
             System.err.println("error");
             e.printStackTrace();
        }
    }
    
    public void close(){
        try {
            if (connect!=null){
                connect.close();
                System.err.println("\n close connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<CPUUtilizationModel> selectAllCPUUTilization(){
        ArrayList<CPUUtilizationModel> cpuList = new ArrayList<>();
        String sql = "SELECT * FROM cpu";
        try{
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
               
            while(result.next()){
                CPUUtilizationModel cpu = new CPUUtilizationModel();
                cpu.setTime(result.getInt("time"));
                cpu.setNumberOfCPU(result.getInt("cpu"));
                cpuList.add(cpu);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return cpuList;
    }
    
        public ArrayList<CPUUtilizationModel> selectTOP15CPUUTilization(){
        ArrayList<CPUUtilizationModel> cpuList = new ArrayList<>();
//        String sql = "SELECT TOP(15) * FROM cpu Order By time DESC ";
        String sql = "SELECT * FROM cpu Order By time DESC ";
        try{
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
               
            while(result.next()){
                CPUUtilizationModel cpu = new CPUUtilizationModel();
                cpu.setTime(result.getInt("time"));
                cpu.setNumberOfCPU(result.getInt("cpu"));
                cpuList.add(cpu);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return cpuList;
    }
    
    public String insert(CPUUtilizationModel cpu){
        String sql = "INSERT INTO cpu (time, cpu) VALUES (?, ?)";
        String status="";
        try{
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, cpu.getTime());
            ps.setInt(2, cpu.getNumberOfCPU());
            int record = ps.executeUpdate();
            if (record >= 1){
                status = "inset success";
            }else{
                status = "insert error";
            }
        } catch (Exception e){
                e.printStackTrace();
        }
        return  status;
    }
    
        
    public String update(CPUUtilizationModel cpu){
        String status="";
        String sql = "update cpu set cpu  = " + cpu.getNumberOfCPU()+ " where time = " + cpu.getTime();
    
        try{
            PreparedStatement ps = connect.prepareStatement(sql);
            int record = ps.executeUpdate();
            if (record >= 1){
               status = "update success";
            }else{
                status = "update error";
            }
                    
        } catch (Exception e){
                e.printStackTrace();
        }
        return  status;
    }
//    
//    public boolean delete(Books b){
//        boolean result = false;
//        String sql = "delete from books where book_id = " + b.getBookId();
//        try{
//            PreparedStatement ps = connect.prepareStatement(sql);
//            int record = ps.executeUpdate();
//            if (record >= 1){
//                System.out.println("success");
//            }else{
//                System.out.println("error");
//            }
//                    
//        } catch (Exception e){
//                e.printStackTrace();
//        }
//        return  result;
//    }
//    
//    public ArrayList<Books> searchByKeyword(String keyword){
//        ArrayList<Books> bList = new ArrayList<>();
//        String sql = "SELECT * from books where book_name = '" + keyword + "'";
//        try{
//            PreparedStatement ps = connect.prepareStatement(sql);
//            ResultSet result = ps.executeQuery();
//            while(result.next()){
//                Books b = new Books();
//                b.setBookId(result.getInt("book_id"));
//                b.setBookName(result.getString("book_name"));
//                bList.add(b);
//            }
//
//                    
//        } catch (Exception e){
//                e.printStackTrace();
//        }
//        return  bList;
//       
//    }
    
}
