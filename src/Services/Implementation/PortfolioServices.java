/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.FosUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Tools.DataSource;
import Entities.Job;
import Entities.Portfolio;
import Entities.User;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author AGENT6
 */
public class PortfolioServices {
    Portfolio portfolio;
    Connection cn=DataSource.dbConnexion();
    ObservableList<Portfolio> data;
    List<User> data2;
     public void addPortfolio(Portfolio p,int x){
        PreparedStatement pt;
        
        try{
            
            pt=cn.prepareStatement("insert into portfolio(skills,description,previousExperiences,freelancer_id ) VALUES(?,?,?,?)");           
                pt.setString(1, p.getSkills());
                pt.setString(2, p.getDescription());
                pt.setString(3, p.getPreviousExperiences());
                pt.setInt(4, x);
                                                
                pt.executeUpdate();
            //System.out.println("add job");
        }
        catch(SQLException ex){
            System.out.println("leee");
            
        }
    }
     
     
     
     public Portfolio ShowPortfolio(int x){
        data = FXCollections.observableArrayList();
        try{
            String req="select * from Portfolio where 	freelancer_id= '"+x+"'";
            ResultSet rs=cn.createStatement().executeQuery(req);
            while(rs.next())
            {
                
                Portfolio p=new Portfolio(rs.getString("skills"), rs.getString("description"), rs.getString("previousExperiences"));
                data.add(p);             
            }
        }
        catch(SQLException ex){System.out.println(ex.getMessage());}
        return data.get(0);
     }
     
     
     
     
     
     public User getUser(int x)
      {
          data2 = new ArrayList<User>();
          try{
            String req="select * from fos_user where id='"+x+"'";
            ResultSet rs=cn.createStatement().executeQuery(req);
            while(rs.next())
            {
          
                //User e= new User(x);
                //data2.add(e);             
            }
        }
        catch(SQLException ex){System.out.println(ex.getMessage());}
        return data2.get(0);
      }
     //service::
   /*  public void deleteJob(int id){
          PreparedStatement pt;
        
        try{
            String req="delete from job where id=?";
            pt=cn.prepareStatement(req);
           
            pt.setInt(1,id);
            pt.executeUpdate();
            System.out.println("tfasakh");
     }
    catch(SQLException ex){}
     
     }
     
     public void updateJob(Job j,int x){
         
         PreparedStatement pt;
         try{
             pt=cn.prepareStatement("update job set title=?,type=?,location=?,minSalary=?,maxSalary=?,description=? where id='"+x+"'");
             pt.setString(1, j.getTitre());
             pt.setString(2,j.getType() );
             pt.setString(3,j.getLocation() );
             pt.setDouble(4,j.getMinSal() );
             pt.setDouble(5,j.getMaxSal() );
             pt.setString(6,j.getDescription() );
             pt.executeUpdate();
             System.out.println("Updated succefully");
         }
         catch(SQLException ex){System.out.println("lele");}
     }
     
     
     public ObservableList<Job> ShowJob(){
        data = FXCollections.observableArrayList();
        try{
            String req="select * from job";
            ResultSet rs=cn.createStatement().executeQuery(req);
            int i=0;
            while(rs.next())
            {
                Job j=new Job(rs.getInt("id"), rs.getString("title"), rs.getString("type"),rs.getString("location"), rs.getInt("minSalary"),  rs.getInt("maxSalary"), rs.getString("description"));                                    
                data.add(j);             
            }
        
            System.out.println(data.toString());
        }
        catch(SQLException ex){System.out.println(ex.getMessage());}
        return data;
     }
     
     
     
      public ObservableList<Job> ShowJobByTypeFullstack(){
        data = FXCollections.observableArrayList();
       
        try{
            String req="select * from job where type='Fullstack'";
            ResultSet rs=cn.createStatement().executeQuery(req);
            int i=0;
            while(rs.next())
            {
                Job j=new Job(rs.getInt("id"), rs.getString("title"), rs.getString("type"),rs.getString("location"), rs.getInt("minSalary"),  rs.getInt("maxSalary"), rs.getString("description"));                                    
                data.add(j);              
            }
            System.out.println(""+data.size());
        }
        catch(SQLException ex){System.out.println(ex.getMessage());}
        return data;
     }
      
      
      public ObservableList<Job> ShowJobByTypeInternship(){
        data = FXCollections.observableArrayList();
       
        try{
            String req="select * from job where type='Internship'";
            ResultSet rs=cn.createStatement().executeQuery(req);
            int i=0;
            while(rs.next())
            {
                Job j=new Job(rs.getInt("id"), rs.getString("title"), rs.getString("type"),rs.getString("location"), rs.getInt("minSalary"),  rs.getInt("maxSalary"), rs.getString("description"));                                  
                data.add(j);              
            }
            
        }
        catch(SQLException ex){System.out.println(ex.getMessage());}
        return data;
     }*/
      
}