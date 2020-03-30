/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Implementation;

import Entities.FosUser;
import Entities.Project;
import Entities.Review;
import Entities.User;
import Tools.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;

/**
 *
 * @author Ahmed
 */
public class ReviewService extends Review {

    private ObservableList<Event>data;
    private static ReviewService reviewServiceInstance;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    Connection cnx = DataSource.dbConnexion();   
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());
    Review review;
   
    public ReviewService(){}
    
    public static ReviewService getInstance(){
        if(reviewServiceInstance==null) 
            reviewServiceInstance=new ReviewService();
        return reviewServiceInstance;
    }
    
    public void AjoutReview(Review r) throws SQLException{
    try{
        String req ="INSERT INTO review (onBudget,onTime,rating,comment,projectId,freelancerReviewedId) Values(?,?,?,?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        
        st.setInt(1, r.getOnBudget());
        st.setInt(2, r.getOnTime());
        st.setInt(3, r.getRating());
        st.setString(4, r.getComment());
        st.setInt(5, r.getProjectId());
        st.setInt(6, r.getFreelancerReviewedId());
        st.executeUpdate();
        System.out.println("Review added");
    }
    catch (SQLException ex){
        Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE,null, ex);
        System.out.println(ex.getMessage());
    }    
    }
    
    public ObservableList<Review> AfficherReview(){
        ObservableList data;
        data =  FXCollections.observableArrayList();
        try{
            String req="select * from review";
            ResultSet rs=cnx.createStatement().executeQuery(req);
            int i=0;
            while(rs.next())
            {
                Review r= new Review(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
                data.add(r);
                i++;
            }
            //System.out.println(data.toString());
        }
        catch(SQLException ex){System.out.println("Erreur affiche review");}
        return data;
     }
    
    public ObservableList<User> AfficherFreelancers(){
        ObservableList data;
        data =  FXCollections.observableArrayList();
        try{
            String req="select * from fos_user WHERE type='freelancer'";
            ResultSet rs=cnx.createStatement().executeQuery(req);
            int i=0;
            while(rs.next())
            {
                User fu= new User(rs.getInt(1),rs.getString(2));
                data.add(fu);
                i++;
            }
            //System.out.println(data.toString());
        }
        catch(SQLException ex){System.out.println(ex.getMessage());
        }
        return data;
     }
    
    public ObservableList<Project> AfficherProject(){
        ObservableList data1;
        data1 =  FXCollections.observableArrayList();
        try{
            String req="select * from project";
            ResultSet rs=cnx.createStatement().executeQuery(req);
            int i=0;
            while(rs.next())
            {
                Project p= new Project(rs.getInt(1),rs.getString(2));
                data1.add(p);
                i++;
            }
            //System.out.println(data1.toString());
        }
        catch(SQLException ex){System.out.println(ex.getMessage());;}
        return data1;
     }
    
    
     public void UpdateReview(Review r,int x){
         
         PreparedStatement pt;
         try{
             pt=cnx.prepareStatement("update Review set onBudget=?,onTime=?,rating=?,comment=? where id="+x);
             pt.setInt(1, r.getOnBudget());
             pt.setInt(2,r.getOnTime());
             pt.setInt(3,r.getRating());
             pt.setString(4,r.getComment());

             
             pt.executeUpdate();
             System.out.println("Updated succefully");
         }
         catch(SQLException ex){System.out.println("Erreur");}
     }

    public void DeleteReview(int Id) {
        PreparedStatement ps;
        try{
            ps=cnx.prepareStatement("delete from review where id=?");
            ps.setInt(1, Id);
            ps.executeUpdate();
            System.out.println("Deleted with success");
        }catch(SQLException ex){
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
}
